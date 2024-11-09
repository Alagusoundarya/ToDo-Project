package com.example.exam;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historyRecyclerView;
    private TaskAdapter historyAdapter;
    private TaskDatabaseHelper taskDatabaseHelper;
    private List<Task> completedTaskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        taskDatabaseHelper = new TaskDatabaseHelper(this);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);

        loadCompletedTasks();

        historyAdapter = new TaskAdapter(completedTaskList);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(historyAdapter);
    }

    private void loadCompletedTasks() {
        Cursor cursor = taskDatabaseHelper.getCompletedTasks();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String taskName = cursor.getString(cursor.getColumnIndex("task_name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") String deadline = cursor.getString(cursor.getColumnIndex("deadline"));
                Task task = new Task(id, taskName, description, deadline, true);
                completedTaskList.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No completed tasks", Toast.LENGTH_SHORT).show();
        }
    }
}
