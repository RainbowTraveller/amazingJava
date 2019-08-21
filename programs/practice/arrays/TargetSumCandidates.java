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
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class TargetSumCandidates {
    public static void main(String[] args) {
		int [] input = {2,3,6,7};
		int target = 7;
		System.out.println("Combination Sum  : " + combinationSum(input, target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Set<List<Integer>> ulist = new HashSet<>();

        Arrays.sort(candidates);
        helper(candidates, target, 0, ulist, new LinkedList<Integer>() );
        for(List<Integer> valid : ulist) {
            list.add(valid);
        }
        return list;
    }

    public static void helper(int[] candidates, int target, int currSum, Set<List<Integer>>list,  List<Integer> curr) {
        if(currSum == target) {
            //System.out.println(curr + " :: " + currSum);
            LinkedList<Integer> valid = new LinkedList<>(curr);
            Collections.sort(valid);
            list.add(valid);
        } else {
            for(int i = 0; i < candidates.length; ++i) {
                currSum += candidates[i];
                if(currSum <= target) {
                    curr.add(candidates[i]);
                    helper(candidates, target, currSum, list, curr);
                    curr.remove(curr.size() - 1);
                    //System.out.println(curr + " :: " + currSum);
                }
                currSum -= candidates[i];
            }
        }
    }
}

