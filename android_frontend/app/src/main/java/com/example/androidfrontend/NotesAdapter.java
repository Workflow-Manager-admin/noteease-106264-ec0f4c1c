package com.example.androidfrontend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

// PUBLIC_INTERFACE
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    public interface OnNoteListener {
        void onNoteClicked(Note note);
    }

    private List<Note> notes;
    private final OnNoteListener listener;

    public NotesAdapter(List<Note> notes, OnNoteListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle().isEmpty() ? "(Untitled)" : note.getTitle());
        holder.content.setText(note.getContent());
        holder.timestamp.setText(DateFormat.getDateTimeInstance().format(note.getTimestamp()));
        holder.itemView.setOnClickListener(v -> listener.onNoteClicked(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    // PUBLIC_INTERFACE
    public void updateData(List<Note> updatedNotes) {
        this.notes = updatedNotes;
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, timestamp;

        NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_item_title);
            content = itemView.findViewById(R.id.note_item_content);
            timestamp = itemView.findViewById(R.id.note_item_timestamp);
        }
    }
}
