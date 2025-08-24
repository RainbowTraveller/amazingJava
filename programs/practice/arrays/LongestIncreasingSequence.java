import java.util.Arrays;

/*
 * Longest increasing subsequence ( this is not continuous, hence not substring)
 */
public class LongestIncreasingSequence {

  /**
   * Dynamic programming approach - O(n^2) time and O(n) space <br>
   * e.g. 2 1 4 0 9 5 11 1 1 2 1 3 3 4 Final result of the function is 4
   *
   * <p>2 >> 4 >> 9 >> 11 is longest increasing subsequence
   *
   * <p>The program correctly implements the classic dynamic programming solution for the LIS
   * problem. Here's how it works:
   *
   * <p>Initialization: The tracker array is initialized with all 1s. tracker[i] represents the
   * length of the longest increasing subsequence that ends at index i. Every element is a
   * subsequence of at least length one (itself).
   *
   * <p>DP Calculation: The nested loops build the solution iteratively. For each element arr[i],
   * the inner loop checks all previous elements arr[j] (where j < i).
   *
   * <p>If arr[j] is smaller than arr[i], it means arr[i] can potentially extend the increasing
   * subsequence that ends at arr[j].
   *
   * <p>The condition tracker[i] < tracker[j] + 1 checks if extending the subsequence ending at j
   * would create a longer subsequence ending at i than what's currently stored. If so, tracker[i]
   * is updated to tracker[j] + 1.
   *
   * <p>Final Result: After the loops complete, the tracker array holds the length of the longest
   * increasing subsequence ending at each position. The overall LIS length is simply the maximum
   * value in the tracker array. The final loop correctly finds this maximum value.
   */
  public static void longestIncreasingSequnce(int[] arr) {
    if (arr != null) {
      int[] tracker = new int[arr.length];
      // Initialize tracker array with 1s
      for (int i = 0; i < arr.length; ++i) {
        tracker[i] = 1;
      }

      // DP Calculation
      for (int i = 1; i < arr.length; ++i) {
        for (int j = 0; j < i; ++j) {
          if (arr[j] < arr[i] && tracker[i] < tracker[j] + 1) {
            tracker[i] = tracker[j] + 1;
          }
        }
      }

      int max = 0;
      for (int i = 0; i < arr.length; ++i) {
        // System.out.print(" " + tracker[ i ]);
        if (max < tracker[i]) {
          max = tracker[i];
        }
      }
      System.out.println("Longest increasing subsequence length : " + max);
    }
  }

  /**
   * Calculates the length of the longest increasing subsequence using an O(n log n) approach. This
   * method maintains an array of the smallest tail elements of all increasing subsequences.
   *
   * @param nums The input array of integers.
   * @return The length of the longest increasing subsequence.
   */
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // tails[i] is the smallest tail of all increasing subsequences with length i+1.
    int[] tails = new int[nums.length];
    int size = 0;

    for (int num : nums) {
      // Find the insertion point for 'num' in the 'tails' array.
      // This is where 'num' could form a new LIS.
      int i = 0, j = size;
      while (i < j) {
        int mid = i + (j - i) / 2;
        if (tails[mid] < num) {
          i = mid + 1;
        } else {
          j = mid;
        }
      }

      // Replace the tail of an existing LIS or extend the LIS.
      tails[i] = num;
      if (i == size) {
        size++;
      }
    }
    return size;
  }

  /*
   * This is continuous sequence length finder (it is substring here)
   */
  public static void longestContinuousIncreasingSequnce(int[] arr) {
    if (arr != null) {
      int curr = 1;
      int max = 1;
      for (int i = 1; i < arr.length; ++i) {
        if (arr[i - 1] < arr[i]) {
          curr++;
          max = Math.max(curr, max);
        } else {
          curr = 1;
        }
      }
      System.out.println("Longest increasing subsequence length : " + max);
    }
  }

  public static int LISRecursive(int[] arr) {
    return LISRecursiveHelper(arr, Integer.MIN_VALUE, 0);
  }

  /**
   * Consider taken and not taken scenario. We consider each elements and then first take it and see
   * if it is greater than previous one and modify seq. length In not taken scene we don't care if
   * it matches criteria or not At each step we have 2 choices and there are n such cases to
   * consider to we have 2^n running time
   */
  public static int LISRecursiveHelper(int[] arr, int prevMax, int index) {
    if (index == arr.length) {
      return 0;
    }

    int taken = 0;
    if (prevMax < arr[index]) {
      taken = 1 + LISRecursiveHelper(arr, arr[index], index + 1);
    }
    int notTaken = LISRecursiveHelper(arr, prevMax, index + 1);
    return Math.max(notTaken, taken);
  }

  public static int LISRecursiveMemo(int[] arr) {
    int[][] memo = new int[arr.length + 1][arr.length];
    for (int[] row : memo) {
      Arrays.fill(row, -1);
    }
    return LISRecursiveMemoHelper(arr, -1, 0, memo);
  }

  public static int LISRecursiveMemoHelper(int[] arr, int prevIndex, int currIndex, int[][] memo) {
    if (currIndex == arr.length) {
      return 0;
    }
    if (memo[prevIndex + 1][currIndex] > 0) {
      return memo[prevIndex + 1][currIndex];
    }

    int taken = 0;
    if (prevIndex == -1 || arr[prevIndex] < arr[currIndex]) {
      taken = 1 + LISRecursiveMemoHelper(arr, currIndex, currIndex + 1, memo);
    }
    int notTaken = LISRecursiveMemoHelper(arr, prevIndex, currIndex + 1, memo);
    memo[prevIndex + 1][currIndex] = Math.max(taken, notTaken);
    return memo[prevIndex + 1][currIndex];
  }

  public static void main(String[] args) {
    // int [] input = {10, 11, 0 , 0, 1 ,2, 3, -1, 2 ,3, 14, 5, 6, 7};
    int[] input = {10, 22, 9, 33, 21, 50, 41, 60};

    longestIncreasingSequnce(input);
    longestContinuousIncreasingSequnce(input);
    LongestIncreasingSequence lis = new LongestIncreasingSequence();
    System.out.println("Binary Search : " + lis.lengthOfLIS(input));
    System.out.println("Recursive : " + LISRecursive(input));
    System.out.println("Recursive Memo : " + LISRecursiveMemo(input));
  }
}
