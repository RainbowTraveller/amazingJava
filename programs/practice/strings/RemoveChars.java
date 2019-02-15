import java.lang.StringBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class RemoveChars {
    /*
     * Given input string and remove string
     * Program removes all the characters contained in the remove string from the input string
     */

    public static String removeHeavyLooping(String i, String r) {
        // DOES NOT WORK UNNECESSARY COMPLICATED LOGIC
        int len = i.length();
        char[] iarr = i.toCharArray();
        char[] rarr = r.toCharArray();

        for(int j = 0; j < r.length(); ++j){ //N
            char c = rarr[j];
            for(int k = 0; k < len; ++k) { //N
                char ch = iarr[k];
                if(c == ch){
                    int count = k;
                    while(count < len - 1) {
                        iarr[count] = iarr[count+1];//N
                        count++;
                    }
                    iarr[count] = ' ';
                    len--;
                }
            }
        }
        return new String(iarr);
    }

    public static String removeHash(String i, String r) {
        // DOES NOT WORK UNNECESSARY COMPLICATED LOGIC
        int len = i.length();
        char[] iarr = i.toCharArray();
        char[] rarr = r.toCharArray();
        HashSet<Character> chash = new HashSet<Character>();
        for(char c : rarr) {
            chash.add(c);
        }
        int index = 0;
        for(char ch : iarr) {
            if(chash.contains(ch)){
                int count = index;
                while(count < len - 1) {
                    iarr[count] = iarr[count+1];
                    count++;
                }
                len--;
                index--;
            }
            index++;
        }
        return new String(iarr);
    }

    public static String removeSeparateOutput(String i, String r) {
        char[] iarr = i.toCharArray();
        char[] rarr = r.toCharArray();
        StringBuffer sbuff = new StringBuffer();
        HashSet<Character> chash = new HashSet<Character>();
        for(char c : rarr) {
            chash.add(c);
        }
        int index = 0;
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
        //System.out.println(removeHeavyLooping(input, rem));
        //System.out.println(removeHash(input, rem));
        System.out.println(removeSeparateOutput(input, rem));
        System.out.println(removeInplace(input, rem));
    }
}
