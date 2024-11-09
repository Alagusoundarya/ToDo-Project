package com.example.exam;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historyRecyclerView;
    private TaskAdapter historyAdapter;
    private ArrayList<Task> completedTaskList;
    private TaskDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);  // Make sure your layout file is named correctly

        // Initialize the RecyclerView and TaskAdapter
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the database helper
        databaseHelper = new TaskDatabaseHelper(this);

        // Fetch completed tasks from the database
        completedTaskList = databaseHelper.getCompletedTasks();

        if (completedTaskList != null && !completedTaskList.isEmpty()) {
            // Set up the adapter with the completed tasks
            historyAdapter = new TaskAdapter(completedTaskList, this);
            historyRecyclerView.setAdapter(historyAdapter);
        } else {
            Toast.makeText(this, "No completed tasks", Toast.LENGTH_SHORT).show();
        }
    }
}
