package com.practice;

import java.util.List;
import java.util.Arrays;
import com.practice.Person;

public class StreamSimpleExample {
    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Sachin", "Tendulakar", 40),
                new Person("Saurav", "Ganguly", 43),
                new Person("Rahul", "Dravid", 43),
                new Person("Sunil", "Gavaskar", 60)
            );

        people.stream()
            .filter(p -> p.getLastName().startsWith("G"))
            .forEach(p -> System.out.println(p.getFirstName()));
    }
}

