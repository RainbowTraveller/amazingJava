/**
 * LeetCode Problem 18: 4Sum https://leetcode.com/problems/4sum/
 *
 * <p>Given an array nums of n integers, return an array of all the unique quadruplets
 *
 * <p>[nums[a], nums[b], nums[c], nums[d]] such that: 0 <= a, b, c, d < n a, b, c, and d are
 * distinct.
 *
 * <p>nums[a] + * nums[b] + nums[c] + nums[d] == target You may return the answer in any order.
 *
 * <p>The program implements a generalized k-sum solution that can solve the 4-sum problem
 *
 * <p>Time Complexity: O(n^(k-1))
 *
 * <p>Space Complexity: O(n)
 */
public class kSum {
  /**
   * Main function to find all unique quadruplets in the array that sum up to the target.
   *
   * @param nums Input array of integers
   * @param target Target sum for the quadruplets
   * @return List of lists containing all unique quadruplets that sum to the target
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {

    if (nums != null || !(nums.length == 0)) {
      Arrays.sort(nums);
      return kSum(nums, target, 0, 4);
    }
    return null;
  }

  /**
   * Generalized k-sum function to find all unique k-tuples in the array that sum up to the target.
   *
   * @param nums Input array of integers
   * @param target Target sum for the k-tuples
   * @param start Starting index in the array for the current search
   * @param k Number of elements to sum up to the target
   * @return List of lists containing all unique k-tuples that sum to the target
   *     <p>The logic is to reduce the k-sum problem to a (k-1)-sum problem recursively until it
   *     reaches the 2-sum problem, which is solved using the two-pointer technique. The function
   *     also includes optimizations to skip unnecessary computations based on the average value of
   *     the target and the sorted nature of the array.
   */
  public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
    // Resultant list to store all unique k-tuples
    List<List<Integer>> result = new LinkedList<>();
    // Base case: if the starting index reaches the end of the array, return empty result
    if (start == nums.length) {
      return result;
    }
    // Calculate the average value needed for the remaining k elements to reach the target
    long average = target / k;
    // If the average is less than the smallest number or greater than the largest number, return
    // empty result
    // This optimization helps in reducing unnecessary computations
    // since the array is sorted, we can make this determination quickly
    // If the average is out of bounds, it's impossible to find k numbers that sum to the target
    // hence we return an empty list
    if (average < nums[start] || average > nums[nums.length - 1]) {
      return result;
    }
    // If k is 2, we can directly use the two-sum approach
    if (k == 2) {
      return twoSum(nums, target, start);
    }
    // Recursive case: reduce the k-sum problem to a (k-1)-sum problem
    for (int i = start; i < nums.length; ++i) {
      // Skip duplicates to ensure unique k-tuples
      // This check ensures that we only consider the first occurrence of each number
      // unless it's the first number in the current recursive call
      // if the first number then continue or if the current number is not equal to the previous
      // number
      // then we can proceed to find (k-1)-sum with the current number included
      if (i == start || nums[i] != nums[i - 1]) {
        for (List<Integer> curr : kSum(nums, target - nums[i], i + 1, k - 1)) {
          // Create a new list to store the current k-tuple
          List<Integer> sub = new ArrayList<>(curr);
          sub.add(nums[i]);
          result.add(sub);
        }
      }
    }
    return result;
  }

  /**
   * Two-sum function to find all unique pairs in the array that sum up to the target.
   *
   * @param nums Input array of integers
   * @param target Target sum for the pairs
   * @param start Starting index in the array for the current search
   * @return List of lists containing all unique pairs that sum to the target
   *     <p>This function uses the two-pointer technique to efficiently find pairs that sum to the
   *     target. It also includes checks to skip duplicates to ensure that only unique pairs are
   *     included in the result.
   */
  public List<List<Integer>> twoSum(int[] nums, long target, int start) {
    List<List<Integer>> res = new LinkedList<>();
    int low = start, hi = nums.length - 1;
    while (low < hi) {
      int sum = nums[hi] + nums[low];
      // If the sum is less than the target or if the current low is a duplicate, move the low
      // pointer to the right
      if (sum < target || (low > start && nums[low - 1] == nums[low])) {
        low++;
        // If the sum is greater than the target or if the current high is a duplicate, move the
        // high
        // pointer to the left
      } else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {

        hi--;
      } else {
        // If the sum equals the target, add the pair to the result and move both pointers
        // to continue searching for other pairs
        res.add(Arrays.asList(nums[low++], nums[hi--]));
      }
    }
    return res;
  }
}
