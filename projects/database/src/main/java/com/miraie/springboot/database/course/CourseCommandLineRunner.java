package com.miraie.springboot.database.course;

import com.miraie.springboot.database.course.jpa.CourseJpaRepository;
import com.miraie.springboot.database.course.springDataJpa.CourseSDJRepository;
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
//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSDJRepository repository;

    @Override
    public void run (String... arg) {
        /*
        //Spring JDBC
        repository.insertStatic();
        repository.insertDynamic(new Course(2, "Real Time Streaming", "Ververica"));
        repository.insertDynamic(new Course(3, "Data Driven Project", "Confluent"));
        repository.insertDynamic(new Course(4, "Web Services with Golang", "Google"));
         */
        /*
        // JPA
        repository.insert(new Course(1, "Algorithm", "Princeton"));
        repository.insert(new Course(2, "Real Time Streaming", "Ververica"));
        repository.insert(new Course(3, "Data Driven Project", "Confluent"));
        repository.insert(new Course(4, "Web Services with Golang", "Google"));
         */

        repository.save(new Course(1, "Algorithm", "Princeton"));
        repository.save(new Course(2, "Real Time Streaming", "Ververica"));
        repository.save(new Course(3, "Data Driven Project", "Confluent"));
        repository.save(new Course(4, "Web Services with Golang", "Google"));
        repository.deleteById(1L);
        System.out.println(repository.findById(2L));
        System.out.println(repository.findById(3L));
        System.out.println(repository.findAll());
        System.out.println(repository.count());


        System.out.println(repository.findByAuthor("Ververica"));
        System.out.println(repository.findByAuthor("Confluent"));
    }

}
