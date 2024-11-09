package com.example.exam;

public class Task {
    private int id;
    private String taskName;
    private String taskDescription;
    private String taskDeadline;
    private boolean isCompleted;

    public Task(int id, String taskName, String taskDescription, String taskDeadline, boolean isCompleted) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
