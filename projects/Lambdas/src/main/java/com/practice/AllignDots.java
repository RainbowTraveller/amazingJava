import java.util.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

class AllignDots {
  public static List<Person> createPeople() {
    return Arrays.asList(
	    new Person("Sara", Gender.FEMALE, 20),
	    new Person("Sara", Gender.FEMALE, 22),
	    new Person("Bob", Gender.MALE, 20),
	    new Person("Paula", Gender.FEMALE, 32),
	    new Person("Paul", Gender.MALE, 32),
	    new Person("Jack", Gender.MALE, 2),
	    new Person("Jack", Gender.MALE, 72),
	    new Person("Jill", Gender.FEMALE, 12)
	  );
  }

  public static void main(String[] args) {
    // System.out.println(
    //   createPeople().stream() .filter(person -> person.getGender() == Gender.MALE) .filter(person -> person.getAge() > 20) .sorted(comparing(Person::getAge)) .map(Person::getName) .collect(toList()));

    System.out.println(
      createPeople().stream()
                    .filter(person -> person.getGender() == Gender.MALE)
                    .filter(person -> person.getAge() > 20)
                    .sorted(comparing(Person::getAge))
                    .map(Person::getName)
                    .collect(toList()));
  }
}
