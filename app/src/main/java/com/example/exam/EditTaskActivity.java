package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText, taskDescriptionEditText, taskDeadlineEditText;
    private Button saveTaskButton;
    private int taskId;
    private TaskDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_activity);

        taskNameEditText = findViewById(R.id.editTaskName);
        taskDescriptionEditText = findViewById(R.id.editTaskDescription);
        taskDeadlineEditText = findViewById(R.id.editTaskDeadline);
        saveTaskButton = findViewById(R.id.saveTaskButton);

        databaseHelper = new TaskDatabaseHelper(this);

        // Get the task ID passed from the previous activity
        taskId = getIntent().getIntExtra("TASK_ID", -1);

        // Load the task data
        Task task = databaseHelper.getTaskById(taskId);
        if (task != null) {
            taskNameEditText.setText(task.getTaskName());
            taskDescriptionEditText.setText(task.getTaskDescription());
            taskDeadlineEditText.setText(task.getTaskDeadline());
        }

        saveTaskButton.setOnClickListener(v -> {
            String taskName = taskNameEditText.getText().toString();
            String taskDescription = taskDescriptionEditText.getText().toString();
            String taskDeadline = taskDeadlineEditText.getText().toString();

            if (!taskName.isEmpty() && !taskDescription.isEmpty() && !taskDeadline.isEmpty()) {
                // Update the task in the database
                databaseHelper.updateTask(taskId, taskName, taskDescription, taskDeadline);
                finish();  // Close the activity and return to the previous screen
            }
        });
    }
}
