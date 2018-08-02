package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.IOperations;


abstract class Addition implements IOperations {

    /**
     * Adds the new employee to the rolodex 
     */
    public void add(Rolodex rdx);//Not implemented
    /**
     * Deletes existing employee from the rolodex 
     */
    public void delete(Rolodex rdx) {
    }
    /**
     * Modifies existing employee from the rolodex 
     */
    public void modify(Rolodex rdx) {
    }
    /**
     * Save the entire rolodex to a file 
     */
    public void save(Rolodex rdx) {
    }
    /**
     * Loads the entire the rolodex from a file 
     */
    public void load(Rolodex rdx) {
    }
    /**
     * Search the a given employee in the rolodex 
     */
    public void search(Rolodex rdx) {
    }

}
