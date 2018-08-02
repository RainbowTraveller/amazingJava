package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.IOperations;
import com.crystalsoft.employeemanagement.Rolodex.DuplicateEmployeeException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Unit test for AdditionImpl Class
 */
public class AdditionTest {
    
    private Rolodex rdx;
    private Employee emp;
    private AdditionImpl ai;

    @Test
    public void testAdd() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        ai = new AdditionImpl();
        ai.add(rdx, emp);
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(0, rdx.getEmployees().size());
        assertEquals(1, rdx.getEmployees().size());
    }

    @Test
    public void testAddFail() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        ai = new AdditionImpl();
        ai.add(rdx, emp);
        try {
            ai.add(rdx, emp);
        } catch (Exception e) {
            assertEquals(true, e instanceof DuplicateEmployeeException);  
        }
    }
}
