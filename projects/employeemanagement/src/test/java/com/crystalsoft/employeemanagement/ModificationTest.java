package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.IOperations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Unit test for ModificationImpl Class
 */
public class ModificationTest {
    
    private Rolodex rdx;
    private Employee emp;
    private ModificationImpl mi;

    @Test
    public void testModify() throws Exception {
            
       /* rdx = new Rolodex();

        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);
        
        AdditionImpl ai = new AdditionImpl();
        ai.add(rdx, emp);

        mi = new ModificationImpl();
        mi.modify(rdx, emp);
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(0, rdx.getEmployees().size());
        assertEquals(1, rdx.getEmployees().size());
        
        Employee femp = new Employee("lastname", "firsname", 100, "", "");
        assertEquals(femp, rdx.find(femp));
        TO DO : Try mocking or remove user input thing to some other place 
        http://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input#comment18332481_6415728
        */
    }
}
