package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.DuplicateEmployeeException;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;

import java.util.Scanner;

public class Management {
    private static String filename = "RolodexData";
    private static String filelocation = "/home/milind/WFMilind/amazingJava/projects/employeemanagement/src/test/resources/";

    public static void main(String[] args) {
        Rolodex rdx = new Rolodex();
        Employee emp;

        System.out.println("1.Add Employee");
        System.out.println("2.Delete Employee");
        System.out.println("3.Search Employee");
        System.out.println("4.Modify Employee");
        System.out.println("5.Load Rolodex from file");
        System.out.println("6.Save Rolodex to file");
        System.out.println("7.Display Employee List");
        System.out.println("8.Exit");

        while(true) {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    emp = Employee.getEmployeeInfo();
                    rdx.addEmployee(emp);
                    break;
                case 2:
                    emp = Employee.getEmployeeInfo();
                    rdx.deleteEmployee(emp);
                    break;
                case 3:
                    emp = Employee.getEmployeeInfo();
                    System.out.println("The Employee Found: ");
                    System.out.println(rdx.searchEmployee(emp));
                    break;
                case 4:
                    emp = Employee.getEmployeeInfo();
                    rdx.modifyEmployee(emp);
                    break;
                case 5:
                    rdx.loadEmployees(filename, filelocation);
                    break;
                case 6:
                    rdx.saveEmployees(filename, filelocation);
                    break;
                case 7:
                    System.out.println(rdx); 
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not a valid choice");
            }
        }
    }
}

