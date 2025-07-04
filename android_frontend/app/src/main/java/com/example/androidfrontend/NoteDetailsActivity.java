package com.example.androidfrontend;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;

// PUBLIC_INTERFACE
public class NoteDetailsActivity extends AppCompatActivity {
    /** Material screen showing a note's details, read-only. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Notes_Light);
        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Note note = (Note) getIntent().getSerializableExtra("note");
        if (note != null) {
            setTitle(note.getTitle().isEmpty() ? "(Untitled)" : note.getTitle());
            ((TextView)findViewById(R.id.details_title)).setText(note.getTitle());
            ((TextView)findViewById(R.id.details_content)).setText(note.getContent());
            ((TextView)findViewById(R.id.details_timestamp))
                .setText(DateFormat.getDateTimeInstance().format(note.getTimestamp()));
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
