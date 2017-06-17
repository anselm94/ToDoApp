package com.sap.vt.todoapp.domain;

import android.content.Context;

import com.sap.vt.todoapp.persistence.SQLitePersistence;

import java.util.ArrayList;

/**
 * Created by I329046 on 6/14/2017.
 */

public class ToDoNoteRepo {

    private static ToDoNoteRepo mInstance;
    private IToDoNoteRepoListener mListener;

    private SQLitePersistence sqLitePersistence;

    private ToDoNoteRepo(Context context) {
        sqLitePersistence = new SQLitePersistence(context);
    }

    public static ToDoNoteRepo getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ToDoNoteRepo(context);
        }
        return mInstance;
    }

    public void setListener(IToDoNoteRepoListener listener) {
        this.mListener = listener;
    }

    public void deleteNote(ToDoNote delNote) {
        sqLitePersistence.deleteEntry(delNote.getId());
        this.refreshListener();
    }

    public boolean createNote(ToDoNote createdNote) {
        boolean created = sqLitePersistence.createEntry(createdNote);
        this.refreshListener();
        return created;
    }

    public ArrayList<ToDoNote> getNoteItems() {
        return sqLitePersistence.readEntries();
    }

//    public ToDoNote getNote(){
//        return sqLitePersistence.readEntry()
//    }

    public boolean updateNote(ToDoNote updatedNote) {
        boolean updated = sqLitePersistence.updateEntryStatus(updatedNote);
        this.refreshListener();
        return updated;
    }

    public void refreshListener() {
        if (this.mListener != null) {
            ArrayList<ToDoNote> allItems = this.getNoteItems();
            ArrayList<ToDoNote> doneItems = new ArrayList<>();
            ArrayList<ToDoNote> notDoneItems = new ArrayList<>();
            for (ToDoNote item : allItems) {
                if (item.isDone()) {
                    doneItems.add(item);
                } else {
                    notDoneItems.add(item);
                }
            }
            this.mListener.onUpdate(notDoneItems, doneItems);
        }
    }
}
