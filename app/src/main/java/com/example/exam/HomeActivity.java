package com.example.exam;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskDatabaseHelper = new TaskDatabaseHelper(this);

        // Fetch pending tasks from the database
        ArrayList<Task> tasks = taskDatabaseHelper.getPendingTasks();

        // Set up RecyclerView with TaskAdapter
        taskAdapter = new TaskAdapter(tasks); // Pass the task list to the adapter
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(taskAdapter);

        // Add task button (floating action button)
        findViewById(R.id.addTaskButton).setOnClickListener(view -> {
            // Handle adding a new task (navigate to Add Task screen)
            Toast.makeText(HomeActivity.this, "Add Task clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
