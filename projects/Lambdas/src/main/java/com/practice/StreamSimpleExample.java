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

        //Get Last name starting with G and print them
        people.stream()
            .filter(p -> p.getLastName().startsWith("G"))
            .forEach(p -> System.out.println(p.getFirstName()));
        List<Integer> numbers = Arrays.asList(1, 2, 3 ,4 ,5, 6, 7, 8,9 ,10);

        //Double the even numbers and return the sum
        System.out.println(
        numbers.stream()
            .filter( e -> e % 2 == 0 )
            .map( e -> e * 2 )
            .reduce(0, Integer::sum));

        System.out.println(
        numbers.stream()
            .filter( e -> e % 2 == 0 )
            .mapToInt( e -> e * 2 )
            .sum());


    }
}

