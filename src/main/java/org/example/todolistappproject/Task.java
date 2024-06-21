package org.example.todolistappproject;

import java.time.LocalDate;

public class Task {
    private String description;
    private boolean completed;
    private String priority;
    private LocalDate deadline;
    private String category;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, String priority, LocalDate deadline, String category) {
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.category = category;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return description + " (" + priority + ") - " + deadline + " [" + category + "]" + (completed ? " [Completed]" : "");
    }
}
