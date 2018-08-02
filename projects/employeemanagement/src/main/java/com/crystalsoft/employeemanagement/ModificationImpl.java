package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;
import com.crystalsoft.employeemanagement.OperationsImpl;

import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ModificationImpl extends OperationsImpl {

    public void modify(Rolodex rdx, Employee e) throws Exception {
        //Main called getEmployeeInfo : for unit testing purpose
        Employee emp = rdx.find(e);
        if (null == emp) {
            throw new EmployeeNotFoundException("Employee not found:" + e.toString());
        } else {
            System.out.println("Please enter the information about the employee to be Modified");
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
            if(!(null == firstname)) {
                emp.setFirstName(firstname);
            }
            if(!(null == lastname)) {
                emp.setLastName(lastname); 
            }
            if(!(null == position)) {
                emp.setPosition(position); 
            }
            if(!(null == project)) {
                emp.setProject(project); 
            }
        }
    }
}
