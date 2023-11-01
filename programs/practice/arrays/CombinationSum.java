/*
 *  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 *  The same repeated number may be chosen from candidates unlimited number of times.
 *
 *  Note:
 *
 *  All numbers (including target) will be positive integers.
 *  The solution set must not contain duplicate combinations.
 *  Example 1:
 *
 *  Input: candidates = [2,3,6,7], target = 7,
 *  A solution set is:
 *  [
 *    [7],
 *    [2,2,3]
 *  ]
 *  Example 2:
 *
 *  Input: candidates = [2,3,5], target = 8,
 *  A solution set is:
 *  [
 *    [2,2,2,2],
 *    [2,3,3],
 *    [3,5]
 *  ]

 Please also check : CombinationSum2.java
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class CombinationSum {
    public static void main (String[] args) {
        int[] input = {2, 3, 6, 7};
        int target = 7;
        System.out.println("Combination Sum  : " + combinationSum(input, target));
    }

    public static List<List<Integer>> combinationSum (int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        //To maintain unique list collection and avoid duplicates
        Set<List<Integer>> ulist = new HashSet<>();

        //Impoartant step to collect the elements in sysstematic order
        Arrays.sort(candidates);
//        helper(candidates, target, 0, ulist, new LinkedList<Integer>()); // starts from 0 and adds numbers to reach sum
        helper(candidates, new LinkedList<Integer>(), target, ulist); // This subtracts and checks if the sum is 0
        for (List<Integer> valid : ulist) {
            list.add(valid);
        }
        return list;
    }

    /**
     * Logic where we go from 0 to target sum by adding cadidates to a sum
     *
     * @param candidates
     * @param target
     * @param currSum
     * @param list
     * @param curr
     */
    public static void helper (int[] candidates, int target, int currSum, Set<List<Integer>> list, List<Integer> curr) {
        if (currSum == target) {
            //System.out.println(curr + " :: " + currSum);
            LinkedList<Integer> valid = new LinkedList<>(curr);
            Collections.sort(valid);
            list.add(valid);
        } else {
            //Unlimited number of occurrences are allowed so we always start scanning the array
            // from the index 0 and reconsider all the elements
            for (int i = 0; i < candidates.length; ++i) {
                currSum += candidates[i];
                // Important check avoids extra recursive calls
                if (currSum <= target) {
                    curr.add(candidates[i]);
                    helper(candidates, target, currSum, list, curr);
                    curr.remove(curr.size() - 1);
                    //System.out.println(curr + " :: " + currSum);
                }
                currSum -= candidates[i];
            }
        }
    }


    /**
     * Logic where target sum is reduced one cadidate item a time to reach zero
     *
     * @param inputs
     * @param currList
     * @param sum
     * @param tracker
     */
    public static void helper (int[] inputs, List<Integer> currList, int sum, Set<List<Integer>> tracker) {
        if (sum == 0) {
            List<Integer> finalList = new LinkedList<>(currList);
            Collections.sort(finalList);
            tracker.add(finalList);
        } else if (sum > 0) {
            for (int i = 0; i < inputs.length; i++) {
                sum -= inputs[i];
                if (sum >= 0) {
                    currList.add(inputs[i]);
                    helper(inputs, currList, sum, tracker);
                    currList.remove(currList.size() - 1);
                    sum += inputs[i];
                }
            }
        }
    }
}



