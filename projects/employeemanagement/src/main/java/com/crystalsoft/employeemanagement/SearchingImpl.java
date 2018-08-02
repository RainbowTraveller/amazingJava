package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;
import com.crystalsoft.employeemanagement.OperationsImpl;

public class SearchingImpl extends OperationsImpl {
    public Employee search (Rolodex rdx, Employee e) throws EmployeeNotFoundException {
        Employee emp = rdx.find(e);
        if (null == emp) {
            throw new EmployeeNotFoundException("Employee not found:" + e.toString());
        }
        return emp;
    }
}

