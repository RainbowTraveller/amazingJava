package com.crystalsoft.employeemanagement;

import com.crystalsoft.employeemanagement.Employee;
import com.crystalsoft.employeemanagement.Rolodex;
import com.crystalsoft.employeemanagement.OperationsImpl;

import java.lang.Iterable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class SavingImpl extends OperationsImpl {
    
    private String fileLocation;
    private String fileName;

    public SavingImpl(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
    }

    public void save(Rolodex rdx) throws Exception {
        File file = new File(this.fileLocation  + this.fileName);
        /*
         * Check if file exists and prompt for overwritting
         */
        // Different kind of try
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(rdx.toString());
        } catch (IOException ioe) {
            System.out.println("Error in saving the data");
            ioe.printStackTrace();
        }
    }
}
