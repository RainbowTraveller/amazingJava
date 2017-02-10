package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.IOperations;

import java.util.Scanner;

abstract class OperationsImpl implements IOperations {

    /**
     * Adds the new employee to the rolodex 
     */
    public void add(Rolodex rdx, Employee emp) throws Exception {
     }
    /**
     * Deletes existing employee from the rolodex
     */
    public void delete(Rolodex rdx, Employee emp) throws Exception {
    } 
    /**
     * Modifies existing employee from the rolodex 
     */
    public void modify(Rolodex rdx, Employee emp) throws Exception {
    }
    /**
     * Save the entire rolodex to a file 
     */
    public void save(Rolodex rdx) throws Exception {
    }
    /**
     * Loads the entire the rolodex from a file 
     */
    public void load(Rolodex rdx) throws Exception {
    } 
    /**
     * Search the a given employee in the rolodex 
     */
    public Employee search(Rolodex rdx, Employee emp) throws Exception {
        return null;
    }
}
