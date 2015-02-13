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
 * Unit test for DeletionImpl Class
 */
public class DeletionTest {
    
    private Rolodex rdx;
    private Employee emp;
    private AdditionImpl ai;
    private DeletionImpl di;

    @Test
    public void testDelete() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        ai = new AdditionImpl();
        ai.add(rdx, emp);
        di = new DeletionImpl();
        di.delete(rdx, emp);
        assertNotSame(1, rdx.getEmployees().size());
        assertEquals(0, rdx.getEmployees().size());
    }

    @Test
    public void testDeleteFail() throws Exception {
            
        rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);

        ai = new AdditionImpl();
        ai.add(rdx, emp);
        di = new DeletionImpl();
        di.delete(rdx, emp);
        try{
            di.delete(rdx, emp);
        } catch (Exception e) {
            assertEquals(true, e instanceof EmployeeNotFoundException);  
        }
    }
}
