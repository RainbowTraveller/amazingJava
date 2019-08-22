import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Cascading {
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
    List<Person> people = createPeople();

    //Bad practice. Error prone, can't be parallelized if we ever need to.
    // List<String> names = new ArrayList<>();
    //
    // people.stream()
    //       .filter(person -> person.getAge() > 20)
    //       .filter(person -> person.getGender() == Gender.FEMALE)
    //       .map(Person::getName)
    //       .map(String::toUpperCase)
    //       .forEach(name -> names.add(name));
    //
    // System.out.println(names);

    List<String> names =
      people.stream()
            .filter(person -> person.getAge() > 20)
            .filter(person -> person.getGender() == Gender.FEMALE)
            .map(Person::getName)
            .map(String::toUpperCase)
            .collect(toList());

    System.out.println(names);
  }
}
