package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.DuplicateEmployeeException;
import com.crystalsoft.employeemanagement.Rolodex.EmployeeNotFoundException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Unit test for Rolodex Class
 */
public class RolodexTest {

    private Rolodex rdx;
    private List<Employee> mylist; 
    private Employee emp1;
    private Employee emp2;

    @BeforeClass
    public void instantiate() throws Exception {
        rdx = new Rolodex();

        emp1 = new Employee();
        emp1.setFirstName("firstname");
        emp1.setLastName("lastname");
        emp1.setId(100);
        emp1.setPosition("developer");
        emp1.setProject("highway");

        emp2 = new Employee();
        emp2.setFirstName("fname");
        emp2.setLastName("lname");
        emp2.setId(200);
        emp2.setPosition("dev");
        emp2.setProject("high");

        mylist = new ArrayList<Employee>();
        mylist.add(emp1);
        mylist.add(emp2);
    }

    @Test
    public void checkInstantiate() throws Exception {
       assertNotSame(null, rdx.getEmployees());
    }

    @Test
    public void checkInstantiateWithParam() throws Exception {
       rdx = new Rolodex(mylist);
       assertNotSame(null, rdx.getEmployees());
       assertNotSame(0, rdx.getEmployees().size());
       assertEquals(2, rdx.getEmployees().size());
    }

    @Test
    public void testSetAndGetEmployees() throws Exception {
        rdx.setEmployees(null);
        assertEquals(null, rdx.getEmployees());
        mylist.clear();
        mylist.add(emp1);
        mylist.add(emp2);
        rdx.setEmployees(mylist);
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(0, rdx.getEmployees().size());
        assertEquals(2, rdx.getEmployees().size());
    }

    @Test
    public void testAddEmployee() throws Exception {
        rdx.clear();
        Employee emp3 = new Employee();
        emp3.setFirstName("Alex");
        emp3.setLastName("Particle");
        emp3.setId(400);
        emp3.setPosition("manager");
        emp3.setProject("Pipeline");
        rdx.addEmployee(emp3);
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(0, rdx.getEmployees().size());
        assertEquals(1, rdx.getEmployees().size());
    }

    @Test
    public void testAddEmployeefail() throws Exception {
        rdx.clear();
        Employee emp3 = new Employee();
        emp3.setFirstName("Alex");
        emp3.setLastName("Particle");
        emp3.setId(400);
        emp3.setPosition("manager");
        emp3.setProject("Pipeline");
        rdx.addEmployee(emp3);
        try {
            rdx.addEmployee(emp3);
        } catch (Exception e) {
            assertEquals(true, e instanceof DuplicateEmployeeException);  
        }
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        rdx.clear();
        rdx.addEmployee(emp1);
        rdx.deleteEmployee(emp1);
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(1, rdx.getEmployees().size());
        assertEquals(0, rdx.getEmployees().size());
    }
    
    @Test
    public void testDeleteEmployeeFail() throws Exception {
        rdx.clear();
        rdx.addEmployee(emp1);
        rdx.deleteEmployee(emp1);
        try {
            rdx.deleteEmployee(emp1);
        } catch (Exception e) {
            assertEquals(true, e instanceof EmployeeNotFoundException);  
        }
    }
    @Test
    public void testGetSize() throws Exception {
        rdx.addEmployee(new Employee("myfname", "mylname", 400, "tech", "casablanca"));
        rdx.clear();
        assertNotSame(null, rdx.getEmployees());
        assertNotSame(1, rdx.getEmployees().size());
        assertEquals(0, rdx.getEmployees().size());
    }
    
    @Test
    public void testSize() throws Exception {
       rdx.clear();
       assertEquals(0, rdx.getSize());
       rdx.addEmployee(emp1);
       rdx.addEmployee(emp2);
       assertEquals(2, rdx.getSize());
    }

    @Test
    public void testIdSort() throws Exception {
       rdx.clear();
       assertEquals(0, rdx.getSize());
       rdx.addEmployee(emp2);
       rdx.addEmployee(emp1);
       assertEquals(0, rdx.getEmployees().indexOf(emp1));
       assertEquals(1, rdx.getEmployees().indexOf(emp2));
    }

    @Test
    public void testfind() throws Exception {
       rdx.clear();
       assertEquals(0, rdx.getSize());
       rdx.addEmployee(emp2);
       rdx.addEmployee(emp1);
       assertEquals(emp1, rdx.find(emp1));
       assertEquals(emp2, rdx.find(emp2));
    }

    @Test
    public void testfindfail() throws Exception {
       rdx.clear();
       assertEquals(0, rdx.getSize());
       try {
            rdx.find(emp2);
       } catch (Exception e) {
           assertEquals(true, e instanceof EmployeeNotFoundException);  
       }
    }
}

