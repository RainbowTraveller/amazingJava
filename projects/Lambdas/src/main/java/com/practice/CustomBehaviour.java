/*
 * Print method from LauncherJava8 converted to more generic one which accepts a
 * behaviour as well
 */
package com.practice;

import java.util.List;
import java.util.Arrays;
import com.practice.Person;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class CustomBehaviour {

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
        performConditionally(people, p -> true, p -> System.out.println(p));

        Collections.sort(people, (p1, p2) -> p1.getFirstName().compareToIgnoreCase(p2.getFirstName()));
        System.out.println("Printing Sorted by first name");
        performConditionally(people, p -> true, p-> System.out.println(p));

        System.out.println("Printing Surname Starting with G");
        performConditionally( people, p -> p.getLastName().startsWith("G"),p-> System.out.println(p));
    }

    public static void performConditionally(List<Person> people, Predicate<Person> c, Consumer<Person> con) {
        for(Person p : people) {
            if(c.test(p)) {
                con.accept(p);
            }
        }
    }
}
