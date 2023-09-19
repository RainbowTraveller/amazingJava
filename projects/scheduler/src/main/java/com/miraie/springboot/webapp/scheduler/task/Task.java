package com.miraie.springboot.webapp.scheduler.task;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Task {
    private int id;
    private String userName;
    @Size(min = 10, message = "Please enter min 10 character message")
    private String description;
    private LocalDate endDate;
    private boolean done;

    public Task(int id, String userName, String description, LocalDate endDate, boolean done) {
        this.id = id;
        this.userName = userName;
        this.description = description;
        this.endDate = endDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", endDate=" + endDate +
                ", done=" + done +
                '}';
    }
}
