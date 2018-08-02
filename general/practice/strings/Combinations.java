import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Combinations {

    /*
     *
     *  a b c ab bc ac abc
     *
     */

    public static ArrayList<String> combinations(String input){
     /*   Logic :
     *  a    b   c
     *  ab   bc
     *  ac
     *  abc
     *
     *  start from b and combine all elements following it
     *  now start with a and combine all elements following it
     *
     */
        int len = input.length();
        System.out.println("Input Length : " + len);
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

    public static void powerSet(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        LinkedList<String> ps = new LinkedList<String>();
        powerSetHelper(arr,ps,len - 1);
        for(String str : ps) {
            System.out.println(str);
        }
    }

    public static void powerSetHelper(char[] arr, LinkedList<String> ps, int len) {
        /*
         * Go to innermost depth, when len = -1 add empty string to output list.
         * now as we come out of recursion, combine all output lists to current char at corr. index (which is len)
         */
        if(len == -1) {
            ps.add(new String());
            return;
        } else {
            powerSetHelper(arr, ps, len - 1);
            System.out.println(len);
            /*for(String str : ps) {
                System.out.println("E :" + str);
            }*/
            String str = "";
            LinkedList<String> templist = new LinkedList<String>();
            for(String s : ps) {
                if(s.equals("")) {
                    str = str + arr[len];
                } else {
                    str = s + arr[len];
                }
                templist.add(str);
            }
            ps.addAll(templist);
        }
    }

    public static void CombinationAllowed(char[] arr, int length, int index, StringBuffer output, int[] allowed) {
        for(int i = index + 1; i < length; ++i) {
            if(allowed[i] > 0) {
                output.append(arr[i]);
                System.out.println(output.toString());
                int nextIndex = -1;
                if(allowed[i] == 1) {
                    nextIndex = i;
                } else {
                    nextIndex = i - 1;
                }
                CombinationAllowed(arr, length, nextIndex, output, allowed);
                output.deleteCharAt(output.length() - 1);
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

        System.out.println("---------POWER SET-----------------");
        powerSet(input);

        System.out.println("---------Using ALLOWED MAP-----------------");
        int length = input.length();
        char[] arr = input.toCharArray();
        int[] allowed = new int[length];
        StringBuffer buff = new StringBuffer(length);
        for(int i = 0; i < length; ++i) {
             allowed[i] = 1;
        }
        CombinationAllowed(arr, length, -1, buff, allowed);
    }
}
