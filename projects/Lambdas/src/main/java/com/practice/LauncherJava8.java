package com.practice;

import java.util.List;
import java.util.Arrays;
import com.practice.Person;
import java.util.Comparator;
import java.util.Collections;
import java.util.function.Predicate;

public class LauncherJava8 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Sachin", "Tendulakar", 40),
                new Person("Saurav", "Ganguly", 43),
                new Person("Rahul", "Dravid", 43),
                new Person("Sunil", "Gavaskar", 60)
            );


        Collections.sort(people, (p1, p2) -> p1.getLastName().compareToIgnoreCase( p2.getLastName()));
        System.out.println("Printing Sorted by last name");
        printList(people, p -> true);

        Collections.sort(people, (p1, p2) -> p1.getFirstName().compareToIgnoreCase(p2.getFirstName()));
        System.out.println("Printing Sorted by first name");
        printList(people, p -> true);

        System.out.println("Printing Surname Starting with G");
        printList( people, p -> p.getLastName().startsWith("G") );
    }

    public static void printList(List<Person> people, Predicate<Person> c) {
        for(Person p : people) {
            if(c.test(p)) {
                System.out.println(" : " + p + " : ");
            }
        }
    }
}
