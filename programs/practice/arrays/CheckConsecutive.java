/*
 * In a given array of integers check if the numbers are consecutive
 */

class CheckConsecutive {
  public static void main(String[] argv) {
    int[] arr1 = {2, 5, 6, 3, 4};
    int[] arr2 = {3, 6, 7, 2, 9, 10};
    CheckConsecutive cc = new CheckConsecutive();
    // System.out.println(cc.getMax(arr1));
    // System.out.println(cc.getMin(arr1));
    System.out.println(cc.areConsecutive(arr1));
    System.out.println(cc.areConsecutive(arr2));
    System.out.println(cc.areConsecutiveOnlyMin(arr1));
    System.out.println(cc.areConsecutiveOnlyMin(arr2));
    System.out.println(cc.areConsecutiveAP(arr1));
    System.out.println(cc.areConsecutiveAP(arr2));
    System.out.println(cc.areConsecutiveXOR(arr1));
    System.out.println(cc.areConsecutiveXOR(arr2));
  }

  /**
   * Returns the minimum element in the array.
   *
   * @param arr The input array.
   * @return The minimum element in the array.
   * @throws NullPointerException if the input array is null.
   */
  int getMin(int[] arr) {
    if (arr != null) {
      int min = arr[0];
      for (int i = 1; i < arr.length; ++i) {
        if (arr[i] < min) {
          min = arr[i];
        }
      }
      return min;
    }
    throw new NullPointerException("NOT A VALID ARRAY OBJECT");
  }

  /**
   * Returns the maximum element in the array.
   *
   * @param arr The input array.
   * @return The maximum element in the array.
   * @throws NullPointerException if the input array is null.
   */
  int getMax(int[] arr) {
    if (arr != null) {
      int max = arr[0];
      for (int i = 1; i < arr.length; ++i) {
        if (arr[i] > max) {
          max = arr[i];
        }
      }
      return max;
    }
    throw new NullPointerException("NOT A VALID ARRAY OBJECT");
  }

  /**
   * Checks if the elements in the array are consecutive. 1. The maximum element minus the minimum
   * element plus one should equal the length of the array. 2. Each element should be unique and
   * within the range of the minimum and maximum values.
   *
   * @param arr The input array.
   * @return true if the elements are consecutive, false otherwise.
   */
  boolean areConsecutive(int[] arr) {
    int min = getMin(arr);
    int max = getMax(arr);
    if ((max - min + 1) == arr.length) {
      boolean[] visited = new boolean[arr.length];
      for (int i = 0; i < arr.length; ++i) {
        if (visited[arr[i] - min] == true) {
          return false;
        }
        visited[arr[i] - min] = true;
      }
      return true;
    }
    return false;
  }

  /**
   * Checks if the elements in the array are consecutive using only the minimum element. The sum of
   * the elements should equal the sum of the first n natural numbers starting from min.
   *
   * @param arr The input array.
   * @return true if the elements are consecutive, false otherwise.
   */
  boolean areConsecutiveOnlyMin(int[] arr) {
    int min = getMin(arr);
    int sum = 0;
    for (int i = 0; i < arr.length; ++i) {
      sum += arr[i];
    }
    for (int i = 0; i < arr.length; ++i) {
      sum -= (min + i);
    }
    return sum == 0;
  }

  /**
   * Checks if the elements in the array are consecutive using an arithmetic progression formula.
   * The sum of the elements should equal the sum of the first n natural numbers starting from min.
   *
   * @param arr The input array.
   * @return true if the elements are consecutive, false otherwise.
   */
  boolean areConsecutiveAP(int[] arr) {
    int min = getMin(arr);
    int n = arr.length;
    // sum = (n * (2 * first_term + (n - 1) * 1)) / 2;

    int sum = 0;
    int sumToNTermsUsingAP = (n * (2 * min + (n - 1) * 1)) / 2;

    for (int i = 0; i < arr.length; ++i) {
      sum += arr[i];
    }
    return sum == sumToNTermsUsingAP;
  }

  /**
   * Checks if the elements in the array are consecutive using XOR. The XOR of the elements should
   * equal the XOR of the first n natural numbers starting from min.
   *
   * @param arr The input array.
   * @return true if the elements are consecutive, false otherwise.
   */
  public boolean areConsecutiveXOR(int[] arr) {
    if (arr != null) {
      int min = getMin(arr);
      int max = getMax(arr);

      int x1 = arr[0];
      int x2 = min;

      for (int i = 1; i < arr.length; ++i) {
        x1 = x1 ^ arr[i];
      }

      for (int i = 1; i < arr.length; ++i) {
        x2 = x2 ^ (min + i);
      }

      return (x1 ^ x2) == 0;
    }
    return false;
  }
}
