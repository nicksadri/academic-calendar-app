package com.example.test;

public class Task extends Event {
    private String taskTitle;
    private boolean completeness;

    public Task(int year, int month, int day, String courseName, String taskTitle, boolean completeness) {
        super(year, month, day, courseName);
        this.taskTitle = taskTitle;
        this.completeness = completeness;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public boolean getCompleteness() {
        return completeness;
    }
}
