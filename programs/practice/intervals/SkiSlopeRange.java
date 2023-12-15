import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
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

  public static int findMinMachines(int n, int[][] pairs) {
    // Get Ranges based on the input data : slope and range pairs
    int[][] ranges = getRanges(pairs);
    //  Range are sorted based on the slope number
    // it should start with the 0 as it should start covering from 0 if not it's error
    if (ranges[0][0] != 0) {
      return -1;
    }
    int i = 1; // index
    int cnt = 0; // Initialize to 1
    int end = ranges[0][1]; // end of the first range
    // Get maxmum range for the starting 0
    while (i < ranges.length && ranges[i][0] == 0 && ranges[i][1] >= end) {
      end = ranges[i][1];
      i++;
    }
    // did we already find the range
    if (end == n) {
      return cnt + 1;
    }
    // We already have convered till the end, next target is next slope
    int desired = end + 1;
    end = Integer.MAX_VALUE;
    while (i < ranges.length) {
      int offset = 0;
      while (i < ranges.length && ranges[i][0] >= desired && desired <= ranges[i][1]) {
        if (end < ranges[i][1]) {
          end = ranges[i][1];
          if (end == n) {
            return cnt + 1;
          }
        }
        offset = 1;
        i++;
      }
      if(offset == 0) {
        i++;
      }
      cnt += offset;
      desired = end + 1;
    }
    //    while (i < ranges.length) {
    //      while (i < ranges.length && ranges[i][0] >= desired && desired <= ranges[i][1]) {
    //        if (end == Integer.MAX_VALUE) {
    //          cnt++;
    //          end = ranges[i][1];
    //        } else if (end < ranges[i][1]) {
    //          end = ranges[i][1];
    //          if (end == n) {
    //            return cnt + 1;
    //          }
    //        }
    //        i++;
    //      }
    //      desired = end + 1;
    //    }
    return desired == n ? -1 : cnt + 1;
  }

  /**
   * Create ranges from the given data Pairs are in the form (slope Index , range) So the range will
   * be slope Index - range and slope index + range It means a machine at slope index can clean this
   * much range of slope indexes
   *
   * <p>Special case : for 0th slope the range will be same as the actual value present in the pair
   *
   * @param pairs
   * @return
   */
  public static int[][] getRanges(int[][] pairs) {

    int len = pairs.length;
    // Range array
    int[][] ranges = new int[len][2];
    for (int i = 0; i < len; ++i) {

      if (pairs[i][0] == 0) { // No chamge for slope index 0
        ranges[i][0] = pairs[i][0];
        ranges[i][1] = pairs[i][1];
      } else {
        // Calculate the range
        ranges[i][0] = pairs[i][0] - pairs[i][1];
        ranges[i][1] = pairs[i][0] + pairs[i][1];
      }
    }
    // Sort range based on start index of the range, if equal consider the to range value
    Arrays.sort(ranges, (int[] r1, int[] r2) -> (r1[0] == r2[0] ? r1[1] - r2[1] : r1[0] - r2[0]));

    //    Arrays.stream(ranges).map(a -> new String(a[0] + " : " +
    // a[1])).forEach(System.out::println);

    return ranges;
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
