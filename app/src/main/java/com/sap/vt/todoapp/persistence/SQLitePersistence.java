package com.sap.vt.todoapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by I329046 on 6/14/2017.
 */

public class SQLitePersistence extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoDB.db";
    public static final String TODO_TABLE_NAME = "todo";
    public static final String TODO_COLUMN_ID = "id";
    public static final String TODO_COLUMN_TITLE = "title";
    public static final String TODO_COLUMN_EMAIL = "description";
    public static final String TODO_COLUMN_STATUS = "isDone";

    public SQLitePersistence(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
