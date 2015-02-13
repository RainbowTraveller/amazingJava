import java.lang.String;
import java.util.Scanner;

public class ScannerInput {
    public static void main (String [] args) {

        System.out.println("Please enter the information about the employee to be Modified"); 
        Scanner scanIn = new Scanner(System.in); 
        System.out.println("Enter First Name:"); 
        String firstname = scanIn.nextLine(); 
        System.out.println("Enter Last Name:"); 
        String lastname = scanIn.nextLine(); 
        System.out.println("Enter Id:"); 
        int id = Integer.valueOf(scanIn.nextLine()); 
        System.out.println("Enter the position:");
        String position = scanIn.nextLine();
        System.out.println("Enter the project:");
        String project = scanIn.nextLine();
        System.out.println("Entered data: " + " " + firstname + " " + lastname + " " + id + " " + position + " " + project + " ");

    }
}
