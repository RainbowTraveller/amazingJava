import java.lang.StringBuffer;
import java.util.Scanner;

public class MultiplyNumbersAsStr {

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = sc.nextLine();
        String num2 = sc.nextLine();

        //Reverse the strings
        //e.g. 123 --> 321
        StringBuffer sb1 = new StringBuffer(num1);
        sb1 = sb1.reverse();
        //e.g. 456 --> 654
        StringBuffer sb2 = new StringBuffer(num2);
        sb2 = sb2.reverse();


        //array to store the result
        int [] answer = new int[num1.length() + num2.length()];

        //Last valid digit index of the final multiplication
        int endingIndex = 0;

        for(int i = 0; i < sb1.length(); ++i) { // 3 then 2 then 1
            //3-->2--->1
            int digit1 = sb1.charAt(i) - '0';

            for(int j = 0; j < sb2.length(); ++j) { //6 then 5 then 4

                //6-->5--->4
                int digit2 = sb2.charAt(j) - '0';
                int mul = digit1 * digit2;
                //0 and 0 index --> 0; 0 and 1 index ---> 1
                //0 1 2
                //0 1 2
                //-------
                //how actual multiplication is done and storing of result
                //0-0 0-1 0-2
                //    1-0 1-1 1-2
                //        2-0 2-1 2-2
                int index = i + j;
                answer[ index ] += mul;
                int carry = answer[ index ] / 10;
                answer[ index ] = answer[ index ] % 10;
                answer[ index + 1 ] += carry;
                endingIndex = index + 1;
            }
        }

        for(int i = endingIndex; i >= 0; --i) {
            System.out.print(answer[i]);
        }
        System.out.println();
    }
}
