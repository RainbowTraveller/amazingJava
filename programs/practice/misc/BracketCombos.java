import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashSet;



/*
 * Print all the valid combinations of a pair of braces
 * take input at no. of pairs of braces
 *
 *      Basic backtracking principles
 *      input : ( or  )
 *      constraint : closed < open  then proceed adding closed
 *      goal : total count is 2 * n brackets
 *
 */
public class BracketCombos {


    public void processParenthesis(List<String> result,  StringBuffer buff, int open, int close,int max) {
        if (buff.length() == max * 2) {
          result.add(buff.toString());
          return;
        }

        if (open < max) {
          processParenthesis(result, buff.append("("), open+1, close, max);
          buff.deleteCharAt(buff.length() - 1);
        }

        if (close < open) {
          processParenthesis(result, buff.append(")"), open, close+1, max);
          buff.deleteCharAt(buff.length() - 1);
        }
    }

    public void helper(LinkedList<String> al, int leftRem, int rightRem, StringBuffer str) {
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
    public static void main(String[] args) {
        String input = args[0];
        BracketCombos bc = new BracketCombos();
        List<String> result =  new LinkedList<String>();
        bc.processParenthesis(result, new StringBuffer(), 0,0, Integer.parseInt(input));
        System.out.println(result);

        StringBuffer str = new StringBuffer();
        int len = Integer.parseInt(input);
        LinkedList<String> al = new LinkedList<String>();
        bc.helper(al, len, len, str);
        al.forEach(System.out::println);
    }
}
