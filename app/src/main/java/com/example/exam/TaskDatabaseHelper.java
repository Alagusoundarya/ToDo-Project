package com.example.exam;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDatabaseHelper extends SQLiteOpenHelper {

    // Define table and columns
    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DEADLINE = "deadline";
    private static final String COLUMN_IS_COMPLETED = "is_completed";

    // Constructor
    public TaskDatabaseHelper(Context context) {
        super(context, "tasks.db", null, 1); // Database name and version
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL to create the tasks table
        String createTableQuery = "CREATE TABLE " + TABLE_TASKS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_DEADLINE + " TEXT, "
                + COLUMN_IS_COMPLETED + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade database if necessary
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    // Add a new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        values.put(COLUMN_DEADLINE, task.getDeadline());
        values.put(COLUMN_IS_COMPLETED, task.isCompleted() ? 1 : 0);

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    // Get all completed tasks
    public ArrayList<Task> getCompletedTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Task> completedTasks = new ArrayList<>();

        // Query to get tasks that are marked as completed
        String query = "SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_IS_COMPLETED + " = 1";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Get task details
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") String deadline = cursor.getString(cursor.getColumnIndex(COLUMN_DEADLINE));
                @SuppressLint("Range") boolean isCompleted = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_COMPLETED)) == 1;

                // Create task object and add to list
                Task task = new Task(id, name, description, deadline, isCompleted);
                completedTasks.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return completedTasks;
    }

    // Other methods like getTaskById(), updateTask(), etc. can go here
}
