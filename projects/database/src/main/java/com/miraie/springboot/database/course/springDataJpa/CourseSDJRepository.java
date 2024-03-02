package com.miraie.springboot.database.course.springDataJpa;

import com.miraie.springboot.database.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSDJRepository extends JpaRepository<Course, Long> {
    //Custom methods can be added too
    List<Course> findByAuthor (String author);

    List<Course> findByName (String name);

}
