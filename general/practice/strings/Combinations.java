import java.util.ArrayList;
import java.util.Arrays;

public class Combinations {

    /*
     *
     *  a b c ab bc ac abc
     *
     *  Logic :
     *  abc
     *  a
     *  b
     *  c start from b and combine all elements following it
     *  bc now start with a and combine all elements following it
     *
     */
    public static ArrayList<String> combinations(String input){
        int len = input.length();
        ArrayList<String> combos = new ArrayList<String>(Arrays.asList(input.split("")));
        for(int i = len - 2; i >= 0; --i) {
            int curLength = combos.size();
            for(int j = i + 1; j < curLength; ++j) {
                combos.add(combos.get(i) + combos.get(j));
            }
        }
        return combos;
    }

    public static void combinationsRecursive(String input, char[] output, int length, int recLevel, int start) {
        char[] op = output.clone();
        for(int i = start; i < length; ++i) {
            op[recLevel] = input.charAt(i);
            System.out.println(new String(op));
            if(i < length) {
                combinationsRecursive(input, op, length, recLevel + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        String input = args[0];
        ArrayList<String> combos = combinations(input);
        for (String s : combos) {
            System.out.println(s);
        }
        System.out.println("---------RECURSIVE-----------------");
        combinationsRecursive(input, new char[input.length()], input.length(), 0, 0);
    }
}
