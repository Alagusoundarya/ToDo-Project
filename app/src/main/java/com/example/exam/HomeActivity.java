package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText taskNameEditText, taskDescEditText, taskDeadlineEditText;
    private Button addTaskButton;
    private TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        taskNameEditText = findViewById(R.id.taskNameEditText);
        taskDescEditText = findViewById(R.id.taskDescEditText);
        taskDeadlineEditText = findViewById(R.id.taskDeadlineEditText);
        addTaskButton = findViewById(R.id.addTaskButton);

        // Initialize Database Helper
        taskDatabaseHelper = new TaskDatabaseHelper(this);

        // Button click listener to add task
        addTaskButton.setOnClickListener(v -> {
            String taskName = taskNameEditText.getText().toString();
            String taskDesc = taskDescEditText.getText().toString();
            String taskDeadline = taskDeadlineEditText.getText().toString();

            if (!taskName.isEmpty() && !taskDesc.isEmpty() && !taskDeadline.isEmpty()) {
                taskDatabaseHelper.addTask(taskName, taskDesc, taskDeadline);
                Toast.makeText(this, "Task Added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Go to History Activity to see completed tasks
        findViewById(R.id.historyButton).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
