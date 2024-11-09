package com.example.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskDatabaseHelper = new TaskDatabaseHelper(this);
        EditText nameEditText = findViewById(R.id.taskNameEditText);
        EditText deadlineEditText = findViewById(R.id.taskDeadlineEditText);
        Button submitButton = findViewById(R.id.submitTaskButton);

        submitButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            long deadline = Long.parseLong(deadlineEditText.getText().toString());

            taskDatabaseHelper.insertTask(name, "", deadline);
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
