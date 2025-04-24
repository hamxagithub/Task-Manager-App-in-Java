package com.example.assignment2;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private long id;
    private String title;
    private String description;
    private String date;
    private String priority;

    public TaskModel(long id, String title, String description, String date, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    public TaskModel(String title, String description, String date, String priority) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) { this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    public String getDate() {
        return date;
    }
    public void setDate(String date) { this.date = date; }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) { this.priority = priority; }
}
