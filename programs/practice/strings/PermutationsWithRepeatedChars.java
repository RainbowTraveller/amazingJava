/**
 * Computes and prints permutations of a string containing repeated Characters
 */

import java.util.Map;
import java.util.HashMap;

public class PermutationsWithRepeatedChars {
    public static void perms(char[] inputArr, StringBuffer output, int[] allowed, int len) {
        if(output.length() == len) {
            System.out.println(output.toString());
            return;
        }
        for(int i = 0; i < allowed.length; ++i) {
            if(allowed[i] != 0) {
                allowed[i]--;
                output.append(inputArr[i]);
                perms(inputArr, output, allowed, len);
                allowed[i]++;
                output.deleteCharAt(output.length() - 1);
            }
        }
    }

    public static void main(String [] args) {
        Map<Character, Integer> unique = new HashMap<Character, Integer>();
        String input = args[0];
        int len = input.length();
        StringBuffer output = new StringBuffer(len);
        char [] inputArr = input.toCharArray();
        for(int i=0; i < len; ++i) {
            if(unique.containsKey(inputArr[i])) {
                int frequency =  unique.getOrDefault(inputArr[i], 0);
                unique.put(inputArr[i], frequency + 1);
            }
        }
        int[] allowed = new int[unique.size()];
        int i = 0;
        //Note: take only set of characters with are unique
        //Frequency is already computed
        inputArr = new char[ unique.size()];
        for(Character c : unique.keySet()) {
            allowed[i] = unique.get(c);
            inputArr[i] = c;
            i++;
        }
        perms(inputArr, output, allowed, len);
        }
}
