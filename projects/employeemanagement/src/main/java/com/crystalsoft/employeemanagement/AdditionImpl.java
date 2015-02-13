package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.DuplicateEmployeeException;
import com.crystalsoft.employeemanagement.OperationsImpl;

import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdditionImpl extends OperationsImpl {

    public void add(Rolodex rdx, Employee e) throws DuplicateEmployeeException {
        //Main will call first getEmployeeInfo from Emplooyee and then call add: for unit
        //testing
        if (null == rdx.find(e)) {
            rdx.employees.add(e);
            rdx.sortById();
        } else {
            throw new DuplicateEmployeeException("Employee record already exists\n" + e);
        }
    }
}


