import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Validates a list of geo-targeting options based on redundancy, conflict, and ordering rules. */
public class GeoValidator {

  /**
   * Checks if the given geo-targeting options are a valid combination.
   *
   * @param options A list of geo location strings.
   * @return true if the combination is valid, false otherwise.
   */
  public boolean isValid(List<String> options) {
    if (options == null || options.isEmpty()) {
      return true;
    }

    // Separate positive and negative locations and check for ordering rule.
    Set<String> negatives = new HashSet<>();
    Set<String> positives = new HashSet<>();
    boolean seenPositive = false;

    for (String option : options) {
      if (option.startsWith("!")) {
        if (seenPositive) {
          // Rule: Positive locations follow negative locations.
          // This means once a positive location is seen, no more negatives are allowed.
          return false;
        }
        negatives.add(option.substring(1));
      } else {
        seenPositive = true;
        positives.add(option);
      }
    }

    // Rule 1: No redundant locations.
    // Check for redundancy within the negative set.
    if (hasRedundancy(negatives)) {
      return false;
    }

    // Check for redundancy within the positive set.
    if (hasRedundancy(positives)) {
      return false;
    }

    // Rule 2 & 1 (cross-list): No conflicting locations & a specific cross-redundancy rule.
    for (String positive : positives) {
      for (String negative : negatives) {
        // Remove the '!' from the negative location for comparison.
        String negativeStripped = negative;

        // Rule: No conflicting locations.
        // A conflict exists if a positive location is a sub-location of a negative one.
        // E.g., positive="na.us", negative="!na". The positive is a child of the negative.
        if (isPrefix(positive, negativeStripped)) {
          return false;
        }

        // Rule: "if you have na, then !eu is also redundant."
        // This means if a positive continent exists, any other negative continent is redundant.
        if (isContinent(positive)
            && isContinent(negativeStripped)
            && !positive.equals(negativeStripped)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Helper function to check for redundancy within a set of locations. A location is redundant if a
   * more general location (a prefix) is also present. e.g., "na" and "na.us.ca" are redundant.
   *
   * @param locations A set of location strings.
   * @return true if redundancy is found, false otherwise.
   */
  private boolean hasRedundancy(Set<String> locations) {
    List<String> locationList = new ArrayList<>(locations);
    for (int i = 0; i < locationList.size(); i++) {
      for (int j = i + 1; j < locationList.size(); j++) {
        String loc1 = locationList.get(i);
        String loc2 = locationList.get(j);
        if (isPrefix(loc2, loc1) || isPrefix(loc1, loc2)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if a potential child location is a sub-location (prefix) of a parent location. The check
   * must be exact to avoid false positives (e.g., "us" is not a prefix of "usa").
   *
   * @param child The potential sub-location string.
   * @param parent The potential parent location string.
   * @return true if child is a sub-location of parent, false otherwise.
   */
  private boolean isPrefix(String child, String parent) {
    if (child.length() <= parent.length()) {
      return false;
    }
    return child.startsWith(parent + ".");
  }

  /**
   * Checks if a location string represents a continent (single segment).
   *
   * @param location The location string.
   * @return true if it's a continent, false otherwise.
   */
  private boolean isContinent(String location) {
    return location.indexOf('.') == -1;
  }

  public static void main(String[] args) {
    GeoValidator validator = new GeoValidator();

    // Example 1: Valid
    List<String> example1 = List.of("na.us.ca", "na.us.ny", "as", "!na.us.ca.la", "!as.jp");
    System.out.println("Example 1 is valid: " + validator.isValid(example1)); // Expected: true

    // Example 2: Conflicting (na and !na)
    List<String> example2 = List.of("na", "!na");
    System.out.println("Example 2 is valid: " + validator.isValid(example2)); // Expected: false

    // Example 3: Redundancy (na.us and na)
    List<String> example3 = List.of("na.us", "na");
    System.out.println("Example 3 is valid: " + validator.isValid(example3)); // Expected: false

    // Example 4: Order violation (!na and then na)
    List<String> example4 = List.of("!na", "na");
    System.out.println(
        "Example 4 is valid: " + validator.isValid(example4)); // Expected: true (Correct order)

    // Example 5: Order violation (na then !na)
    List<String> example5 = List.of("na", "!na");
    System.out.println("Example 5 is valid: " + validator.isValid(example5)); // Expected: false
  }
}
