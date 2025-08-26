import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MergeKSortedArray {

  static class Tracker {
    int arrayIndex;
    int index;
    int element;

    public Tracker(int arrIndex, int index, int num) {
      this.arrayIndex = arrIndex;
      this.index = index;
      this.element = num;
    }

    public String toString() {
      return new String("ROW : " + arrayIndex + " Col :" + index + " NUM :" + element);
    }
  }

  /**
   * Merge k sorted arrays
   *
   * @param sortedArrs
   * @return
   */
  static List<Integer> mergeKSortedArrays(int[][] sortedArrs) {
    List<Integer> result = new LinkedList<>();
    int k = sortedArrs.length;
    // min heap based on the element value
    // k is the number of sorted arrays
    // k is also the maximum size of the heap
    // the heap will always contain the smallest element from each of the k arrays
    PriorityQueue<Tracker> pq = new PriorityQueue<>(k, (i1, i2) -> (i1.element - i2.element));
    for (int i = 0; i < k; i++) {
      Tracker currTracker = new Tracker(i, 0, sortedArrs[i][0]);
      pq.offer(currTracker);
    }
    // pq.stream().forEach(System.out::println);

    while (!pq.isEmpty()) {
      // having the smallest element at the top of the heap
      // this is the smallest element among all the k arrays
      Tracker cTracker = pq.poll();
      // Adding the smallest element to the result
      result.add(cTracker.element);
      // if there are more elements in the same array, add the next element to the heap
      int currArrayIndex = cTracker.arrayIndex;
      int currIndex = cTracker.index;
      // This check is important to avoid ArrayIndexOutOfBoundsException
      // We only add the next element from the same array if it exists
      // If we don't do this check, we might try to access an index that doesn't exist
      // and that will throw an exception
      if (sortedArrs[cTracker.arrayIndex].length > (currIndex + 1)) {
        // System.out.println(cTracker);
        pq.offer(
            new Tracker(currArrayIndex, currIndex + 1, sortedArrs[currArrayIndex][currIndex + 1]));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] a = {
      {2, 3, 4, 6, 7},
      {1, 5, 8, 9, 11},
      {0, 10, 12, 13, 14, 15}
    };
    List<Integer> result = MergeKSortedArray.mergeKSortedArrays(a);
    result.stream().forEach(System.out::println);
  }
}
