import java.util.List;

public class FunctionalProg101 {
  public static void main(String[] args) {
    System.out.println("Traditionally");
    printNumbersTraditionally(List.of(1, 2, 3, 4, 5, 6, 7));
    System.out.println("Functionally");
    printNumbersFuntional(List.of(1, 2, 3, 4, 5, 6, 7));
    System.out.println("Functionally Filter : ");
    printNumbersFuntionalFilter(List.of(1, 2, 3, 4, 5, 6, 7));
  }

  public static void printNumbersTraditionally(List<Integer> nums) {
    // How to loop
    for (int num : nums) {
      if(num % 2 == 0)
        System.out.println(num);
    }
  }

  public static void printNum(int num) {
    System.out.println(num);
  }

  public static void printNumbersFuntional(List<Integer> nums) {
    System.out.println("Own Method");
    nums.stream().forEach(FunctionalProg101::printNum);
    System.out.println("Sysout");
    nums.stream().forEach(System.out::println);
  }

  public static void printNumbersFuntionalFilter(List<Integer> nums) {
    // if it was a multiline function we can also use
    // filter(ClassName::method)
    nums.stream().filter(a -> a % 2 == 0).forEach(System.out::println);
  }
}
