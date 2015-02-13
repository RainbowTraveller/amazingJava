package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.Rolodex.DuplicateEmployeeException;
import com.crystalsoft.employeemanagement.OperationsImpl;

import java.lang.Integer;
import java.lang.Iterable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoadImpl extends OperationsImpl {
    
    private String fileLocation;
    private String fileName;

    public LoadImpl(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
    }

    public void load(Rolodex rdx) throws Exception {
        File file = new File(this.fileLocation  + this.fileName);
        Scanner fileScanner = new Scanner(file);
        List<Object> data = new ArrayList<Object>(); 
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if(line.length() != 0) {
                int colonIndex = line.lastIndexOf(':');
                line = line.substring(colonIndex + 1);
                data.add(line);
            } else {
                //Using blank line as separator for two records
                Object [] dataArray = data.toArray();
                Employee emp = new Employee((String)dataArray[0], (String)dataArray[1],Integer.valueOf((String)dataArray[2]),(String)dataArray[3],(String)dataArray[4]); 
                rdx.addEmployee(emp);
                data.clear();
            }
        }
    }
}

