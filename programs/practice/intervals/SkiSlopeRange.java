import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/*
 Ski season is approaching! You are an employee at a ski resort, and you have been tasked with
 ensuring the slopes on the resort are fully covered with snow for maximum ski-ability.

 You have a slope with indices from 0...N, and a collection of snow making machines installed
 along that slope. Each machine is a tuple of (index, range) indicating the machine is on the
 slope at index, and is capable of covering the slope with snow up to range indices away.

 Your goal is to find the smallest number of snow machines that need to be turned on in order to
 cover the entire slope with snow.

 Problem
 Write a function that takes in an integer N and a list of machines, and return the minimum number
 of snow making machines that need to be turned on to fully cover the slope. If it is not possible
 to cover the slope, return -1

 Examples
 N: 2 Machines: [(0, 1), (1, 1), (2, 1)]    Output: 1
 Comment: We simply need to turn on the middle machine at index 1 to cover the entire slope

 N: 3 Machines: [(0, 2)]     Output: -1
 Comment: The only machine at index 0 cannot possibly cover the entire slope.

 N: 5 Machines: [(0, 2) (1, 1), (3, 1), (4, 1)] Output: 2
 Comment: Either machine at index 0 or index 1 can be activate to cover up to index 2. The machine
 at index 4 covers the remaining parts of the slope from indices 3, 4, 5.
*/
public class SkiSlopeRange {

  /**
   * Logic Explanation The provided Java code solves the ski slope coverage problem using a greedy
   * algorithm. The core idea is to always make the locally optimal choice in the hope that it will
   * lead to a globally optimal solution. In this case, the greedy choice is to always select the
   * snow machine that extends the coverage the farthest.
   *
   * <p>Interval Transformation: The first step is to convert the machine data (index, range) into a
   * set of coverage intervals [start, end]. A machine at index with range can cover the slope from
   * max(0, index - range) to min(N, index + range). This transformation simplifies the problem into
   * a classic interval-covering problem.
   *
   * <p>Sorting: The algorithm then sorts these intervals based on their start point in ascending
   * order. This sorting is crucial because it allows us to process the slope from left to right
   * (from index 0 to N).
   *
   * <p>Greedy Selection: We start with currentCoverage at 0. The goal is to extend this coverage to
   * N in the fewest steps. In each step (representing one snow machine turned on), we look at all
   * available machines that can cover the current currentCoverage point. From this subset of
   * machines, we select the one that reaches the farthest.
   *
   * <p>Iteration: The process repeats:
   *
   * <p>Find the machine that starts at or before currentCoverage and has the maximum end point
   * (maxReach).
   *
   * <p>If no such machine exists, it's impossible to extend coverage, so we return -1.
   *
   * <p>Otherwise, we increment our machinesNeeded count and update currentCoverage to maxReach,
   * effectively "jumping" to the new farthest point reached.
   *
   * <p>The loop continues until currentCoverage is greater than or equal to N, meaning the entire
   * slope is covered. The final machinesNeeded count is the minimum required.
   *
   * <p>Time Complexity: The algorithm runs in O(M log M) time, where M is the number of machines,
   * due to the sorting step. The greedy selection process runs in O(M) time.
   *
   * <p>Space Complexity: The space complexity is O(M) for storing the intervals.
   */
  public static int findMinMachines(int n, int[][] pairs) {
    List<int[]> intervals = new ArrayList<>();
    // Create intervals based on the machine's index and range
    // Ensure the intervals do not exceed the slope boundaries [0, n]
    // for example (0, 2) on a slope of length 3 would cover [0, 2]
    // (1, 1) would cover [0, 2]
    // (3, 1) would cover [2, 4]
    // (4, 1) would cover [3, 5]
    // Thus, the intervals would be [0, 2], [0, 2], [2, 4], [3, 5]
    for (int[] pair : pairs) {
      int index = pair[0];
      int range = pair[1];
      int start = Math.max(0, index - range);
      int end = Math.min(n, index + range);
      intervals.add(new int[] {start, end});
    }
    // Sort intervals by their start point
    intervals.sort(Comparator.comparingInt(a -> a[0]));

    int machinesNeeded = 0;
    int currentCoverage = 0;
    int i = 0;

    while (currentCoverage < n) {
      int maxReach = currentCoverage;
      boolean foundCoverage = false;

      // Find the interval that extends the coverage the farthest
      while (i < intervals.size() && intervals.get(i)[0] <= currentCoverage) {
        // Update maxReach if this interval extends our coverage
        if (intervals.get(i)[1] > maxReach) {
          maxReach = intervals.get(i)[1];
          // We found at least one interval that can extend our coverage
          foundCoverage = true;
        }
        // Move to the next interval
        i++;
      }

      // If no progress was made, it's impossible to cover the slope
      if (maxReach == currentCoverage) {
        return -1;
      }

      // We have selected a machine, so increment the count
      machinesNeeded++;
      // Update currentCoverage to the farthest point we can reach
      currentCoverage = maxReach;
    }

    return machinesNeeded;
  }

  public static void main(String[] args) {
    int[][] pairs = {{0, 1}, {1, 1}, {2, 1}};
    System.out.println(SkiSlopeRange.findMinMachines(2, pairs));

    pairs = new int[][] {{0, 2}};
    System.out.println(SkiSlopeRange.findMinMachines(3, pairs));

    pairs = new int[][] {{0, 2}, {1, 1}, {3, 1}, {4, 1}};
    System.out.println(SkiSlopeRange.findMinMachines(5, pairs));
  }
}
