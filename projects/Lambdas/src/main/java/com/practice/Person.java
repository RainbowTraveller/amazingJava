package com.practice;

/**
 * Hello world!
 *
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person( String firstName, String lastName, int age ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public void setFirstName( String fname ) {
        this.firstName = fname;
    }

    public void setLastName( String lname ) {
        this.lastName = lname;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }


    public String toString(  ) {
        return "Person : age " + this.age + " First Name : " + this.firstName + " Last Name : " + this.lastName;
    }
}
