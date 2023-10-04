package com.miraie.springboot.webapp.scheduler.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    public List<Task> getByUserName (String userName);
}
