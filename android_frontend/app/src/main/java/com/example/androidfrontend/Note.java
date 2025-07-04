package com.example.androidfrontend;

import java.io.Serializable;

// PUBLIC_INTERFACE
public class Note implements Serializable {
    /** Model class representing a Note for storage and transport between activities. */
    private long id; // for database or list identification
    private String title;
    private String content;
    private long timestamp;

    public Note(long id, String title, String content, long timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }
    public Note(String title, String content) {
        this(-1, title, content, System.currentTimeMillis());
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public long getTimestamp() { return timestamp; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
