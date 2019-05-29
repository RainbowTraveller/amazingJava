package com.practice;

import java.util.List;
import java.util.Arrays;
import com.practice.Person;
import java.util.Comparator;
import java.util.Collections;

public class LauncherJava7 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Sachin", "Tendulakar", 40),
                new Person("Saurav", "Ganguly", 43),
                new Person("Rahul", "Dravid", 43),
                new Person("Sunil", "Gavaskar", 60)
            );


        //Sorting the list by lastname
        Comparator<Person> lastnameComparator = new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.getLastName().compareToIgnoreCase(p2.getLastName());
            }
        };

        Collections.sort(people, lastnameComparator);
        printList(people);
        printList(people, new Condition() {
                public boolean test(Person p) {
                    return p.getLastName().startsWith("G");
                }
                });
    }

    public static void printList(List<Person> people) {
        System.out.println("Sorted by LastName " + people);
    }

    public static void printList(List<Person> people, Condition c) {
        for(Person p : people) {
            if(c.test(p)) {
                System.out.println("Person with last name beginning with G : " + p);
            }
        }
    }

}
interface Condition {
    public boolean test(Person p);
}
