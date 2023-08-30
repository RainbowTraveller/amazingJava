package com.miraie.springboot.startup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> getAllCourses() {
        return Arrays.asList(new Course(1, "Algirithm", "Princeton"),
                new Course(2, "TimeManagement", "RiseSmart"),
                new Course(3, "Apache Flink", "Ververica"),
                new Course(4, "Apache Tomcat", "BEAWebLogic"),
                new Course(5, "MongoDB", "MongoDB Inc"),
                new Course(6, "Large Scale Systems", "David Willey"));
    }
}
