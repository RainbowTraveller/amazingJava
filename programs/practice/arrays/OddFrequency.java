/*
 * Given an integer array find out a number which occurs odd no. of times
 * in the array
 *
 */
public class OddFrequency {

  public int findNoWithOddFrequency(int[] numbers) {
    int mask = -1;
    if (numbers != null) {
      mask = numbers[0];
      for (int i = 1; i < numbers.length; ++i) {
        mask = mask ^ numbers[i];
      }
    }
    return mask;
  }

  public static void main(String[] args) {
    // int [] arr = {1,4,5,4,5,6,2,8,7,3,9,2,5,1,6,7,8,3,9};
    // int [] arr = {1,3,5,7,1,3,7,5,7};
    int[] arr = {1, 3, 5, 7, 1, 1, 3, 2};
    OddFrequency of = new OddFrequency();
    System.out.println(of.findNoWithOddFrequency(arr));
  }
}
