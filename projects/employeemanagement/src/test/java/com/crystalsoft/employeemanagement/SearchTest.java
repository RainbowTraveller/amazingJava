package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.IOperations;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Unit test for SearchImpl Class
 */
public class SearchTest {
    
    private Rolodex rdx;
    private Employee emp;
    private AdditionImpl ai;
    private SearchingImpl si;

    @Test
    public void testSearch() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        ai = new AdditionImpl();
        ai.add(rdx, emp);
        si = new SearchingImpl();
        Employee e = si.search(rdx, emp);
        assertNotSame(null, e);
        assertEquals(e, emp);
    }

    @Test
    public void testSearchFail() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        si = new SearchingImpl();
        try {
             Employee e = si.search(rdx, emp);
        } catch (Exception e) {
            assertEquals(true, e instanceof EmployeeNotFoundException);  
        }
    }
}
