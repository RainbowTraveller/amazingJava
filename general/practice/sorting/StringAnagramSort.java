import java.util.Arrays;

public class StringAnagramSort {

     public static void main (String [] args) {
         String [] input = {"abcd", "bqrs", "wxyz", "bcda", "zwxy", "rsbq", };
         Arrays.sort(input);
         System.out.println(Arrays.toString(input));
     }
}
