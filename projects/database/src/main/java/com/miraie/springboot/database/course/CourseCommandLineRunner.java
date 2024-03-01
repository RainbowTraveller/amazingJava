package com.miraie.springboot.database.course;

import com.miraie.springboot.database.course.jpa.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
This is class which is called when the application runs, and it executes
code inside the run command
It plays around with some DB commands based on the corresponding repository instance
 */
@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    //    @Autowired
//    private CourseJdbcRepository repository;
    @Autowired
    private CourseJpaRepository repository;

    @Override
    public void run (String... arg) {
//        repository.insertStatic();
//        repository.insertDynamic(new Course(2, "Real Time Streaming", "Ververica"));
//        repository.insertDynamic(new Course(3, "Data Driven Project", "Confluent"));
//        repository.insertDynamic(new Course(4, "Web Services with Golang", "Google"));
//
        repository.insert(new Course(1, "Algorithm", "Princeton"));
        repository.insert(new Course(2, "Real Time Streaming", "Ververica"));
        repository.insert(new Course(3, "Data Driven Project", "Confluent"));
        repository.insert(new Course(4, "Web Services with Golang", "Google"));
        repository.deleteById(1);

        System.out.println(repository.findById(2));
        System.out.println(repository.findById(3));
    }

}
