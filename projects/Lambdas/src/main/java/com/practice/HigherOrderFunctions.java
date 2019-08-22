import java.util.*;
import java.util.function.*;

class HigherOrderFunctions {
  public static void main(String[] args) {
    //Higher-Order Functions
    //1. May pass a function to a function
    //2. May create a function within a function
    //3. May return a function from a function

    List<Integer> numbers = Arrays.asList(1, 2, 3);

    //1.
    numbers.forEach(i -> System.out.println(i));

    //2.
    Predicate<Integer> isEven = number -> number % 2 == 0;

    System.out.println(isEven.test(4));

    //3.
    Predicate<Integer> isGT4 = isGT(4);
    System.out.println(isGT4.test(5));

    Predicate<Integer> isGT14 = isGT(14);
    System.out.println(isGT14.test(5));
  }

  public static Predicate<Integer> isGT(int value) {
    return number -> number > value;
  }
}
