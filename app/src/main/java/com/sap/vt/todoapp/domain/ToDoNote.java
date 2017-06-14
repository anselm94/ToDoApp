package com.sap.vt.todoapp.domain;

/**
 * Created by I329046 on 6/14/2017.
 */

public class ToDoNote {
    private String title;
    private String description;
    private boolean isDone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
