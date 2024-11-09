package com.example.exam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView historyRecyclerView;
    private TaskAdapter taskAdapter;
    private TaskDatabaseHelper taskDatabaseHelper;
    private ArrayList<Task> completedTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialize the RecyclerView and TaskDatabaseHelper
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskDatabaseHelper = new TaskDatabaseHelper(this);

        // Fetch completed tasks from the database
        completedTasks = taskDatabaseHelper.getCompletedTasks();

        // Set up the adapter with the completed tasks
        taskAdapter = new TaskAdapter(completedTasks);
        historyRecyclerView.setAdapter(taskAdapter);
    }
}
