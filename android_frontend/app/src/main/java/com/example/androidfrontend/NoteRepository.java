package com.example.androidfrontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// PUBLIC_INTERFACE
public class NoteRepository {
    /** In-memory repo for demo; can be replaced with DB or file storage if needed. */
    private final ArrayList<Note> notes = new ArrayList<>();
    private long idCounter = 0;

    // PUBLIC_INTERFACE
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    // PUBLIC_INTERFACE
    public Note getNoteById(long id) {
        for (Note n : notes) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    // PUBLIC_INTERFACE
    public void addNote(Note note) {
        note.setId(++idCounter);
        note.setTimestamp(System.currentTimeMillis());
        notes.add(0, note);
    }

    // PUBLIC_INTERFACE
    public void updateNote(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == note.getId()) {
                notes.set(i, note);
                return;
            }
        }
    }

    // PUBLIC_INTERFACE
    public void deleteNote(long id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                notes.remove(i);
                return;
            }
        }
    }

    // PUBLIC_INTERFACE
    public List<Note> searchNotes(String query) {
        ArrayList<Note> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase(Locale.ROOT);
        for (Note note : notes) {
            if (note.getTitle().toLowerCase(Locale.ROOT).contains(lowerQuery) ||
                note.getContent().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                result.add(note);
            }
        }
        return result;
    }
}
