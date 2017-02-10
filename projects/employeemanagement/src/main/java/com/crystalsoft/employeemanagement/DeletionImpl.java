package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.OperationsImpl;
import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;

import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class DeletionImpl extends OperationsImpl {

    public void delete(Rolodex rdx, Employee e) throws EmployeeNotFoundException {
        //Main called getEmployeeInfo : for unit testing purpose
        Employee emp = rdx.find(e);
        if (null == emp) {
            throw new EmployeeNotFoundException("Employee not found:" + e.toString());
        } else {
                rdx.employees.remove(e);
        }
    }
} 
