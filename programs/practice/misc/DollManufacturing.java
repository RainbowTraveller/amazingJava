
import java.io.*;
import java.util.*;

/*

We have a bin of doll parts in a factory. Each part goes to a doll with a specific, unique name. Each part will be described by a string, with the name of the doll and the part name separated by an underscore, like "Mary_arm".

All dolls are made of the same types of parts, and we have a list of all of the parts that form a complete doll. Given a list of available parts, return a list of doll names for which will we be able to create at least one complete doll.

complete_part_list = "eyes,nose,mouth,ears"

parts = [
    "Bette_feet",
    "Bette_eyes",
    "Colleen_nose",
    "Astrid_eyes",
    "Doug_eyes",
    "Bette_nose",
    "Astrid_nose",
    "Doug_mouth",
    "Bette_ears",
    "Bette_mouth",
    "Colleen_nose",
    "Colleen_arms",
    "Astrid_feet",
    "Colleen_nose",
    "Colleen_mouth",
    "Doug_nose",
    "Doug_ears",
    "Astrid_hands",
    "Doug_eyes" ]

# Expected output (in any order):  [ "Doug", "Bette" ]

 */

class DollManufacturing {
  public static void main(String[] args) {

    String partList = "eyes,nose,mouth,ears";

    String[] parts = {
      "Bette_feet",
      "Bette_eyes",
      "Colleen_nose",
      "Astrid_eyes",
      "Doug_eyes",
      "Bette_nose",
      "Astrid_nose",
      "Doug_mouth",
      "Bette_ears",
      "Bette_mouth",
      "Colleen_nose",
      "Colleen_arms",
      "Astrid_feet",
      "Colleen_nose",
      "Colleen_mouth",
      "Doug_nose",
      "Doug_ears",
      "Astrid_hands",
      "Doug_eyes" };
    System.out.println(getCompleteDolls( parts, partList));

  }

  public static List<String> getCompleteDolls( String[] parts, String partList) {

    Map<String, Set<String>> dollTracker = new HashMap<String, Set<String>>();
    String[] stdParts = partList.split(",");
    Set<String> standard = new HashSet<String>(Arrays.asList(stdParts));

    for(String part : parts) {
      String[] details = part.split("_");
      String name = details[0];
      String bodyPart = details[1];
      //System.out.println("Name :" + name + " bodyPart : " + bodyPart);
      Set<String> bodyParts = dollTracker.getOrDefault(name, new HashSet<String>());
      bodyParts.add(bodyPart.trim());
      dollTracker.put(name, bodyParts);
    }
    List<String> completeDolls = new LinkedList<String>();

    for(String name : dollTracker.keySet()) {
      Set<String> currentParts = dollTracker.get(name);
      //System.out.println("Current : " + currentParts);
      //System.out.println("Standard  : " + standard);
      //System.out.println("Result  : " + currentParts.containsAll(standard));
      int count = 0;
      for(String stdPart : standard) {
        //System.out.println("Standard Part : " + stdPart);
        //System.out.println("Result  : " + currentParts.contains(stdPart));
        if(currentParts.contains(stdPart.trim())) {
          count++;
        }
      }

      if(count == standard.size()) {
        completeDolls.add(name);
      }

    }
    return completeDolls;

  }
}
