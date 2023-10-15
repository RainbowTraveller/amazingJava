package com.practice.testing;

import org.junit.jupiter.api.*;

public class BeforAndAfterTest {
    /*
        BeforAll and AfterAll methods are to be executed as class level
        these set up for all tests and tear down after all tests
        so make them class level by marking static
     */
    @BeforeAll
    public static void methodToBeExecutedBeforeAll () {
        System.out.println("Before All");
    }
    @BeforeEach
    public void methodToBeExecutedBeforeEachTest() {
        System.out.println("Before Each");
    }
    @Test
    public void beforeAndAfterTest1() {
        System.out.println("Test 1");

    }
    @Test
    public void beforeAndAfterTest2() {
        System.out.println("Test 2");

    }
    @Test
    public void beforeAndAfterTest3() {
        System.out.println("Test 3");
    }
    @AfterEach
    public void methodToBeExecutedAfterEachTest() {
        System.out.println("After Each");
    }
    @AfterAll
    public static void methodToBeExecutedAfterAll () {
        System.out.println("After All");
    }
}
