package com.example.exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> taskList;

    // Constructor to accept the list of tasks
    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        // Bind data to the views
        holder.taskNameTextView.setText(task.getName());
        holder.taskDescriptionTextView.setText(task.getDescription());
        // Optional: set the task deadline if needed
        holder.taskDeadlineTextView.setText(String.valueOf(task.getDeadline()));  // assuming deadline is a long value
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        // Define references to the views in the task item layout
        public TextView taskNameTextView;
        public TextView taskDescriptionTextView;
        public TextView taskDeadlineTextView;

        public TaskViewHolder(View view) {
            super(view);
            // Initialize the view references
            taskNameTextView = view.findViewById(R.id.taskName);
            taskDescriptionTextView = view.findViewById(R.id.taskDescription);
            taskDeadlineTextView = view.findViewById(R.id.taskDeadline);
        }
    }
}
