package com.sap.vt.todoapp.domain;

import java.util.ArrayList;

/**
 * Created by I329046 on 6/14/2017.
 */

public interface IToDoNoteRepoListener {
    void onUpdate(ArrayList<ToDoNote> notDoneItems, ArrayList<ToDoNote> doneItems);
}
