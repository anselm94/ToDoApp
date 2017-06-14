package com.sap.vt.todoapp.persistence;

import com.sap.vt.todoapp.domain.ToDoNote;

import java.util.ArrayList;

/**
 * Created by I329046 on 6/14/2017.
 */

public interface IPersistenceMapper {
    int createEntry(ToDoNote note);

    ToDoNote readEntry(int id);

    ArrayList<ToDoNote> readEntries();

    boolean updateEntryStatus(int id);

    boolean deleteEntry(int id);
}
