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
    System.out.println("Optimized Solution");
    java.util.Arrays.stream(getMaxFromSlidingWindow(input, 3)).forEach(System.out::println);
    System.out.println("Simple Solution");
    java.util.Arrays.stream(findMaxSlidingWindowSimple(input, 3)).forEach(System.out::println);
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
        while (!tracker.isEmpty() && nums[i] >= nums[tracker.peekLast()]) {
          tracker.pollLast();
        }
        tracker.offerLast(i);
      }
      // System.out.println(nums[tracker.peekFirst()]);
      result.add(nums[tracker.peekFirst()]);
      // For subsequent elements do the following
      // 1. Remove index equal to i - k as it is out of the sliding window
      // 2. This will keep next valid and greatest index in the queue
      // 3. Push all indexes such that their value is greater than the

      for (int i = windowSize; i < len; i++) {

        // Remove element index which is outside the current window
        // Why the First Element (Index) is Checked for the Window
        // The first element in the deque (tracker.peekFirst()) is the one that was added earliest
        // and corresponds to the largest value so far in the window.
        // When the window slides, the element at the beginning of the previous window is now
        // outside the current window.
        // The condition tracker.peekFirst() == i - windowSize checks precisely this. The index i -
        // windowSize represents the index that is just a step before
        // the current window. If the index at the front of the deque is this "outdated" index, it
        // must be removed. By doing this, we ensure that the deque only
        // contains indices that are within the bounds of the current sliding window.
        if (!tracker.isEmpty() && tracker.peekFirst() == i - windowSize) {
          tracker.pollFirst();
        }

        // Check from the behind and not from front
        // Remove all indexes from the queue which have and element which is less than
        // current element
        // This will maintain monotonic queue : it should have indexes of in decreasing order, so
        // remove all the indexes corresponding to smaller values than current one from the queue
        // Once that is done then add the current index
        //
        // Explanation for Removing from the Rear End
        // Removing elements from the rear of the deque is crucial for maintaining the monotonic
        // decreasing property. When a new element nums[i] is about to be added, we compare it with
        // the element at the end of the deque, nums[tracker.peekLast()].
        //
        // If nums[i] is greater than or equal to nums[tracker.peekLast()], then the element at the
        // back of the deque and any elements before it are no longer relevant.
        //
        // The newly added element nums[i] is a better candidate for the maximum. All previous
        // elements in the deque that are smaller than nums[i] are now obsolete. Their indices can
        // be safely removed because they will never be the maximum in a future window as long as
        // nums[i] is present. As the window slides, nums[i] will eventually move out, but the
        // elements we are removing would have moved out even sooner, so they are not needed.

        while (!tracker.isEmpty() && nums[i] >= nums[tracker.peekLast()]) {
          tracker.pollLast();
        }
        tracker.offerLast(i);
        // System.out.println(nums[tracker.peekFirst()]);
        result.add(nums[tracker.peekFirst()]);
      }
      return result.stream().mapToInt(i -> i).toArray();
    }
    return null;
  }

  /**
   * Function to get max from sliding window of size k
   *
   * <p>Time Complexity : O(n*k)
   *
   * <p>Space Complexity : O(1)
   *
   * @param nums input array
   * @param k size of the sliding window
   * @return array of max elements from each sliding window
   */
  public static int[] findMaxSlidingWindowSimple(int[] nums, int k) {
    List<Integer> maxList = new LinkedList<>();
    for (int i = 0; i <= nums.length - k; i++) {
      int max = nums[i];
      for (int j = 1; j < k; j++) {
        if (nums[i + j] > max) {
          max = nums[i + j];
        }
      }
      maxList.add(max);
    }
    return maxList.stream().mapToInt(i -> i).toArray();
  }
}
