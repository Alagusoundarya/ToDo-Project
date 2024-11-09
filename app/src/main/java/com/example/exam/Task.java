package com.example.exam;

public class Task {
    private int id;
    private String taskName;
    private String description;
    private String deadline;
    private boolean isCompleted;

    public Task(int id, String taskName, String description, String deadline, boolean isCompleted) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
