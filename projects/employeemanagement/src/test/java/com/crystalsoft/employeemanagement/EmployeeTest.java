package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;

import java.lang.reflect.Method;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Unit test for Employee Class
 */
public class EmployeeTest
{
    private Employee emp;

    @BeforeClass
    public void instantiate(){
        emp = new Employee();
        emp.setFirstName("firstname");
        emp.setLastName("lastname");
        emp.setId(100);
        emp.setPosition("developer");
        emp.setProject("highway");
    }

    @Test
    public void checkInstance() {
       assertTrue(emp instanceof Employee);
    }

    @Test
    public void checkFirstName() {
       assertEquals(emp.getFirstName(), "firstname");
    }

    @Test
    public void checkLastName() {
       assertEquals(emp.getLastName(), "lastname");
    }

    @Test
    public void checkId(){
        assertEquals(emp.getId(), 100);
    }

    @Test
    public void checkPosition(){
        assertEquals(emp.getPosition(), "developer");
    }

    @Test
    public void checkProject(){
        assertEquals(emp.getProject(), "highway");
    }

    @Test
    public void checkIdSortEqual(){
        Employee.IdSort idComparator = new Employee.IdSort();
        Employee emp1 = emp;
        assertEquals(0, idComparator.compare(emp, emp1));
    }

    @Test
    public void checkIdSortNotEqual(){
        Employee.IdSort idComparator = new Employee.IdSort();
        Employee emp1 = new Employee();
        emp1.setId(200);
        assertNotSame(0, idComparator.compare(emp, emp1));
    }

    @Test
    public void checkNameSortEqual(){
        Employee.NameSort nameComparator = new Employee.NameSort();
        Employee emp1 = emp;
        assertEquals(0, nameComparator.compare(emp, emp1));
    }

    @Test
    public void checkNameSortNotEqual(){
        Employee.NameSort nameComparator = new Employee.NameSort();
        Employee emp1 = new Employee();
        emp1.setFirstName("Johny");
        emp1.setLastName("Walker");
        assertNotSame(0, nameComparator.compare(emp, emp1));
    }
}
