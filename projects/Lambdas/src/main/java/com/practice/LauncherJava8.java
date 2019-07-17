package com.practice;

import java.util.stream.Stream;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import com.practice.Person;


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

        //Demos from Tech Talk online
        functionComposition();
        declarativeFirst();
        iteratorDemo();
        higherOrder();
        favorImmutability();
        lambdasVsClosures();
        fucntionalNext();

    }

    public static void printList(List<Person> people, Predicate<Person> c) {
        for(Person p : people) {
            if(c.test(p)) {
                System.out.println(" : " + p + " : ");
            }
        }
    }

    public static void functionComposition() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);

        // Both imperative and functional perform same number of computations

        int result = 0;

        for (int e : numbers) {
            if (isGT3(e) && isEven(e)) {
                result = doubleit(e);
                break;
            }
        }

        System.out.println(result);

        System.out.println(numbers.stream() // lazy evaluation
                .filter(LauncherJava8::isGT3).filter(LauncherJava8::isEven).map(LauncherJava8::doubleit).findFirst().orElse(0));

        // filter, map, etc. are intermediate functions. They are not evaluated until a
        // terminal
        // function, like findFirst, is called.

        // The intermediate functions are fused together and executed as one chunk, but
        // minimally,
        // only as necessary. No extra work than needed.
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static boolean isGT3(int n) {
        return n > 4;
    }

    public static int doubleit(int n) {
        return n * 2;
    }

    public static void declarativeFirst() {

        // Before jumping to functional, it helps to thing declarative

        List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");

        // Imperative
        boolean found = false;

        for (String name : names) {
            if (name.equals("Nemo")) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Nemo was found");
        } else {
            System.out.println("Nemo not found");
        }

        // Declarative
        if (names.contains("Nemo")) {
            System.out.println("Nemo was found");
        } else {
            System.out.println("Nemo not found");
        }

        // focus on what instead of how
    }

    public static void iteratorDemo() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // External iterators

        // familiar and complex
        // for(int i = 0; i < numbers.size(); i++) {
        // System.out.println(numbers.get(i));
        // }

        // familiar and simpler than the previous one
        // for(int e : numbers) {
        // System.out.println(e);
        // }

        // Internal iterators
        // numbers.forEach(new Consumer<Integer>() {
        // public void accept(Integer value) {
        // System.out.println(value);
        // }
        // });

        // numbers.forEach((Integer value) -> System.out.println(value));

        // numbers.forEach((value) -> System.out.println(value));

        // numbers.forEach(value -> System.out.println(value));

        numbers.forEach(System.out::println);
    }

    public static void higherOrder() {
        // A method is a function that is attached to a class or object.
        // A function has four parts:
        // 1. name
        // 2. Return type
        // 3. Parameter list
        // 4. Body

        // Lambdas are anonymous functions whose type is inferred. They have two things:
        // (parameter list) -> body

        // Thread thread = new Thread(new Runnable() {
        // public void run() {
        // ;
        // }
        // });

        Thread thread = new Thread(() -> System.out.println("In another thread"));

        thread.start();

        System.out.println("In main thread");

        // We can pass objects to functions
        // We can create objects within functions
        // We can return objects from functions.

        // Higher order functions:
        // We can pass functions to functions
        // We can create functions within functions
        // We can return functions from functions.

    }

    public static void favorImmutability() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        int[] factor = new int[] { 2 };

        Stream<Integer> strm = numbers.stream().map(e -> e * factor[0]); // bad idea. Closure is not pure.

        // pure functions:
        // 1. does not mutate or change anything
        // 2. does not depend on anything that changes

        factor[0] = 0;

        strm.forEach(System.out::println);

        // at the least this code is confusing, hard to reason, and may lead to
        // misunderstanding and errors.

        // Keep lambdas and closures pure. Don't mutate shared state from within or from
        // outside.
    }

    public static void lambdasVsClosures() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        numbers.stream().map(e -> e * 2) // e is the parameter to the labda, much like args is the parameter to main
                .forEach(System.out::println);

        // lambda expresssions do not carry state

        int factor = 2; // this is effectively final, as good as being declared final

        numbers.stream().map(e -> e * factor) // this is a closure
                .forEach(System.out::println);

        // closures carry state. They close-over (hence closure) the defining scope to
        // bind to
        // variables that are not passed in as parameters. Here factor is closed over.

        // closures carry (immutable) state
    }

    public static void fucntionalNext() {
        // Imperative: what and how.
        // Declarative: what not how
        // Functional: Declarative + higher-order functions

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // find the total of sqrt of even numbers

        // Imperative:
        double result = 0;

        for (int e : numbers) {
            if (e % 2 == 0) {
                result += Math.sqrt(e);
            }
        }

        System.out.println(result);

        // Functional
        System.out.println(numbers.stream().filter(e -> e % 2 == 0) // functional (and declarative)
                .mapToDouble(Math::sqrt) // functional (and declarative)
                .sum());
    }
}
