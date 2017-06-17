package com.sap.vt.todoapp.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.sap.vt.todoapp.R;
import com.sap.vt.todoapp.domain.IToDoNoteRepoListener;
import com.sap.vt.todoapp.domain.ToDoNote;
import com.sap.vt.todoapp.domain.ToDoNoteRepo;
import com.sap.vt.todoapp.ui.component.IToDoItemListener;
import com.sap.vt.todoapp.ui.component.ToDoDoneAdapter;
import com.sap.vt.todoapp.ui.component.ToDoNotDoneAdapter;
import com.sap.vt.todoapp.ui.fragment.NewToDoNoteDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IToDoNoteRepoListener, IToDoItemListener {
    ToDoNoteRepo toDoNoteRepo;
    private ToDoDoneAdapter todoDoneAdapter;
    private ToDoNotDoneAdapter toDoNotDoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newToDoNoteDialogFrag = getFragmentManager().findFragmentByTag("fragment_new_todo_note");
                if (newToDoNoteDialogFrag != null) {
                    getFragmentManager().beginTransaction().remove(newToDoNoteDialogFrag).commit();
                }

                NewToDoNoteDialog newToDoNoteDialog = new NewToDoNoteDialog();
                newToDoNoteDialog.show(getFragmentManager(), "fragment_new_todo_note");
            }
        });

        ListView notDoneList = (ListView) findViewById(R.id.list_todo_notdone);
        ListView doneList = (ListView) findViewById(R.id.list_todo_done);
        toDoNotDoneAdapter = new ToDoNotDoneAdapter(this, null);
        todoDoneAdapter = new ToDoDoneAdapter(this, null);
        toDoNotDoneAdapter.setToDoItemListener(this);
        todoDoneAdapter.setToDoItemListener(this);
        notDoneList.setAdapter(toDoNotDoneAdapter);
        doneList.setAdapter(todoDoneAdapter);

        toDoNoteRepo = ToDoNoteRepo.getInstance(getApplicationContext());
        toDoNoteRepo.setListener(this);
        toDoNoteRepo.refreshListener();
    }

//    private void setValues() {
//        ToDoNote a = new ToDoNote();
//        a.setId(1);
//        a.setTitle("Go to Market");
//        a.setDescription("Buy vegetables");
//        a.setDone(true);
//
//        ToDoNote b = new ToDoNote();
//        b.setId(2);
//        b.setTitle("Go to Mall");
//        b.setDescription("Buy clothes");
//        b.setDone(false);
//
//        ArrayList<ToDoNote> tobedone = new ArrayList<>();
//        ArrayList<ToDoNote> done = new ArrayList<>();
//
//        tobedone.add(a);
//        tobedone.add(b);
//        done.add(a);
//        done.add(b);
//
//        this.onUpdate(tobedone, done);
//    }

    @Override
    public void onUpdate(ArrayList<ToDoNote> notDoneItems, ArrayList<ToDoNote> doneItems) {
        toDoNotDoneAdapter.setData(notDoneItems);
        todoDoneAdapter.setData(doneItems);
        toDoNotDoneAdapter.notifyDataSetChanged();
        todoDoneAdapter.notifyDataSetChanged();
    }

    public void createToDoNote(String title, String description) {
        ToDoNote newNote = new ToDoNote();
        newNote.setTitle(title);
        newNote.setDescription(description);
        newNote.setDone(false);
        toDoNoteRepo.createNote(newNote);
    }

    @Override
    public void onToDoItemCloseClicked(ToDoNote closeToDoItem) {
        toDoNoteRepo.deleteNote(closeToDoItem);
    }

    @Override
    public void onToDoItemCheckedClicked(ToDoNote checkedToDoItem) {
        toDoNoteRepo.updateNote(checkedToDoItem);
    }
}
