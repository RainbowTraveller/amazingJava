/*
 *     You are given two integer arrays nums1 and nums2,sorted in non-decreasing order,and two integers m and n,representing the number of elements in nums1 and nums2 respectively.
 *     Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *     The final sorted array should not be returned by the function,but instead be stored inside the array nums1.To accommodate this,nums1 has a length of m+n,where the first m elements denote the elements that should be merged,and the last n elements are set to 0and should be ignored.nums2 has a length of n.
 *     Example 1:
 *     Input:nums1=[1,2,3,0,0,0],m=3,nums2=[2,5,6],n=3
 *     Output:[1,2,2,3,5,6]
 *     Explanation:The arrays we are merging are[1,2,3]and[2,5,6].
 *     The result of the merge is[1,2,2,3,5,6]with the underlined elements coming from nums1.
 *     Example 2:
 *     Input:nums1=[1],m=1,nums2=[],n=0
 *     Output:[1]
 *     Explanation:The arrays we are merging are[1]and[].
 *     The result of the merge is[1].
 *     Example 3:
 *     Input:nums1=[0],m=0,nums2=[1],n=1
 *     Output:[1]
 *     Explanation:The arrays we are merging are[]and[1].
 *     The result of the merge is[1].
 *     Note that because m=0,there are no elements in nums1.The 0is only there to ensure the merge result can fit in nums1.
 */

public class MergeSortedArray {
  /*
  This can be solved in naive expensive ways
  1. Add all elements form num2 to num1 as it has enough capacity and sort using Arrays.sort
  */

  public static void main(String[] args) {

    MergeSortedArray mergeSortedArray = new MergeSortedArray();
    mergeSortedArray.merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Two pointer approach
    // Start from the end of both arrays and compare the elements and place the larger element at
    // the end of nums1
    // This way we don't need extra space and we can avoid overwriting elements in nums1
    // Pointer for nums1
    int p1 = m - 1;
    // Pointer for nums2
    int p2 = n - 1;

    for (int i = m + n - 1; i >= 0; --i) {
      if (p2 < 0) {
        break;
      }
      if (p1 >= 0 && nums1[p1] > nums2[p2]) {
        nums1[i] = nums1[p1];
        p1--;
      } else {
        nums1[i] = nums2[p2];
        p2--;
      }
    }
    System.out.println("Final Array");
    for (int i = 0; i < m + n; i++) {
      System.out.println(nums1[i]);
    }
  }
}
