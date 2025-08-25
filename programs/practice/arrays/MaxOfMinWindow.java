import java.util.Arrays;
import java.util.Stack;

/*  Given an integer array of size n, find the maximum of the minimum’s of every window size in the array. Note that window size varies from 1 to n.
 *  Input:  arr[] = {10, 20, 30, 50, 10, 70, 30}
 *  Output:         70, 30, 20, 10, 10, 10, 10
 *
 *  First element in output indicates maximum of minimums of all
 *  windows of size 1.
 *  Minimums of windows of size 1 are {10}, {20}, {30}, {50}, {10},
 *  {70} and {30}.  Maximum of these minimums is 70
 *
 *  Second element in output indicates maximum of minimums of all
 *  windows of size 2.
 *  Minimums of windows of size 2 are {10}, {20}, {30}, {10}, {10},
 *  and {30}.  Maximum of these minimums is 30
 *
 *  Third element in output indicates maximum of minimums of all
 *  windows of size 3.
 *  Minimums of windows of size 3 are {10}, {20}, {10}, {10} and {10}.
 *  Maximum of these minimums is 20
 *
 *  Similarly other elements of output are computed.
 */

public class MaxOfMinWindow {
  public static void main(String[] args) {
    int[] arr = {10, 20, 30, 50, 10, 70, 30};
    int window = 1;
    for (int i = 1; i <= arr.length; ++i) {
      window = i;
      System.out.println(
          "Max from min with window : " + window + " is : " + getMaxOfMin(window, arr));
    }
    System.out.println(
        "Max from min with window : " + window + " is : " + getMaxOfMin(window, arr));
    maxOfMinimiums(arr);
  }

  /**
   * * Get max of min for a given window size
   *
   * @param window size of the window
   * @param arr input array
   * @return max of min for the given window
   */
  public static int getMaxOfMin(int window, int[] arr) {
    int max = Integer.MIN_VALUE;
    if (window > 0 && arr != null && window < arr.length) {
      int size = arr.length;
      for (int i = 0; i + window < size; ++i) {
        // System.out.println("MAX : " + max);
        // Get min of window and track max
        max = Math.max(max, getCurrWindowMin(i, window, arr));
      }
    }
    return max;
  }

  public static int getCurrWindowMin(int index, int window, int[] arr) {
    // Get sub array, sort and take first element
    int[] currWindow = Arrays.copyOfRange(arr, index, index + window);
    Arrays.sort(currWindow);
    return currWindow[0];
  }

  /**
   * Efficient method to get max of min for every window size from 1 to n
   *
   * @param arr input array
   * @return array of max of min for every window size Algorithm Explanation Find the Window Range
   *     for Each Element: The key insight is that an element arr[i] can be the minimum in a window
   *     only if all other elements within that window are greater than or equal to arr[i]. We can
   *     find the extent of such a window by locating the first element smaller than arr[i] to its
   *     left and to its right. Let's call them prevSmaller[i] and nextSmaller[i] respectively.
   *     <p>prevSmaller[i]: Index of the first element to the left of arr[i] that is smaller than
   *     arr[i].
   *     <p>nextSmaller[i]: Index of the first element to the right of arr[i] that is smaller than
   *     arr[i].
   *     <p>The length of the largest window where arr[i] is the minimum is nextSmaller[i] -
   *     prevSmaller[i] - 1.
   *     <p>Use a Monotonic Stack: To find prevSmaller and nextSmaller for all elements efficiently,
   *     we use a monotonic stack. A monotonic stack maintains elements in either strictly
   *     increasing or strictly decreasing order. By iterating through the array and using the
   *     stack, we can find the nearest smaller element for each position in O(n) time.
   *     <p>For nextSmaller, we iterate from right to left. We pop elements from the stack that are
   *     greater than or equal to the current element. The top of the stack is then our nextSmaller.
   *     <p>For prevSmaller, we do the same but iterate from left to right.
   *     <p>Populate the Result Array: We create a result array ans of size n+1. ans[k] will store
   *     the maximum of minimums for a window of size k.
   *     <p>Iterate through the original array. For each element arr[i], calculate its window length
   *     L = nextSmaller[i] - prevSmaller[i] - 1.
   *     <p>arr[i] is a potential candidate for the maximum of minimums for a window of size L. We
   *     update ans[L] = max(ans[L], arr[i]).
   *     <p>Final Pass: The result array ans is still incomplete. For example, if a value V is the
   *     maximum minimum for a window of size k, it must also be a maximum minimum for any window of
   *     size j < k (since a smaller window can fit inside a larger one). To account for this, we do
   *     a final pass on the ans array from right to left, ans[i] = max(ans[i], ans[i+1]).
   *     <p>Example Walkthrough: arr[] = {10, 20, 30, 50, 10, 70, 30} Find prevSmaller and
   *     nextSmaller:
   *     <p>prevSmaller: [-1, 0, 1, 2, -1, 4, 4]
   *     <p>For arr[0]=10: no smaller element to the left -> -1
   *     <p>For arr[1]=20: arr[0]=10 is smaller -> 0
   *     <p>...
   *     <p>For arr[4]=10: no smaller element to the left -> -1
   *     <p>...
   *     <p>nextSmaller: [4, 4, 4, 4, 7, 6, 7] (Note: 7 is beyond array bounds, indicating no
   *     smaller element to the right)
   *     <p>For arr[0]=10: arr[4]=10 is the first element to the right that's smaller -> 4
   *     <p>For arr[1]=20: arr[4]=10 is the first element to the right that's smaller -> 4
   *     <p>...
   *     <p>For arr[5]=70: arr[6]=30 is the first element to the right that's smaller -> 6
   *     <p>...
   *     <p>Calculate Window Lengths and Populate result array: Let's calculate the length L =
   *     nextSmaller[i] - prevSmaller[i] - 1 and update result (initialized to all zeros).
   *     <p>i=0, arr[0]=10: L=4−(−1)−1=4. result[4] = max(0, 10) = 10.
   *     <p>i=1, arr[1]=20: L=4−0−1=3. result[3] = max(0, 20) = 20.
   *     <p>i=2, arr[2]=30: L=4−1−1=2. result[2] = max(0, 30) = 30.
   *     <p>i=3, arr[3]=50: L=4−2−1=1. result[1] = max(0, 50) = 50.
   *     <p>i=4, arr[4]=10: L=7−(−1)−1=7. result[7] = max(0, 10) = 10.
   *     <p>i=5, arr[5]=70: L=6−4−1=1. result[1] = max(50, 70) = 70.
   *     <p>i=6, arr[6]=30: L=7−4−1=2. result[2] = max(30, 30) = 30.
   *     <p>After this step, result array looks like: [0, 70, 30, 20, 10, 0, 0, 10].
   *     <p>Propagate Maximums: We iterate from i=6 down to 1.
   *     <p>i=6: result[6] = max(result[6], result[7]) = max(0, 10) = 10.
   *     <p>i=5: result[5] = max(result[5], result[6]) = max(0, 10) = 10.
   *     <p>i=4: result[4] = max(result[4], result[5]) = max(10, 10) = 10.
   *     <p>i=3: result[3] = max(result[3], result[4]) = max(20, 10) = 20.
   *     <p>i=2: result[2] = max(result[2], result[3]) = max(30, 20) = 30.
   *     <p>i=1: result[1] = max(result[1], result[2]) = max(70, 30) = 70.
   *     <p>The final populated result array is: [0, 70, 30, 20, 10, 10, 10, 10].
   *     <p>Final Output: The result for a window of size 1 is at result[1], for size 2 at
   *     result[2], and so on. The final output is: 70, 30, 20, 10, 10, 10, 10.
   *     <p>This approach correctly and efficiently finds the solution by leveraging the properties
   *     of a monotonic stack to solve the problem in a single pass.
   */
  public static int[] maxOfMinimiums(int[] arr) {
    int n = arr.length;
    int[] nextSmaller = new int[n];
    int[] prevSmaller = new int[n];
    Stack<Integer> stack = new Stack<>();

    // Find next smaller element for each element
    for (int i = n - 1; i >= 0; i--) {
      // Pop elements from stack while they are greater than or equal to arr[i]
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop();
      }
      // If stack is empty, no smaller element to the rightof arr[i]
      // else the top of the stack is the next smaller element
      nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
      // Push current index onto stack
      stack.push(i);
    }

    // Clear the stack for the next pass
    stack.clear();

    // Find previous smaller element for each element
    for (int i = 0; i < n; i++) {
      // Pop elements from stack while they are greater than or equal to arr[i]
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop();
      }
      // If stack is empty, no smaller element to the left of arr[i]
      // else the top of the stack is the previous smaller element
      prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
      // Push current index onto stack
      stack.push(i);
    }

    // Initialize result array
    int[] result = new int[n + 1];

    // Compute maximum of minimums for each window size
    for (int i = 0; i < n; i++) {
      int length = nextSmaller[i] - prevSmaller[i] - 1;
      result[length] = Math.max(result[length], arr[i]);
    }

    // Propagate the maximums to smaller window sizes
    // This ensures that if a value is the maximum minimum for a larger window,
    // it is also considered for smaller windows
    // Final Pass: The result array ans is still incomplete. For example, if a value V is the
    // maximum minimum for a window of size k, it must also be a maximum minimum for any window
    // of size j < k (since a smaller window can fit inside a larger one).
    // To account for this, we do a final pass on the ans array from right to left, ans[i] =
    // max(ans[i], ans[i+1]).
    for (int i = n - 1; i >= 1; i--) {
      result[i] = Math.max(result[i], result[i + 1]);
    }

    // The final answer is from index 1 to n
    int[] finalAnswer = new int[n];
    for (int i = 0; i < n; i++) {
      finalAnswer[i] = result[i + 1];
      System.out.print(finalAnswer[i] + " ");
    }

    return finalAnswer;
  }
}
