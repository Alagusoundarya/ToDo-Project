// HomeActivity.java
package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TaskAdapter adapter;
    private TaskDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseHelper = new TaskDatabaseHelper(this);
        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AddTaskActivity.class)));

        ImageButton historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryTaskActivity.class)));

        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = databaseHelper.getTasks(false); // Get incomplete tasks
        adapter = new TaskAdapter(tasks, task -> {
            task.setCompleted(true);
            databaseHelper.updateTask(task);
            loadTasks();
        });
        taskRecyclerView.setAdapter(adapter);
    }
}
