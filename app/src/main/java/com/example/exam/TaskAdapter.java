// TaskAdapter.java
package com.example.exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final List<Task> taskList;
    private final OnTaskCompletedListener listener;

    public interface OnTaskCompletedListener {
        void onTaskCompleted(Task task);
    }

    public TaskAdapter(List<Task> taskList, OnTaskCompletedListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());

        if (listener != null) { // For tasks on the Home screen
            holder.completeButton.setVisibility(View.VISIBLE);
            holder.completeButton.setOnClickListener(v -> listener.onTaskCompleted(task));
        } else { // For tasks on the History screen
            holder.completeButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        Button completeButton;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            completeButton = itemView.findViewById(R.id.completeButton);
        }
    }
}
