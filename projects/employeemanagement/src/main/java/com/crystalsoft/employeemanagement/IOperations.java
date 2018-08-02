package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Employee;

/**
 * Declares different operations performed on Rolodex
 */
public interface IOperations {

    /**
     * Adds the new employee to the rolodex 
     */
    void add(Rolodex rdx, Employee emp) throws Exception;
    /**
     * Deletes existing employee from the rolodex 
     */
    void delete(Rolodex rdx, Employee emp)throws Exception;
    /**
     * Modifies existing employee from the rolodex 
     */
    void modify(Rolodex rdx, Employee emp)throws Exception;
    /**
     * Save the entire rolodex to a file 
     */
    void save(Rolodex rdx)throws Exception;
    /**
     * Loads the entire the rolodex from a file 
     */
    void load(Rolodex rdx)throws Exception;
    /**
     * Search the a given employee in the rolodex 
     */
    Employee search(Rolodex rdx, Employee emp)throws Exception;
}
