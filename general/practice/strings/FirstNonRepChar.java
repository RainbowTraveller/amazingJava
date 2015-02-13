import java.util.LinkedHashMap;

public class FirstNonRepChar {
    /*
     * Returns first non repeated character from a string
     *
     * e.g. total = o
     *      teeeter = r
     */

    public static char nonRepeatedChar(String s) {
        Character nrc = null;
        if(s != null) {
            LinkedHashMap<Character, Integer> chmap = new LinkedHashMap<Character, Integer>();
            for(int i = 0; i < s.length(); ++i) {
                Integer count = chmap.get(s.charAt(i));
                if(count == null) {
                    chmap.put(s.charAt(i), 1);
                } else {
                    chmap.put(s.charAt(i), ++count);
                }
            }

            for(char c : chmap.keySet()) {//Linkedhashmap guaranties keyset is returned in proper insertion order
                 if(chmap.get(c) == 1) {
                    nrc = c;
                    break;
                }
            }
        }
    return nrc;
    }

    public static void main(String[] args) {
       String input = args[0];
       System.out.println(nonRepeatedChar(input));
    }
}
