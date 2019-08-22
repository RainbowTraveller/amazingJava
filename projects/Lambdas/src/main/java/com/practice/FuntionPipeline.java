import java.util.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

class FuntionPipeline {
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
    //Given a list of people, create a sorted list of names,
    //based on age, of male over 20 years

    // List<Person> people = createPeople();
    //
    // Collections.sort(people, new Comparator<Person>() {
    //   public int compare(Person p1, Person p2) {
    //     return Integer.valueOf(p1.getAge()).compareTo(p2.getAge());
    //   }
    // });
    //
    // List<String> names = new ArrayList<>();
    // for(Person person : people) {
    //   if(person.getAge() > 20 && person.getGender() == Gender.MALE)
    //     names.add(person.getName());
    // }
    //
    // System.out.println(names);

    System.out.println(
      createPeople().stream()
                    .filter(person -> person.getGender() == Gender.MALE)
                    .filter(person -> person.getAge() > 20)
                    .sorted(comparing(Person::getAge))
                    .map(Person::getName)
                    .collect(toList()));
  }
}
