import java.util.ArrayList;

public class BracketCombos2{

    public void helper(ArrayList<String> al, int leftRem, int rightRem, char[] str, int count) {
        if(leftRem < 0 || rightRem < leftRem) return;

        if(leftRem == 0 && rightRem == 0) {
            String s = String.copyValueOf(str);
            System.out.println("::: STORED ::: " + String.copyValueOf(str));
            al.add(s);
        } else {
            if(leftRem > 0) {
                System.out.println("LEFT: " + leftRem + " " + rightRem + " " + count + String.copyValueOf(str));
                str[count] = '(';
                helper(al, leftRem - 1, rightRem,str, count+1);
            }

            if(rightRem > leftRem) {
                System.out.println("\t\t\tRIGHT: " + leftRem + " " + rightRem + " " + count + String.copyValueOf(str));
                str[count] = ')';
                helper(al, leftRem, rightRem - 1,str, count+1);
            }
        }
    }

    public void paranthesisCombo(ArrayList<String> al, int len) {
        char[] str = new char[len*2];
        helper(al, len, len, str,0);
    }

    public static void main(String[] args) {
        String input = args[0];
        BracketCombos2 bc = new BracketCombos2();
        ArrayList<String> al = new ArrayList<String>();
        bc.paranthesisCombo(al,Integer.parseInt(input));
        for(String s : al) {
            System.out.println(s);
        }
    }
}
