package com.example.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText, taskDescriptionEditText, taskDeadlineEditText;
    private Button saveTaskButton;
    private TaskDatabaseHelper databaseHelper;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskNameEditText = findViewById(R.id.editTaskName);
        taskDescriptionEditText = findViewById(R.id.editTaskDescription);
        taskDeadlineEditText = findViewById(R.id.deadlineDate);
        saveTaskButton = findViewById(R.id.saveTaskButton);

        databaseHelper = new TaskDatabaseHelper(this);

        saveTaskButton.setOnClickListener(v -> {
            String taskName = taskNameEditText.getText().toString();
            String taskDescription = taskDescriptionEditText.getText().toString();
            String taskDeadline = taskDeadlineEditText.getText().toString();

            if (!taskName.isEmpty() && !taskDescription.isEmpty() && !taskDeadline.isEmpty()) {
                Task newTask = new Task(taskName, taskDescription, taskDeadline, false);
                databaseHelper.addTask(newTask);
                finish();  // Close the AddTaskActivity and return to previous activity
            }
        });
    }
}
