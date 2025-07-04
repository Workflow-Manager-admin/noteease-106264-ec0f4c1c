package com.example.androidfrontend;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditorActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_NEW = 101;
    public static final int REQUEST_CODE_EDIT = 102;

    private Note editingNote;
    private EditText titleInput, contentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Notes_Light);
        setContentView(R.layout.activity_note_editor);

        Toolbar toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleInput = findViewById(R.id.edit_title);
        contentInput = findViewById(R.id.edit_content);

        editingNote = (Note) getIntent().getSerializableExtra("note");
        if (editingNote != null) {
            titleInput.setText(editingNote.getTitle());
            contentInput.setText(editingNote.getContent());
            setTitle("Edit Note");
        } else {
            setTitle("New Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_editor, menu);
        // Hide delete option if new note
        if (editingNote == null) {
            menu.findItem(R.id.action_delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NoteRepository repo = NotesApp.getRepository(this);
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_save) {
            String titleStr = titleInput.getText().toString().trim();
            String contentStr = contentInput.getText().toString().trim();
            if (TextUtils.isEmpty(titleStr) && TextUtils.isEmpty(contentStr)) {
                Toast.makeText(this, "Note is empty!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (editingNote == null) {
                repo.addNote(new Note(titleStr, contentStr));
            } else {
                editingNote.setTitle(titleStr);
                editingNote.setContent(contentStr);
                editingNote.setTimestamp(System.currentTimeMillis());
                repo.updateNote(editingNote);
            }
            setResult(RESULT_OK, new Intent());
            finish();
            return true;
        } else if (id == R.id.action_delete) {
            if (editingNote != null) {
                repo.deleteNote(editingNote.getId());
                setResult(RESULT_OK, new Intent());
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
