/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 **/

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class CombinationSum2 {
    public static void main (String args[]) {
        int[] input = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println("Combination Sum  : " + combinationSum(input, target));
    }

    public static List<List<Integer>> combinationSum (int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        // Set maintains unique lists and avoids duplicates
        Set<List<Integer>> tracker = new HashSet<>();
        if (candidates != null && candidates.length > 0) {
            // Sorting helps consider each number in a systematic manner and to avoid duplicates as well
            Arrays.sort(candidates);
            helper(candidates, 0, tracker, new LinkedList<Integer>(), target);
            for (List<Integer> curr : tracker) {
                result.add(curr);
            }
        }
        return result;
    }

    /**
     * Goes through the candidates and captures lists of elements that add upto the
     * given target
     *
     * @param candidates
     * @param index
     * @param tracker
     * @param currList
     * @param sum
     */
    public static void helper (int[] candidates, int index, Set<List<Integer>> tracker, List<Integer> currList, int sum) {
        if (sum == 0) {
            List<Integer> valid = new LinkedList<>(currList);
            Collections.sort(valid);
            tracker.add(valid);
        } else if (sum > 0) {
            for (int i = index; i < candidates.length; ++i) {
                //This is little tricky. It is not clear from the problem statement
                // But when we consider a number as a particular index i, then there should not be same number in the same iteration
                // in other works for the next nested call the index is incremented by 1. In that case if the number at that index is
                // same as that of the one considered before the call is made that is ok.
                // But in the same loop if we have say 2 at index then do not consider any 2's there after.
                // As we have sorted the array it is easier to skip these.
                if (i > index && candidates[i] == candidates[i - 1])
                    continue;
                sum -= candidates[i];
                // avoids unecessary calls is sum is already negative
                if (sum >= 0) {
                    currList.add(candidates[i]);
                    helper(candidates, i + 1, tracker, currList, sum);
                    sum += candidates[i];
                    currList.remove(currList.size() - 1);
                }
            }
        }
    }
}
