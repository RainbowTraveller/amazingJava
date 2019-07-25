
/* Given an integer indicating no. of pairs of brackets
 * find all possible valid permutations of such pairs
 */
import java.util.ArrayList;
import java.lang.StringBuffer;

public class BracketCombos2 {

    public void helper(ArrayList<String> al, int leftRem, int rightRem, StringBuffer str) {
        if (leftRem == 0 && rightRem == 0) {
            System.out.println("::: STORED ::: " + str);
            al.add(str.toString());
        } else {
            if (leftRem > 0) {
                System.out.println("LEFT: " + leftRem + " " + rightRem + " " + str);
                str.append('(');
                helper(al, leftRem - 1, rightRem, str);
                str.deleteCharAt(str.length() - 1);
            }

            if (rightRem > leftRem) {
                System.out.println("\t\t\tRIGHT: " + leftRem + " " + rightRem + " " + str);
                str.append(')');
                helper(al, leftRem, rightRem - 1, str);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }

    public void paranthesisCombo(ArrayList<String> al, int len) {
        StringBuffer str = new StringBuffer();
        helper(al, len, len, str);
    }

    public static void main(String[] args) {
        String input = args[0];
        BracketCombos2 bc = new BracketCombos2();
        ArrayList<String> al = new ArrayList<String>();
        bc.paranthesisCombo(al, Integer.parseInt(input));
        al.forEach(System.out::println);
    }
}
