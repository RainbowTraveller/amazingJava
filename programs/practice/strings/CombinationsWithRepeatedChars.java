/**
 * Computes and prints Combinations of a string containing repeated Characters
 */

import java.util.Map;
import java.util.HashMap;

public class CombinationsWithRepeatedChars {
    public static void combinations(char[] inputArr, StringBuffer output, int[] allowed, int starting) {
        for(int i = starting; i < allowed.length; ++i) {
            if(allowed[i] != 0) {
                allowed[i]--;
                output.append(inputArr[i]);
                System.out.println(output.toString());
                combinations(inputArr, output, allowed, i);
                allowed[i]++;
                output.deleteCharAt(output.length() - 1);
            }
        }
    }

    public static void main(String [] args) {

        //Tracking the occurrences of each character
        Map<Character, Integer> unique = new HashMap<Character, Integer>();
        String input = args[0];
        int len = input.length();
        StringBuffer output = new StringBuffer(len);
        char [] inputArr = input.toCharArray();
        for(int i=0; i < len; ++i) {
            if(unique.containsKey(inputArr[i])) {
                int frequency =  unique.get(inputArr[i]);
                frequency++;
                unique.put(inputArr[i], frequency);
            } else {
                unique.put(inputArr[i], 1);
            }
        }
        int[] allowed = new int[unique.size()];
        inputArr = new char[unique.size()];
        int i = 0;
        for(Character c : unique.keySet()) {
            allowed[i] = unique.get(c);
            inputArr[i] = c;
            i++;
        }
        combinations(inputArr, output, allowed, 0);
    }
}
