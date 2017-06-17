package com.sap.vt.todoapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sap.vt.todoapp.domain.ToDoNote;
import com.sap.vt.todoapp.persistence.SQLitePersistence;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SQLitePersistenceTest {
    @Test
    public void validateInsert() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLitePersistence sqLitePersistence = new SQLitePersistence(appContext);

        ToDoNote toDoNote = new ToDoNote();
        toDoNote.setTitle("Test");
        toDoNote.setDescription("Test");
        toDoNote.setDone(true);

        assertTrue(sqLitePersistence.createEntry(toDoNote));

        ToDoNote retrievedNote = sqLitePersistence.readEntries().get(sqLitePersistence.readEntries().size() - 1);
        assertEquals(toDoNote.getTitle(), retrievedNote.getTitle());
        assertEquals(toDoNote.getDescription(), retrievedNote.getDescription());
        assertEquals(toDoNote.isDone(), retrievedNote.isDone());
    }

    @Test
    public void validateDeleteNote() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLitePersistence sqLitePersistence = new SQLitePersistence(appContext);

        ToDoNote toDoNote = new ToDoNote();
        toDoNote.setTitle("Test Again");
        toDoNote.setDescription("Test Again");
        toDoNote.setDone(false);

        assertTrue(sqLitePersistence.createEntry(toDoNote));
        ToDoNote retrievedAddedNote = sqLitePersistence.readEntries().get(sqLitePersistence.readEntries().size() - 1);
        assertTrue(sqLitePersistence.deleteEntry(retrievedAddedNote.getId()));
        ToDoNote retrievedLastNote = sqLitePersistence.readEntries().get(sqLitePersistence.readEntries().size() - 1);
        assertNotEquals(toDoNote.getTitle(), retrievedLastNote.getTitle());
        assertNotEquals(toDoNote.getDescription(), retrievedLastNote.getDescription());
    }
}
