// HistoryTaskActivity.java
package com.example.exam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryTaskActivity extends AppCompatActivity {

    private TaskDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);

        databaseHelper = new TaskDatabaseHelper(this);
        RecyclerView historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Task> completedTasks = databaseHelper.getTasks(true); // Get completed tasks
        TaskAdapter adapter = new TaskAdapter(completedTasks, null); // No mark as completed needed
        historyRecyclerView.setAdapter(adapter);
    }
}
