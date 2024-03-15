import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FunctionalProgOdd {
  public static void main(String[] args) {
    List<String> courses =
        List.of(
            "Spring",
            "Spring Boot",
            "Spring JPA",
            "Spring MVC",
            "Kubernetes",
            "Algorithm",
            "Golang",
            "Java",
            "Real Time Streaming",
            "MicroServices",
            "DevOps",
            "Systems Programming",
            "aws",
            "C",
            "C++",
            "Go");
//    printNumbersOdd(List.of(1, 2, 3, 4, 5, 6, 7));
//    printCourses(courses);
//    printCoursesSpring(courses);
//    printCoursesFour(courses);
//    printCoursesiCharNum(courses);
    optionalDemo();
  }

  public static void printNumbersOdd(List<Integer> nums) {
    // if it was a multiline function we can also use
    // filter(ClassName::method)
    nums.stream().filter(a -> a % 2 != 0).map(x -> x * x).forEach(System.out::println);
  }

  public static void printCourses(List<String> courses) {
    System.out.println("---Printing all course---");
    courses.stream().forEach(System.out::println);
  }

  public static void printCoursesSpring(List<String> courses) {
    System.out.println("---Printing Spring course---");
    courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
  }

  public static void printCoursesFour(List<String> courses) {
    System.out.println("---Printing atleast 4 letter name course---");
    courses.stream().filter(course -> course.length() >= 4).forEach(System.out::println);
  }

  public static void printCoursesiCharNum(List<String> courses) {
    System.out.println("---Printing no of chars in the name ---");
    courses.stream().map(String::length).forEach(System.out::println);
  }

  public static void optionalDemo() {
    List<String> fruits = List.of("banana", "mango", "apple");
//    Predicate<? super String> predicate = fruit -> fruit.startsWith("b");
    Predicate<? super String> predicate = fruit -> fruit.startsWith("k");
    Optional<String> optional = fruits.stream().filter(predicate).findFirst();
    System.out.println(optional);
    System.out.println(optional.isEmpty());
    System.out.println(optional.isPresent());
    System.out.println(optional.get());
    Optional<String> fruit = Optional.of("mango");
    Optional<String> empty = Optional.empty();
  }
}
