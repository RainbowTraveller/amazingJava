/**
 * Given 2 sorted arrays, find the median.
 *
 * <p>Simple way it to merge 2 arrays and then find middle element if the merged array has odd
 * length or return average of 2 middle elements if the final array has even number of elements But
 * this will take O(n) time.
 *
 * <p>To improve we
 */
public class MedianSortedArrays {
  public static void main(String[] args) {
    /*int [] x = {1, 3, 6, 9, 11, 16};
    int [] y = {-2, 5, 6, 7,14,35,38};
    */
    // 1, 2, 3, 5, 6, 6, 7, 8, 11, 14, 16, 33, 38
    // int [] x = {23, 25,27,30};
    // int [] y = {-2, 5, 6, 7,14};
    // -2, 5, 6, 7, 14, 23, 25, 27, 30

    int[] x = {1, 3};
    int[] y = {2};
    System.out.println("Median of 2 sorted arrays : " + findMedianLinear(x, y));
    System.out.println("Median of 2 sorted arrays : " + findMedian(x, y));
  }

  /*
   * Consider following 2 arrays
   * x = 1, 2, 3, 10, 14
   * y = 6, 7, 8, 9
   *
   * Merged : 1, 2, 3, 6, 7, 8, 9, 10, 14
   * length = 9 ==> odd ==> 9 / 2 = 4
   *
   * so loop thru the arrays from 0 to 4 ( 5 times ) and each time collect the lesser element and increment index
   * of that array. Hence is a way we are traversing the array from smallest element to largest using merge helper function
   * when any one of the array exhausts before the count is reached only other array will continue to serve elements
   * At count we have out desired median.
   *
   * x = 1, 2, 3, 10, 14
   * y = 6, 7, 8, 9, 15
   *
   * Merged : 1, 2, 3, 6, 7, 8, 9, 10, 14, 15
   * length = 10 ==> odd ==> 10 / 2 = 5
   *
   * Here logic remains the same, only difference is keep track of the previous element so that
   * result will be average of that and current element when the count it reached
   */
  public static double findMedianLinear(int[] x, int[] y) {
    int lx = x.length;
    int ly = y.length;
    int count = (lx + ly) / 2;
    boolean isEven = (lx + ly) % 2 == 0;
    int prev = -1, curr = -1;
    int indexX = 0, indexY = 0;
    if (!isEven) {
      for (int i = 0; i <= count; ++i) {
        if (indexX != lx && indexY != ly) {
          curr = x[indexX] > y[indexY] ? y[indexY++] : x[indexX++];
        } else if (indexX < lx) {
          curr = x[indexX++];
        } else {
          curr = y[indexY++];
        }
      }
      return curr;
    } else {
      for (int i = 0; i <= count; ++i) {
        prev = curr;
        if (indexX != lx && indexY != ly) {
          curr = x[indexX] > y[indexY] ? y[indexY++] : x[indexX++];
        } else if (indexX < lx) {
          curr = x[indexX++];
        } else {
          curr = y[indexY++];
        }
      }
      return (prev + curr) / 2D;
    }
  }

  public static double findMedian(int[] x, int[] y) {
    if (x != null && y != null) {
      int xLen = x.length;
      int yLen = y.length;
      int desiredPartition = (xLen + yLen + 1) / 2;
      boolean isOdd = (xLen + yLen) % 2 != 0;
      if (xLen > yLen) {
        return medianHelper(y, x, 0, yLen, desiredPartition, isOdd);
      }
      return medianHelper(x, y, 0, xLen, desiredPartition, isOdd);
    }
    return -1;
  }

  // When we look at the combined array as a whole, we divide it at the middle. Here partitionX and
  // partitionY indicate
  // no. of elements on the left side of the partition. So this partition is actually in between 2
  // numbers( WHAT...yeah for simplicity)
  // e.g. 2 4 6 9 13
  //      1 10 11
  //
  //      After partitioning
  //      leftMaxY | rightMinY
  //      2,4, 6 | 9, 13
  //      leftMaxX | rightMinX
  //      1 | 10, 11
  //    Notice here that total no. of elements is 8 which is even and each partition has 4 elements,
  // so we managed to get a point
  //    such that all elements in left array combined are smaller than respective smallest elements
  // in both the arrays on the right hand side
  //    if the length is odd we have 1 extra element on left side due to code on line 85
  //    apply formula in below code to get desired median
  //
  public static double medianHelper(
      int[] x, int[] y, int start, int end, int desiredPartition, boolean isOdd) {

    double median = -1;
    if (start <= end) {
      int partitionX = (start + end) / 2;
      int partitionY = desiredPartition - partitionX;

      // In case of an array on left is exhausted then no element for comparison
      // which is as good as having all elements less, so choose MIN
      int leftMaxX = partitionX == 0 ? Integer.MIN_VALUE : x[partitionX - 1];
      // If right array exhausts then all elements are greater so choose MAX
      int rightMinX = partitionX == x.length ? Integer.MAX_VALUE : x[partitionX];

      int leftMaxY = partitionY == 0 ? Integer.MIN_VALUE : y[partitionY - 1];
      int rightMinY = partitionY == y.length ? Integer.MAX_VALUE : y[partitionY];

      if (leftMaxX <= rightMinY && leftMaxY <= rightMinX) {
        if (isOdd) {
          median = Math.max(leftMaxY, leftMaxX);
        } else {
          median = (Math.max(leftMaxX, leftMaxY) + Math.min(rightMinX, rightMinY)) / 2D;
        }
      } else if (leftMaxX > rightMinY) {
        median = medianHelper(x, y, start, partitionX - 1, desiredPartition, isOdd);
      } else {
        median = medianHelper(x, y, partitionX + 1, end, desiredPartition, isOdd);
      }
    }
    return median;
  }
}
