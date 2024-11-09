package com.example.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, deadline INTEGER, completed INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }

    public ArrayList<Task> getPendingTasks() {
        return getTasks(0);
    }

    public ArrayList<Task> getCompletedTasks() {
        return getTasks(1);
    }

    private ArrayList<Task> getTasks(int completedStatus) {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tasks", null, "completed=?", new String[]{String.valueOf(completedStatus)}, null, null, null);
        while (cursor.moveToNext()) {
            tasks.add(new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getInt(4) == 1));
        }
        cursor.close();
        return tasks;
    }

    public void insertTask(String name, String description, long deadline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("deadline", deadline);
        values.put("completed", 0);
        db.insert("tasks", null, values);
    }

    public void updateTask(int id, String name, String description, long deadline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("deadline", deadline);
        db.update("tasks", values, "id=?", new String[]{String.valueOf(id)});
    }

    public void markTaskAsCompleted(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", 1);
        db.update("tasks", values, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tasks", "id=?", new String[]{String.valueOf(id)});
    }
}
