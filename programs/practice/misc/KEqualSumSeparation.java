import java.util.Arrays;

public class KEqualSumSeparation {
  public static void main(String[] args) {
    // False
    // [2,2,2,2,3,4,5]
    // 4
    // True
    // [129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22]
    // 3
    int[] arr = {129, 17, 74, 57, 1421, 99, 92, 285, 1276, 218, 1588, 215, 369, 117, 153, 22};
    KEqualSumSeparation findkseparations = new KEqualSumSeparation();
    System.out.println(findkseparations.canPartitionKSubsets(arr, 3));
  }

  public boolean canPartitionKSubsets(int[] arr, int k) {
    // The total sum to be considered
    int sumOfAllArrayItems = Arrays.stream(arr).sum();
    /*
     * 1. We can not have negative partitions
     * 2. We can not divide x no of elements into y parts where y > x
     *    e.g. divide 10 things into 100 groups ( as a whole and not part of a object should be considered )
     * 3. If sum can not be distributed equally into k parts, no need to proceed
     */
    if (k <= 0 || k > arr.length || sumOfAllArrayItems % k != 0) {
      return false;
    }

    return canPartition(0, arr, new boolean[arr.length], k, 0, sumOfAllArrayItems / k);
  }

  boolean canPartition(
      int iterationStart,
      int[] arr,
      boolean[] used,
      int k,
      int inProgressBucketSum,
      int targetBucketSum) {
    /*
     * This is top down approach. We start with k required partitions and when we get one
     * we reduce the k by 1.
     *
     * Now k = 1, which means that we have found k - 1 partitions already. which contain target sum
     * and we already made sure that the sum is evenly distributed into k parts. So if k - 1 partitions are
     * obtained, no need to process for last partitions, previously checked conditions indicate so.We have
     * made sure that K partitions are indeed possible
     *
     */
    if (k == 1) {
      return true;
    }

    // Check if for this partition, criteria is met
    // if yes then reduce the required target partitions, with targetSum remaining same
    if (inProgressBucketSum == targetBucketSum) {
      return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
    }
    /*
     * Crux of the solution:
     * essentially start from 0 and check which elements come together to add up and form desired sum
     * now when an element is considered, call recursively and start from that index onwards.
     * Also when an element is used mark it used.
     * But what if it fails to add up...that means the element you started with was not appropriate one
     * so be it ...don't consider it and mark it not used and try other elements
     * If satisfied though, 1 less bucket to consider out of k buckets
     */
    for (int i = iterationStart; i < arr.length; i++) {
      if (!used[i] && inProgressBucketSum + arr[i] <= targetBucketSum) {
        // Mark it used so nobody else in current iteration uses it
        used[i] = true;
        if (canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
          return true;
        }
        // this did not work, ok mark it not used
        used[i] = false;
      }
    }
    return false;
  }
}
