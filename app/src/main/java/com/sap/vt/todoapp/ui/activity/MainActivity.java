package com.sap.vt.todoapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.sap.vt.todoapp.R;
import com.sap.vt.todoapp.domain.IToDoNoteRepoListener;
import com.sap.vt.todoapp.domain.ToDoNote;
import com.sap.vt.todoapp.ui.component.ToDoDoneAdapter;
import com.sap.vt.todoapp.ui.component.ToDoNotDoneAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IToDoNoteRepoListener {

    private ToDoDoneAdapter todoDoneAdapter;
    private ToDoNotDoneAdapter toDoNotDoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView notDoneList = (ListView) findViewById(R.id.list_todo_notdone);
        ListView doneList = (ListView) findViewById(R.id.list_todo_done);
        toDoNotDoneAdapter = new ToDoNotDoneAdapter(this, null);
        todoDoneAdapter = new ToDoDoneAdapter(this, null);
        notDoneList.setAdapter(toDoNotDoneAdapter);
        doneList.setAdapter(todoDoneAdapter);
    }

    @Override
    public void onUpdate(ArrayList<ToDoNote> notDoneItems, ArrayList<ToDoNote> doneItems) {
        toDoNotDoneAdapter.setData(notDoneItems);
        todoDoneAdapter.setData(doneItems);
        toDoNotDoneAdapter.notifyDataSetChanged();
        todoDoneAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewNoteCreated(ToDoNote todoNote) {

    }
}
