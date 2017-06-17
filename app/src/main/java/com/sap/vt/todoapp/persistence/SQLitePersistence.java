package com.sap.vt.todoapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sap.vt.todoapp.domain.ToDoNote;

import java.util.ArrayList;

/**
 * Created by I329046 on 6/14/2017.
 */

public class SQLitePersistence extends SQLiteOpenHelper implements IPersistenceMapper {
    public static final String DATABASE_NAME = "ToDoDB.db";
    public static final String TODO_TABLE_NAME = "todo";
    public static final String TODO_COLUMN_ID = "id";
    public static final String TODO_COLUMN_TITLE = "title";
    public static final String TODO_COLUMN_EMAIL = "description";
    public static final String CONTACTS_COLUMN_STREET = "isDone";

    public SQLitePersistence(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE todo " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR,description VARCHAR,isDone integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
        onCreate(db);
    }

    public void resetTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        this.onUpgrade(db, 1, 0);
    }


    @Override
    public boolean createEntry(ToDoNote note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO todo (title, description, isDone) VALUES (\"" +
                note.getTitle() + "\",\"" + note.getDescription() + "\"," + Integer.toString(note.isDone() ? 1 : 0) + ")");
        return true;
    }

    @Override
    public ToDoNote readEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from todo where id=" + id + "", null);
        ToDoNote todo = new ToDoNote();
        todo.setId(cursor.getInt(0));
        todo.setTitle(cursor.getString(1));
        todo.setTitle(cursor.getString(2));
        todo.setDone(cursor.getInt(3) == 1);
        return todo;
    }

    @Override
    public ArrayList<ToDoNote> readEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from todo", null);
        ArrayList<ToDoNote> todoNotes = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            ToDoNote todo = new ToDoNote();
            todo.setId(cursor.getInt(0));
            todo.setTitle(cursor.getString(1));
            todo.setDescription(cursor.getString(2));
            todo.setDone(cursor.getInt(3) == 1);
            todoNotes.add(todo);
        }
        return todoNotes;
    }

    @Override
    public boolean updateEntryStatus(ToDoNote toDoNote) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE todo" +
                " SET isDone = " + Integer.toString(toDoNote.isDone() ? 1 : 0) +
                " WHERE id = " + toDoNote.getId());
        return true;
    }

    @Override
    public boolean deleteEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM todo WHERE id = " + id);
        return true;
    }
}
