package com.example.androidfrontend;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// PUBLIC_INTERFACE
public class AboutActivity extends AppCompatActivity {
    /** Minimal material About screen for NoteEase. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Notes_Light);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
