package com.crystalsoft.employeemanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.OperationsImpl;
/**
 * Contains the employee records and perform various operations on it
 */

public class Rolodex {

    /**
     * The container for the employee records
     */
    List<Employee> employees;

    /*
     * Reference to OperataionsImpl 
     */
    OperationsImpl op;
   /**
    * Default Constructor
    */
    public Rolodex() {
        // TO DO : check syschronized collection creation in ArrayList Javadoc
        employees = new ArrayList<Employee>();
    }

   /**
    * Constructor with external list of employees as parameter
    * @param empList List of employees to be assigned to this rolodex
    */
    public Rolodex(List<Employee> empList) {
        employees = empList; 
        this.sortById();
    }

    /**
     * Add a given employee in this rolodex
     * @param e object of Employee to be added
     */
    public void addEmployee(Employee e) {
        try {
            op = new AdditionImpl();
            op.add(this, e);
        } catch (DuplicateEmployeeException dee) {
            System.out.println("Employee Already Present: " + e);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Clear the list of employees in this rolodex 
     */
    public void clear() {
        this.employees.clear();
    }

    /**
     * Delete a given employee from this rolodex
     * @param e object of Employee to be deleted 
     */
    public void deleteEmployee(Employee e) {
        try {
            op = new DeletionImpl();
            op.delete(this, e);
        } catch (EmployeeNotFoundException dee) {
            System.out.println("Employee not  Present: " + e);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Find a given employee from this rolodex
     * @param e object of Employee to be searched 
     */
    public Employee find(final Employee emp) {
        return (Employee) CollectionUtils.find(this.employees, new Predicate() {
        public boolean evaluate(Object o) {
            return ((Employee)o == emp);
            }
        });
    }

    /**
     * Returns the list of employees in this rolodex
     * @return List<Employee> list containing the employees in this rolodex
     */
    public List<Employee> getEmployees() {
        return this.employees;
    }

    /**
     * Returns the number of employees in this rolodex
     * @return int number of  the employees in this rolodex
     */
    public int getSize() {
        return this.employees.size();
    }

    /**
     *  Load the rolodex from a file
     */
    public void loadEmployees(String filename, String path) {
        try {
            op = new LoadImpl(filename, path); 
            op.load(this);
        } catch (DuplicateEmployeeException dee) {
            System.out.println("Duplicate data");
            dee.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("IOException : Error in saving the data");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in saving the data");
            e.printStackTrace();
        }
    }

    /**
     * Modify detailsof a given employee
     */
    public void modifyEmployee(Employee emp) {
        try {
        op = new ModificationImpl(); 
        op.modify(this, emp);
        } catch (EmployeeNotFoundException enfe) {
            System.out.println("Employee Not Present: " + enfe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save all employee data to the file 
     */
    public void saveEmployees(String filename, String path) { 
        try {
            op = new SavingImpl(filename, path);
            op.save(this);
        } catch (IOException ioe) {
            System.out.println("IOException : Error in saving the data");
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Search a given employee in the rolodex
     */
    public Employee searchEmployee(Employee emp) {
        try {
            op = new SearchingImpl();
            emp = op.search(this, emp);
        } catch (EmployeeNotFoundException enfe) {
            System.out.println("Employee Not Present: " + enfe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    /**
     * Set the list of employees in this rolodex with externally supplied list
     * @param empList List of employees to be assigned to this rolodex
     */
    public void setEmployees(List<Employee> empList) {
        this.employees = empList;
        this.sortById();
    }

    /**
     * Sorts the list of employees by id number
     */
    void sortById() {
        Employee.IdSort idSort = new Employee.IdSort();
        if(this.employees != null) {
            Collections.sort(this.employees, idSort); 
        }
    }

    /**
     * Sorts the list of employees by First and Last name
     */
    private void sortByName() {
        Employee.NameSort nameSort = new Employee.NameSort();
        if(this.employees != null) {
            Collections.sort(this.employees, nameSort); 
        }
    }

   /**
    * Returns the string representation of this rolodex
    * @return String textual representation of this rolodex
    */
   public String toString() {
       String str = new String();
       for(Employee  e : this.employees){
           str += e.toString();
       }
       return str;
   }

    /**
     * Exception to be used when the employee in not found in the list of
     * rolodex
     */
    static class EmployeeNotFoundException extends Exception {
        public EmployeeNotFoundException() {
            super();
        }
        
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception to be used when the employee is already present in the list of
     * rolodex
     */
    static class DuplicateEmployeeException extends Exception {
        public DuplicateEmployeeException() {
            super();
        }
        
        public DuplicateEmployeeException(String message) {
            super(message);
        }
    }
}
