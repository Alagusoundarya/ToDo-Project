package com.example.exam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
    private int id;
    private String name;
    private String description;
    private long deadline;
    private boolean completed;

    public Task(int id, String name, String description, long deadline, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public long getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }

    public String getFormattedDeadline() {
        Date date = new Date(deadline);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(date);
    }
}
