import java.lang.StringBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class RemoveChars {
    /*
     * Given input string and remove string
     * Program removes all the characters contained in the remove string from the input string
     */

    public static String removeSeparateOutput(String i, String r) {
        char[] iarr = i.toCharArray();
        char[] rarr = r.toCharArray();
        StringBuffer sbuff = new StringBuffer();
        HashSet<Character> chash = new HashSet<Character>();
        //Create a set of characters from string to be removed
        for(char c : rarr) {
            chash.add(c);
        }

        //if any char from input is present in the set
        //ignore otherwise append
        for(char ch : iarr) {
            if(!chash.contains(ch)){
                sbuff.append(ch);
            }
        }
        return sbuff.toString();
    }

    public static String removeInplace(String i, String r) {
        char[] iarr = i.toCharArray();
        char[] rarr = r.toCharArray();
        HashSet<Character> chash = new HashSet<Character>();
        for(char c : rarr) {
            chash.add(c);
        }
        int index = 0;
        int newIndex = 0;
        for(char ch : iarr) {
            if(chash.contains(ch)) {
                index++;
            } else {
                iarr[newIndex++] = iarr[index++];
            }
        }
        return new String(Arrays.copyOf(iarr, newIndex));
    }


    public static void main(String[] args) {
        String input = args[0];
        String rem = args[1];
        System.out.println(removeSeparateOutput(input, rem));
        System.out.println(removeInplace(input, rem));
    }
}
