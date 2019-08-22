import java.util.*;

class Declarative {
  // public static void printMin(int[] values) { //oops, what if empty?
  //   int result = Integer.MAX_VALUE;
  //
  //   for(int e : values) {
  //     if(e < result)
  //       result = e;
  //   }
  //
  //   System.out.println(result);
  // }

  public static void printMin(int[] values) {
    System.out.println(Arrays.stream(values).min());
  }

  public static void main(String[] args) {
    int[] numbers = new int[] {10, 7, 8, 4, 8, 19, 12, 15};
    int[] empty = new int[] {};

    printMin(numbers);
    printMin(empty);
  }
}
