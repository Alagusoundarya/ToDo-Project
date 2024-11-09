// AddTaskActivity.java
package com.example.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private TaskDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        databaseHelper = new TaskDatabaseHelper(this);

        EditText taskInput = findViewById(R.id.taskInput);
        Button saveTaskButton = findViewById(R.id.saveTaskButton);

        saveTaskButton.setOnClickListener(view -> {
            String taskName = taskInput.getText().toString().trim();
            if (!taskName.isEmpty()) {
                databaseHelper.addTask(new Task(taskName, false));
                Toast.makeText(this, "Task Added!", Toast.LENGTH_SHORT).show();
                finish(); // Return to HomeActivity
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
