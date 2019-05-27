import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashSet;



/*
 * Print all the valid combinations of a pair of braces
 * take input at no. of pairs of braces
 *
 * LinkedHashSet is used as we need the last set of strings hence order needs to be maintained
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

    public static void main(String[] args) {
        String input = args[0];
        BracketCombos bc = new BracketCombos();
        List<String> result =  new LinkedList<String>();
        bc.processParenthesis(result, new StringBuffer(), 0,0, Integer.parseInt(input));
        System.out.println(result);
    }
}
