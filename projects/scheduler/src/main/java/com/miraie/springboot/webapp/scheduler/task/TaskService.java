package com.miraie.springboot.webapp.scheduler.task;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskService {

    private static List<Task> taskList = new LinkedList<>();

    static {
        taskList.add(new Task(1, "milo", "Learning Go", LocalDate.now().plusMonths(6), false));
        taskList.add(new Task(2, "pokoyo", "Learning C++", LocalDate.now().plusWeeks(6), false));
        taskList.add(new Task(3, "milo", "Learning Leetcode", LocalDate.now().plusYears(1), false));
        taskList.add(new Task(4, "guggul", "Learning REST", LocalDate.now().plusMonths(3), false));
    }

    public List<Task> getByUserName(String userName) {
        return taskList;
    }
}
