/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which are
 * not equal to val.
 *
 * <p>Consider the number of elements in nums which are not equal to val be k, to get accepted, you
 * need to do the following things:
 *
 * <p>Change the array nums such that the first k elements of nums contain the elements which are
 * not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [3,2,2,3], val = 3 Output: 2, nums = [2,2,_,_] Explanation: Your function should
 * return k = 2, with the first two elements of nums being 2. It does not matter what you leave
 * beyond the returned k (hence they are underscores). Example 2: i = 0 j = 3 if(nums[j] == val) j--
 * else swap(nums[i], nums[j]) i++
 *
 * <p>0 1 2 2 3 0 4 2 4 1 2 2 3 0 0 2 4 0 2 2 3 0 1 2 4 0 1 2 3 0 2 2 4 0 1 0 2 3 2 2 4 0 1 0 3 2 2
 * 2
 *
 * <p>Input: nums = [0,1,2,2,3,0,4,2], val = 2 Output: 5, nums = [0,1,4,0,3,_,_,_] Explanation: Your
 * function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order. It does not matter what you leave
 * beyond the returned k (hence they are underscores).
 */
public class RemoveDuplicates {

  public static void main(String[] args) {
    RemoveDuplicates removeDuplicates = new RemoveDuplicates();
    System.out.println(removeDuplicates.removeElement(new int[] {3, 2, 2, 3}, 3));
    System.out.println(removeDuplicates.removeElement(new int[] {1, 0, 2, 2, 3, 0, 4, 2}, 2));
    System.out.println(removeDuplicates.removeElement(new int[] {1, 0, 2, 2, 2, 0, 4, 2}, 2));
    System.out.println(removeDuplicates.removeElement(new int[] {3, 3}, 3));

    System.out.println(removeDuplicates.removeElementSwap(new int[] {3, 2, 2, 3}, 3));
    System.out.println(removeDuplicates.removeElementSwap(new int[] {1, 0, 2, 2, 3, 0, 4, 2}, 2));
    System.out.println(removeDuplicates.removeElementSwap(new int[] {1, 0, 2, 2, 2, 0, 4, 2}, 2));
    System.out.println(removeDuplicates.removeElementSwap(new int[] {3, 3}, 3));

    System.out.println(removeDuplicates.removeElementSimpler(new int[] {3, 2, 2, 3}, 3));
    System.out.println(
        removeDuplicates.removeElementSimpler(new int[] {1, 0, 2, 2, 3, 0, 4, 2}, 2));
    System.out.println(
        removeDuplicates.removeElementSimpler(new int[] {1, 0, 2, 2, 2, 0, 4, 2}, 2));
    System.out.println(removeDuplicates.removeElementSimpler(new int[] {3, 3}, 3));
  }

  /**
   * Two pointers
   *
   * <p>one to iterate through the array, the other to keep track of the position to insert the next
   * valid element.
   *
   * <p>When we find an element that is not equal to val, we copy it to the position indicated by
   * the second pointer and increment the second pointer.
   *
   * <p>This way, all elements not equal to val are moved to the front of the array.
   *
   * <p>Finally, we return the position of the second pointer, which represents the new length of
   * the array without the val elements.
   *
   * <p>Time complexity: O(n) Space complexity: O(1)
   */
  public int removeElement(int[] nums, int val) {
    int i = 0;
    int j = nums.length;
    while (i < j) {
      if (nums[i] == val) {
        nums[i] = nums[j - 1];
        j--;
      } else {
        i++;
      }
    }
    return j;
  }

  /**
   * Two pointers - swap
   *
   * <p>one pointer starts from the beginning (i) and the other from the end (j) of the array.
   *
   * <p>The idea is to move all occurrences of val to the end of the array by swapping elements.
   *
   * <p>When we find an element at index j that is equal to val, we simply decrement j to skip it.
   * If we find an element at index j that is not equal to val, we swap it with the element at index
   * i and increment i.
   *
   * <p>This way, all elements not equal to val are moved to the front of the array, and all
   * occurrences of val are moved to the end.
   *
   * <p>Finally, we return the position of pointer i, which represents the new length of the array
   * without the val elements.
   *
   * <p>Time complexity: O(n) Space complexity: O(1)
   */
  public int removeElementSwap(int[] nums, int val) {
    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      if (nums[j] == val) {
        j--;
      } else {
        // swap
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
      }
    }
    return i;
  }

  /**
   * Two pointers
   *
   * <p>one to iterate through the array, the other to keep track of the position to insert the next
   * valid element.
   *
   * <p>When we find an element that is not equal to val, we copy it to the position indicated by
   * the second pointer and increment the second pointer.
   *
   * <p>This way, all elements not equal to val are moved to the front of the array.
   *
   * <p>Finally, we return the position of the second pointer, which represents the new length of
   * the array without the val elements.
   *
   * <p>Time complexity: O(n) Space complexity: O(1)
   */
  public static int removeElementSimpler(int[] nums, int val) {
    int replacing = 0;
    int curr = 0;

    while (curr < nums.length) {
      if (nums[curr] != val) {
        nums[replacing] = nums[curr];
        replacing++;
      }
      curr++;
    }
    return replacing;
  }
}
