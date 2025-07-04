package com.example.androidfrontend;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// PUBLIC_INTERFACE
public class SettingsActivity extends AppCompatActivity {
    /** Material-themed settings screen (minimal demo for scaffold). */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Notes_Light);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Settings");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
