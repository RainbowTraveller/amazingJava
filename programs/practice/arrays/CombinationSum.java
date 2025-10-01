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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CombinationSum {
  public static void main(String[] args) {
    int[] input = {2, 3, 6, 7};
    int target = 7;
    System.out.println("Combination Sum  : " + combinationSum(input, target));
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> list = new LinkedList<>();
    List<Integer> current = new LinkedList<>();
    // To maintain unique list collection and avoid duplicates
    // Set<List<Integer>> ulist = new HashSet<>();

    // Important step to collect the elements in systematic order
    // Arrays.sort(candidates); // Not required for Optimized version
    // helperAdd(candidates, target, 0, ulist, new LinkedList<Integer>()); // starts from 0 and adds
    // numbers to reach sum
    // helperSub(
    //     candidates,
    //     new LinkedList<Integer>(),
    //     target,
    //     ulist); // This subtracts and checks if the sum is 0
    // for (List<Integer> valid : ulist) {
    //   list.add(valid);
    // }
    helperOptimized(candidates, target, 0, current, list);
    return list;
  }

  /**
   * This method starts from 0 and adds numbers to reach the target sum
   *
   * @param candidates
   * @param target
   * @param currSum
   * @param list
   * @param curr
   */
  public static void helperAdd(
      int[] candidates, int target, int currSum, Set<List<Integer>> list, List<Integer> curr) {
    if (currSum == target) {
      // System.out.println(curr + " :: " + currSum);
      LinkedList<Integer> valid = new LinkedList<>(curr);
      Collections.sort(valid);
      list.add(valid);
    } else {
      // Unlimited number of occurrences are allowed so we always start scanning the array
      // from the index 0 and reconsider all the elements
      for (int i = 0; i < candidates.length; ++i) {
        currSum += candidates[i];
        // Important check avoids extra recursive calls
        if (currSum <= target) {
          curr.add(candidates[i]);
          helperAdd(candidates, target, currSum, list, curr);
          curr.remove(curr.size() - 1);
          // System.out.println(curr + " :: " + currSum);
        }
        currSum -= candidates[i];
      }
    }
  }

  /**
   * This method starts from target and subtracts numbers to reach 0
   *
   * @param inputs
   * @param currList
   * @param sum
   * @param tracker
   */
  public static void helperSub(
      int[] inputs, List<Integer> currList, int sum, Set<List<Integer>> tracker) {
    if (sum == 0) {
      List<Integer> finalList = new LinkedList<>(currList);
      Collections.sort(finalList);
      tracker.add(finalList);
    } else if (sum > 0) {
      for (int i = 0; i < inputs.length; i++) {
        sum -= inputs[i];
        if (sum >= 0) {
          currList.add(inputs[i]);
          helperSub(inputs, currList, sum, tracker);
          currList.remove(currList.size() - 1);
          sum += inputs[i];
        }
      }
    }
  }

  /**
   * Optimized version without using Set to maintain unique lists
   *
   * @param candidates array of input numbers
   * @param target sum to be achieved k
   */
  public static void helperOptimized(
      int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new LinkedList<>(current));
      return;
    }
    if (target < 0) {
      return;
    }
    for (int i = start; i < candidates.length; i++) {
      current.add(candidates[i]);
      helperOptimized(candidates, target - candidates[i], i, current, result);
      current.remove(current.size() - 1);
    }
  }
}
