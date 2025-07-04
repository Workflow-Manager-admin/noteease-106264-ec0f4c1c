package com.example.androidfrontend;

import android.app.Application;
import android.content.Context;

// PUBLIC_INTERFACE
public class NotesApp extends Application {
    private static NoteRepository repo;

    @Override
    public void onCreate() {
        super.onCreate();
        repo = new NoteRepository();
    }

    // PUBLIC_INTERFACE
    public static NoteRepository getRepository(Context context) {
        if (repo == null) {
            repo = new NoteRepository();
        }
        return repo;
    }
}
