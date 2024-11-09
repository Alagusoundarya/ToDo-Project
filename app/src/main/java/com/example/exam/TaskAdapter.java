package com.example.exam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> taskList;
    private Context context;
    private TaskDatabaseHelper taskDatabaseHelper;

    public TaskAdapter(ArrayList<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
        taskDatabaseHelper = new TaskDatabaseHelper(context);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskDeadline.setText(task.getDeadline());

        // Edit task
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("TASK_ID", task.getId());
            context.startActivity(intent);
        });

        // Mark as completed
        holder.markCompletedButton.setOnClickListener(v -> {
            taskDatabaseHelper.markTaskAsCompleted(task.getId());
            taskList.remove(position);
            notifyItemRemoved(position);
        });

        // Delete task
        holder.deleteButton.setOnClickListener(v -> {
            taskDatabaseHelper.deleteTask(task.getId());
            taskList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, taskDeadline;
        Button editButton, markCompletedButton, deleteButton;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskDeadline = itemView.findViewById(R.id.taskDeadline);
            editButton = itemView.findViewById(R.id.editButton);
            markCompletedButton = itemView.findViewById(R.id.markCompletedButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
