package com.miraie.springboot.database.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
This is class
 */
@Component
public class CourseCommandLinerunner implements CommandLineRunner {

    @Autowired
    private CourseRepository repository;

    @Override
    public void run (String... arg) {
        repository.insertStatic();
        repository.insertDynamic(new Course(2, "Real Time Streaming", "Ververica"));
        repository.insertDynamic(new Course(3, "Data Driven Project", "Confluent"));
        repository.insertDynamic(new Course(4, "Web Services with Golang", "Google"));

        repository.deleteById(1);

        System.out.println(repository.findById(2));
        System.out.println(repository.findById(3));
    }

}
