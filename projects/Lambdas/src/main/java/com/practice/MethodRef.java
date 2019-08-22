import java.util.*;

class MethodRef {
  public void printIt(int number) { System.out.println(number);}

  public void use(List<Integer> numbers) {
    //numbers.forEach(i -> printIt(i));
    numbers.forEach(this::printIt);
  }
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3);

    //routing as an argument to an instance method
    //numbers.forEach(i -> System.out.println(i));
    numbers.forEach(System.out::println);

    System.out.println("----");
    //routing as an argument to a static method
    // numbers.stream()
    //        .map(i -> Integer.lowestOneBit(i))
    //        .forEach(System.out::println);

    numbers.stream()
           .map(Integer::lowestOneBit)
           .forEach(System.out::println);

    System.out.println("----");
    //routing as a argument to an instance method on this - see inside use
    new Sample().use(numbers);

    System.out.println("----");
    //routing as a target
    // numbers.stream()
    //        .map(i -> i.doubleValue())
    //        .forEach(System.out::println);
    numbers.stream()
           .map(Integer::doubleValue)
           .forEach(System.out::println);

    System.out.println("----");
    //routing two parameters as arguments
    // System.out.println(
    //   numbers.stream()
    //          .reduce(0, (sum, i) -> Integer.sum(sum, i)));
    System.out.println(
      numbers.stream()
             .reduce(0, Integer::sum));

    System.out.println("----");
    //routing as a target and an argument
    // System.out.println(
    //   numbers.stream()
    //          .reduce(0, (prev, i) -> prev.compareTo(i)));

    System.out.println(
      numbers.stream()
             .reduce(0, Integer::compareTo));
  }
}
