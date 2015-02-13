package com.crystalsoft.employeemanagement;

import java.util.Comparator;
import java.util.Scanner;

/**
 * Describes the employee details to be handled 
 */
class Employee {

        /**
         * First name of the employee
         */
        private String firstName;
        /**
         * idetification number of the employee
         */
        private int id;
        /**
         * last name of the employee
         */
        private String lastName;
        /**
         * designation of the employee
         */
        private String position;
        /**
         * The project employee is working on
         */
        private String project;

       /**
        * Default Constructor
        */
       public Employee () {
           //This is to not have NullPointerException in equals method, unless
           //some unexpected scenario happens
            this.firstName = "";
            this.lastName =  "";
            this.id = 0;
            this.position =  "";
            this.project =  "";
       }

       /**
        * Parametarized Constructor
        *
        * @param firstName first name of this employee
        * @param lastName last name of this employee
        * @param id identification number for this employee
        * @param position designation of the employee
        * @param project the department this employee is working for
        */
       public Employee(String firstName, String lastName, int id, String position, String project) {
           //TO DO : remove id, calculate it automatically in Rolodex
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
            this.position = position;
            this.project = project;
       }

       /**
        * Overriding equals method
        */
       public boolean equals(Object other) {
           if(other instanceof Employee){
               Employee e = (Employee)other;
               return ( e.getFirstName().equals(this.firstName) && e.getLastName().equals(this.lastName)
                       && (e.getId() == this.id) && e.getPosition().equals(this.position) 
                       && this.getProject().equals(this.project));
           } 
           return false;
       }

       /**
        * Fetches the first name of this employee
        * @return String first name of this employee
        */
       public String getFirstName() {
            return firstName; 
       }

       /**
        * Fetches the identification number of this employee
        * @return int identification number of this employee
        */
       public int getId() {
            return id;
       }

       /**
        * Fetches the last name of this employee
        * @return String last name of this employee
        */
       public String getLastName() {
            return lastName;
       }

       /**
        * Fetches the designation of this employee
        * @return String designation of this employee
        */
       public String getPosition( ) {
            return position;
       }

       /**
        * Fetches the working project of this employee
        * @return String project this employee is working on
        */
       public String getProject() {
            return project;
       }

       /**
        * Assigns  first name to this employee
        * @param firstName first name of this employee
        */
       public void setFirstName(String firstName) {
            this.firstName = firstName;
       }

       /**
        * Assigns identification number  to this employee
        * @param id identification number of this employee
        */
       public void setId(int id) {
            this.id = id;
       }

       /**
        * Assigns last name to this employee
        * @param lastName last name of this employee
        */
       public void setLastName(String lastName) {
            this.lastName = lastName;
       }

       /**
        * Assigns designation to this employee
        * @param position designation of this employee
        */
       public void setPosition(String position) {
         this.position = position;
       }

       /**
        * Assigns project to this employee
        * @param project project of this employee
        */
       public void setProject(String project) {
           this.project = project;
       }

       /**
        * Returns the string representation of this employee
        * @return String textual representation of this employee
        */
       public String toString() {
            return "Name:" + this.firstName + "\n" + "Last Name:" + this.lastName + "\n" +
                "Employee id:" + this.id + "\n" + "Position:" + this.position + "\n" + "Project:" + this.project + "\n\n";
       }

       /**
        * Collects the information of employee and returns an employee object
        * based on  the information
        * @return Employee object built based on entered information 
        */
       public static Employee getEmployeeInfo() {
            System.out.println("Please enter the information about the employee");
            Scanner scanIn = new Scanner(System.in);
            System.out.println("Enter First Name:");
            String firstname = scanIn.nextLine();
            System.out.println("Enter Last Name:");
            String lastname = scanIn.nextLine();
            System.out.println("Enter Id:");
            int id = Integer.valueOf(scanIn.nextLine());
            System.out.println("Enter the position:");
            String position = scanIn.nextLine();
            System.out.println("Enter the project:");
            String project = scanIn.nextLine();
            return new Employee(firstname, lastname, id, position, project); 
       }

      /**
       * Internal class representing the comparator based on employee
       * identification number 
       */
       static class IdSort implements Comparator<Employee> {
           /**
            * Returns 0 if idetifications numbers for the two
            * employees are same 
            * Returns -1 if id of employee 1 is less than id of employee 2
            * Returns 1 if id of employee 1 is greater than id of employee 2
            * @param one the object of class employee to be compared 
            * @param two the object of class employee to be compared 
            */
           public int compare(Employee one, Employee two) {
               return Integer.valueOf(one.getId()).compareTo(two.getId());
           }
       }
       
      /**
       * Internal class representing the comparator based on employee
       * name 
       */
       static class NameSort implements Comparator<Employee> {
           /**
            * Returns 0 if idetifications numbers for the two
            * employees are same 
            * Returns -1 if id of employee 1 is less than id of employee 2
            * Returns 1 if id of employee 1 is greater than id of employee 2
            * @param one the object of class employee to be compared 
            * @param two the object of class employee to be compared 
            */
           public int compare(Employee one, Employee two) {
               int firstNameCompare = one.getFirstName().compareTo(two.getFirstName());
               return firstNameCompare != 0 ? firstNameCompare :(one.getLastName().compareTo(two.getLastName())); 
           }
       }
}

