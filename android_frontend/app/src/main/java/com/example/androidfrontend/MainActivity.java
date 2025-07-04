package com.example.androidfrontend;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

// PUBLIC_INTERFACE
public class MainActivity extends AppCompatActivity implements NotesAdapter.OnNoteListener {
    private NoteRepository noteRepository;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Notes_Light); // custom light material theme
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteRepository = NotesApp.getRepository(this);

        // Adapter with note click *and* long click support
        notesAdapter = new NotesAdapter(noteRepository.getAllNotes(), this);

        RecyclerView recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_note);
        fab.setOnClickListener(v -> startNewNote());

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterNotes(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterNotes(newText);
                return true;
            }
        });

        // Handle note details navigation on long click (optional UX)
        recyclerView.addOnItemTouchListener(new NoteItemLongClickListener(this, recyclerView, new NoteItemLongClickListener.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Note longPressedNote = noteRepository.getAllNotes().get(position);
                Intent intent = new Intent(MainActivity.this, NoteDetailsActivity.class);
                intent.putExtra("note", longPressedNote);
                startActivity(intent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startNewNote() {
        Intent intent = new Intent(this, NoteEditorActivity.class);
        startActivityForResult(intent, NoteEditorActivity.REQUEST_CODE_NEW);
    }

    private void filterNotes(String query) {
        List<Note> filtered = noteRepository.searchNotes(query);
        notesAdapter.updateData(filtered);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == NoteEditorActivity.REQUEST_CODE_EDIT ||
                requestCode == NoteEditorActivity.REQUEST_CODE_NEW) {
                // Repopulate the adapter (noteRepository already updated)
                notesAdapter.updateData(noteRepository.getAllNotes());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNoteClicked(Note note) {
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("note", note);
        startActivityForResult(intent, NoteEditorActivity.REQUEST_CODE_EDIT);
    }
}
