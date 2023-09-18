package com.miraie.springboot.webapp.scheduler.task;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskService {

    private static List<Task> taskList = new LinkedList<>();

    private static int count = 0;
    static {
        taskList.add(new Task(++count, "milo", "Learning Go", LocalDate.now().plusMonths(6), false));
        taskList.add(new Task(++count, "pokoyo", "Learning C++", LocalDate.now().plusWeeks(6), false));
        taskList.add(new Task(++count, "milo", "Learning Leetcode", LocalDate.now().plusYears(1), false));
        taskList.add(new Task(++count, "guggul", "Learning REST", LocalDate.now().plusMonths(3), false));
    }

    public List<Task> getByUserName(String userName) {
        return taskList;
    }

    public void addTask(String userName, String description, LocalDate endDate, boolean status) {
        Task newTask = new Task(++count, userName, description, endDate, status);
        taskList.add(newTask);
    }
}