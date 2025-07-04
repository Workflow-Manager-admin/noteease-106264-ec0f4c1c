package com.example.androidfrontend;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

// PUBLIC_INTERFACE
public class NoteItemLongClickListener implements RecyclerView.OnItemTouchListener {
    /** Helper to add long click for RecyclerView. */
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private GestureDetector gestureDetector;
    private OnItemLongClickListener listener;

    public NoteItemLongClickListener(Context ctx, final RecyclerView recyclerView, OnItemLongClickListener listener) {
        this.listener = listener;
        gestureDetector = new GestureDetector(ctx, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                View v = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (v != null && listener != null) {
                    int pos = recyclerView.getChildAdapterPosition(v);
                    listener.onItemLongClick(v, pos);
                }
            }
            @Override
            public boolean onSingleTapUp(MotionEvent e) { return false; }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
}
