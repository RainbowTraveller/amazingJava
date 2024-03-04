import java.lang.String;
import java.lang.Exception;
import java.util.Scanner;

/*
 * Program to reverse each word of a string
 */
public class StrWordRev {
    private String str;

    public StrWordRev(String str) {
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }

    public static void main(String[] args){
        String str = "";
        System.out.println("Please Enter a String: ");
        Scanner sc = new Scanner(System.in);
        try{
            str = sc.nextLine();
        } catch(Exception e) {
            e.printStackTrace();
        }
        StrWordRev sr = new StrWordRev(str);
        sr.reverseWords();
        System.out.println("Reversing words in place : " + sr);
        sr.reverseStringAndWords();
        System.out.print("Reversed word string : " + sr);
    }

    /*
     * Just reverses the words from a string
     */
    public void reverseWords() {
        int length = this.str.length();
        int begin = length - 1;
        int end = length - 1;
        char[] strArr = this.str.toCharArray();
        while(begin != 0) {
            begin--;
            if(strArr[begin] == ' ') {
                reverseIndividualWord(strArr, begin+1, end);
                end = begin - 1;
                begin = end;
            }
        }
        reverseIndividualWord(strArr, begin, end);
        this.str = new String(strArr);
    }

    public String toString() {
        return  this.str;
    }

    /*
     *  Simple reverse of String
     */
    private void reverseIndividualWord(char[] strArr, int begin, int end){
       while(begin < end) {
            char temp = strArr[begin];
            strArr[begin] = strArr[end];
            strArr[end] = temp;
            begin++;
            end--;
       }
    }

    /* Reverses String and then each word as well
     */
	public void reverseStringAndWords() {
        /*if(input != null) {
            char[] arr = input.toCharArray();
            int start = 0;
            int end = input.length();
            int tracker = 0;
            reverseIndividualWord(arr, start, end - 1);
            for(int i = 0; i < end; ++i) {
                if(arr[i] == ' ') {
                    tracker = i - 1;
                    reverseIndividualWord(arr, start, tracker);
                    start = i + 1;
                }
            }
            tracker = end - 1;
            reverseIndividualWord(arr, start, tracker);
            System.out.print("Reversed word string : ");
            for(int i = 0; i < end; ++i) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }
        */
        char[] strArr = this.str.toCharArray();
        reverseIndividualWord(strArr, 0, str.length() - 1);
        this.str = new String(strArr);
        reverseWords();
    }
}
