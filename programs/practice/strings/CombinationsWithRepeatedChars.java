
/**
 * Computes and prints Combinations of a string containing repeated Characters
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class CombinationsWithRepeatedChars {
    public static void combinations(char[] inputArr, StringBuffer output, int[] allowed, int starting) {
        for (int i = starting; i < allowed.length; ++i) {
            if (allowed[i] != 0) {
                allowed[i]--;
                output.append(inputArr[i]);
                System.out.println(output.toString());
                //Here we pass on i which is same as current index in the array
                //This is because there are multiple occurrences of a particular character
                //and if a character count is exhausted or not is checked by allowed variable
                combinations(inputArr, output, allowed, i);
                allowed[i]++;
                output.deleteCharAt(output.length() - 1);
            }
        }
    }

    public static void main(String[] args) {

        // Tracking the occurrences of each character
        Map<Character, Integer> unique = new HashMap<Character, Integer>();
        String input = args[0];
        int len = input.length();
        StringBuffer output = new StringBuffer(len);
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < len; ++i) {
            unique.put(inputArr[i], unique.getOrDefault(inputArr[i], 0) + 1);
        }
        int[] allowed = new int[unique.size()];
        inputArr = new char[unique.size()];
        int i = 0;
        for (Character c : unique.keySet()) {
            allowed[i] = unique.get(c);
            inputArr[i] = c;
            i++;
        }
        combinations(inputArr, output, allowed, 0);
    }

    //This is subsets for set of unique integers, no repeated numbers
    //idea remains the same
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums != null || nums.length > 0) {
            helper(result, new LinkedList<Integer>(), 0, nums);
            result.add(new LinkedList<Integer>());
        }

        return result;
    }

    public void helper(List<List<Integer>> result, LinkedList<Integer> curr, int i, int[] nums) {

        for (int j = i; j < nums.length; ++j) {
            curr.addLast(nums[j]);
            result.add(new LinkedList<Integer>(curr));
            //Here we are not dealing with multiple occurrences
            //so there is not check of count of a number or character is
            //exhausted. So adjust index to next character as j + 1``
            helper(result, curr, j + 1, nums);

            curr.removeLast();
        }

    }
}
