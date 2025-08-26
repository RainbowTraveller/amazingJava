import java.util.ArrayList;
import java.util.List;

/*
 * Here a set of n numbers is given
 * These range between 1 to n
 * Some of the numbers in the range are missing, which also means
 * that few of these numbers occur multiple times
 * there are no negative numbers
 * Goal : Find all the missing numbers
 */
public class MissingNumbers {
  public static void main(String[] args) {
    int[] input = {4, 3, 2, 7, 8, 2, 3, 1};
    System.out.println(MissingNumbers.findDisappearedNumbers(input));
    input = new int[] {4, 3, 4, 7, 8, 7, 3, 1};
    System.out.println(MissingNumbers.findDisappearedNumbers(input));
    input = new int[] {4, 3, 5, 7, 8, 6, 3, 1};
    System.out.println(MissingNumbers.findDisappearedNumbers(input));
    input = new int[] {4, 3, 4, 7, 1, 2, 3, 1};
    System.out.println(MissingNumbers.findDisappearedNumbers(input));
  }

  /**
   * The approach is to use the input array itself to mark the numbers which are present For each
   * number we encounter, we mark the number at the index corresponding to that number as negative
   * If we encounter the number again, we don't change the sign At the end, the indices which have
   * positive numbers correspond to the missing numbers
   *
   * <p>Time Complexity : O(n) Space Complexity : O(1)
   *
   * @param nums input array
   * @return list of missing numbers
   */
  public static List<Integer> findDisappearedNumbers(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      // get each value
      int availableNo = Math.abs(nums[i]) - 1;
      // Mark value at this index as -ve don't care what that value is
      if (nums[availableNo] > 0) {
        nums[availableNo] = (-1 * nums[availableNo]);
      }
    }
    List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] >= 0) {
        result.add(i + 1);
      }
    }
    return result;
  }
}
