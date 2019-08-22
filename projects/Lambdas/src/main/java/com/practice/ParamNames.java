import java.util.*;

class ParamNames {
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

    //How readable is this code?
    System.out.println(
      people.stream()
            .map(p -> p.getAge())
            .filter(a -> a > 20)
            .reduce(0, (a, b) -> a + b));

    //Let's try this one:
    System.out.println(
      people.stream()
            .map((Person p) -> p.getAge())
            .filter((Integer a) -> a > 20)
            .reduce(0, (Integer a, Integer b) -> a + b));

    //Hum, p is a person, but a is what? Age, but that's not really clear even
    //though we have type

    //How about the following:
    System.out.println(
      people.stream()
            .map(person -> person.getAge())
            .filter(age -> age> 20)
            .reduce(0, (sum, age) -> sum + age));
            //or
            //.reduce(0, Integer::sum));
  }
}
