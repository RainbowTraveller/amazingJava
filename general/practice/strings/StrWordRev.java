import java.lang.String;
import java.lang.Exception;
import java.util.Scanner;

public class StrWordRev {
    private String str;

    public StrWordRev(String str) {
        this.str = str;
    }

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
        this.str = String.valueOf(strArr);
    }

    public String toString() {
        return  this.str;
    }

    private void reverseIndividualWord(char[] strArr, int begin, int end){
       while(begin < end) {
            char temp = strArr[begin];
            strArr[begin] = strArr[end];
            strArr[end] = temp;
            begin++;
            end--;
       }
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
        System.out.println(sr);
        sr.reverseWords();
        System.out.println(sr);
    }
}
