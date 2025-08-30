import java.util.Arrays;

public class SwapNumsToMakeSumEqual {
  /*
   * sumA - a + b = sumB - b + a
   * (sumA - sumB) / 2  =  a - b
   *
   */

  public int getTarget(int[] a, int[] b) {
    int sumB = 0, sumA = 0;

    sumA = Arrays.stream(a).sum();
    sumB = Arrays.stream(b).sum();

    if ((sumA - sumB) % 2 != 0) {
      return 0;
    }
    return (sumA - sumB) / 2;
  }

  public static void main(String args[]) {

    int[] a = {2, 4, 6, 9};
    int[] b = {3, 2, 5, 7};
    SwapNumsToMakeSumEqual s = new SwapNumsToMakeSumEqual();
    int target = s.getTarget(a, b);
    if (target == 0) {
      System.out.println("Can not find a pair");
    }
    Arrays.sort(a);
    Arrays.sort(b);
    int i = 0, j = 0;

    while (i < a.length && j < b.length) {
      if (a[i] - b[j] < target) {
        i++;
      } else if (a[i] - b[j] > target) {
        j++;
      } else {
        System.out.println("Elements a and b " + a[i] + " " + b[j]);
      }
    }
  }
}
