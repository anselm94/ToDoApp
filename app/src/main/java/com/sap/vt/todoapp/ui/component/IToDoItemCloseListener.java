package com.sap.vt.todoapp.ui.component;

import com.sap.vt.todoapp.domain.ToDoNote;

/**
 * Created by I327891 on 15-Jun-17.
 */

public interface IToDoItemCloseListener {
    void onToDoItemCloseClicked(ToDoNote closeToDoItem);
}
