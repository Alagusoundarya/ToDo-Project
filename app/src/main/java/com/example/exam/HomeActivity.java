package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText taskNameEditText, descriptionEditText, deadlineEditText;
    private Button submitButton;
    private TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        taskDatabaseHelper = new TaskDatabaseHelper(this);

        taskNameEditText = findViewById(R.id.taskNameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        deadlineEditText = findViewById(R.id.deadlineEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String taskName = taskNameEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String deadline = deadlineEditText.getText().toString();

            if (!taskName.isEmpty() && !deadline.isEmpty()) {
                taskDatabaseHelper.addTask(taskName, description, deadline);
                Toast.makeText(HomeActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void navigateToHistory(View view) {
        startActivity(new Intent(this, HistoryTaskActivity.class));
    }
}
