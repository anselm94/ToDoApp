package com.sap.vt.todoapp.domain;

/**
 * Created by I329046 on 6/14/2017.
 */

public class ToDoNoteRepo {

    private ToDoNoteRepo mInstance;
    private IToDoNoteRepoListener mListener;

    private ToDoNoteRepo() {

    }

    public ToDoNoteRepo getInstance() {
        if (mInstance == null) {
            this.mInstance = new ToDoNoteRepo();
        }
        return this.mInstance;
    }

    public void setListener(IToDoNoteRepoListener listener) {
        this.mListener = listener;
    }
}
