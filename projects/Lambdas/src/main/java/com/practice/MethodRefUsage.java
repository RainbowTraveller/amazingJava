import java.util.*;

class MethodRefUsage {
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

  public static void greetPerson(Person person) {
    System.out.print("Hello ");

    if(person.getGender() == Gender.FEMALE)
      System.out.print("Ms. ");
    else
      System.out.print("Mr. ");
    System.out.println(person.getName());
  }

  public static void main(String[] args) {
    List<Person> people = createPeople();

    // people.forEach(person -> greetPerson(person));
    people.forEach(Sample::greetPerson);
  }
}
