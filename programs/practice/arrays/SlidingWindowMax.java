import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max num in sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]


About Monotonic Queue :

In general, whenever we encounter a new element x, we want to discard all elements that are less than x before adding x.
Let's say we currently have [63, 15, 8, 3] and we encounter 12. Any future window with 8 or 3 will also contain 12,
so we can discard them. After discarding them and adding 12, we have [63, 15, 12]. As you can see, we keep elements
in descending order.

To perform these operations, we can use a monotonic queue as it supports efficient insertion, deletion, and retrieval
of elements from the ends of a window. We will implement it with the deque data structure.

A monotonic data structure is one where the elements are always sorted. In our case, we want a monotonic decreasing
queue, which means that the elements in the queue are always sorted descending. When we want to add a new element x,
we maintain the monotonic property by removing all elements less than x before adding x.
*/

public class SlidingWindowMax {
  public static void main(String[] args) {
    int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
    java.util.Arrays.stream(getMaxFromSlidingWindow(input, 3)).forEach(System.out::println);
  }

  /**
   * Function to get max from sliding window of size k
   *
   * <p>Time Complexity : O(n)
   *
   * <p>Space Complexity : O(k)
   *
   * @param nums input array
   * @param windowSize size of the sliding windowSize
   * @return array of max elements from each sliding window
   */
  public static int[] getMaxFromSlidingWindow(int[] nums, int windowSize) {
    Deque<Integer> tracker = new LinkedList<>();
    List<Integer> result = new LinkedList<>();
    if (nums != null && nums.length > windowSize) {
      int len = nums.length;

      // focus on the first k elements where k = windowSize
      // Find the greatest element from that window and then add its index in
      // the tracking queue
      for (int i = 0; i < windowSize; ++i) {
        while (!tracker.isEmpty() && nums[i] >= nums[tracker.peekFirst()]) {
          tracker.pollFirst();
        }
        tracker.offerLast(i);
      }
      //            System.out.println(nums[tracker.peekFirst()]);
      result.add(nums[tracker.peekFirst()]);
      // For subsequent elements do the following
      // 1. Remove index equal to i - k as it is out of the sliding window
      // 2. This will keep next valid and greatest index in the queue
      // 3. Push all indexes such that their value is greater than the

      for (int i = windowSize; i < len; i++) {

        // Remove element index which is outside the current window
        if (!tracker.isEmpty() && tracker.peekFirst() == i - windowSize) {
          tracker.pollFirst();
        }

        // Check from the behind and not from front
        // Remove all indexes from the queue which have and element which is less than
        // current element
        // This will maintain monotonic queue : it should have indexes of in decreasing order, so
        // remove all the indexes corresponding to smaller values than current one from the queue
        // Once that is done then add the current index
        while (!tracker.isEmpty() && nums[i] >= nums[tracker.peekLast()]) {
          tracker.pollLast();
        }
        tracker.offerLast(i);
        //                System.out.println(nums[tracker.peekFirst()]);
        result.add(nums[tracker.peekFirst()]);
      }
      return result.stream().mapToInt(i -> i).toArray();
    }
    return null;
  }
}
