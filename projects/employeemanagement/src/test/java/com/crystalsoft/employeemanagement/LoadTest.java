package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.IOperations;
import com.crystalsoft.employeemanagement.SavingImpl;
import com.crystalsoft.employeemanagement.LoadImpl;

import java.nio.file.Files;
import java.nio.file.Path; 
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Unit test for LoadImpl Class
 */
public class LoadTest {
    List<Employee> empList; 
    IOperations iop; 
    Rolodex rdx;
    String fileName = "RolodexData";
    String fileLocation = "/home/milind/WFMilind/amazingJava/projects/employeemanagement/src/test/resources/";

    @BeforeClass
    public void instantiate(){
        iop = new SavingImpl(fileLocation, fileName);
        empList = new ArrayList<Employee>();
        empList.add(new Employee("Neo","Anderson",1,"CEO","Company"));
        empList.add(new Employee("Neal","Armstrong",2,"TechLead","Critical"));
        empList.add(new Employee("Kalpana","Chawla",3,"Manager","Sustainable"));
        empList.add(new Employee("Rakesh","Sharma",4,"Developer","Ongoing"));
        rdx = new Rolodex(empList);
    }

    @Test
    public void testLoad() throws Exception {
        iop.save(rdx);
        Path path = FileSystems.getDefault().getPath(fileLocation, fileName); 
        assertTrue(Files.exists(path));
        assertNotSame(0, Files.size(path));
        Rolodex lrdx = new Rolodex();
        assertEquals(0, lrdx.getSize());
        iop = new LoadImpl(fileLocation, fileName);
        iop.load(lrdx);
        assertEquals(4, lrdx.getSize());
        System.out.println(lrdx);
    }
}
