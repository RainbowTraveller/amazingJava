/*

We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

badge_records_1 = [
  ["Martha",   "exit"],
  ["Paul",     "enter"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],
  ["Paul",     "enter"],
  ["Curtis",   "exit"],
  ["Curtis",   "enter"],
  ["Paul",     "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
  ["Paul",     "enter"],
  ["Paul",     "enter"],
  ["Martha",   "exit"],
]
                  exit incorrectly
Expected output: ["Curtis", "Paul"], ["Martha", "Curtis"]

Additional test cases:

badge_records_2 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], []

badge_records_3 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: [], ["Paul"]

badge_records_4 = [
  ["Paul", "exit"],
  ["Paul", "enter"],
  ["Martha", "enter"],
  ["Martha", "exit"],
]

Expected output: ["Paul"], ["Paul"]

badge_records_5 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: [], []

badge_records_6 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], ["Paul"]


n: length of the badge records array

[[Curtis, Paul], [Curtis, Martha]]
[[Paul], []]
[[], [Paul]]
[[Paul], [Paul]]
[[], []]
[[Paul], [Paul]]
*/

import java.io.*;
import java.util.*;

public class EntryExitViolations {
  public static final String ENTER = "enter";
  public static final String EXIT = "exit";

  public static void main(String[] argv) {
    String badgeRecords1[][] = new String[][] {
      {"Martha",   "exit"},
      {"Paul",     "enter"},
      {"Martha",   "enter"},
      {"Martha",   "exit"},
      {"Jennifer", "enter"},
      {"Paul",     "enter"},
      {"Curtis",   "exit"},
      {"Curtis",   "enter"},
      {"Paul",     "exit"},
      {"Martha",   "enter"},
      {"Martha",   "exit"},
      {"Jennifer", "exit"},
      {"Paul",     "enter"},
      {"Paul",     "enter"},
      {"Martha",   "exit"},
    };

    String badgeRecords2[][] = new String[][] {
      {"Paul", "enter"},
      {"Paul", "enter"},
      {"Paul", "exit"},
    };

    String badgeRecords3[][] = new String[][] {
      {"Paul", "enter"},
      {"Paul", "exit"},
      {"Paul", "exit"},
    };

    String badgeRecords4[][] = new String[][] {
      {"Paul", "exit"},
      {"Paul", "enter"},
      {"Martha", "enter"},
      {"Martha", "exit"},
    };

    String badgeRecords5[][] = new String[][] {
      {"Paul", "enter"},
      {"Paul", "exit"},
    };

    String badgeRecords6[][] = new String[][] {
      {"Paul", "enter"},
      {"Paul", "enter"},
      {"Paul", "exit"},
      {"Paul", "exit"},
    };

    List<List<String>> desiredList;
    desiredList = getWrongEntryEmployees(badgeRecords1);
    System.out.println(desiredList);

    desiredList = getWrongEntryEmployees(badgeRecords2);
    System.out.println(desiredList);

    desiredList = getWrongEntryEmployees(badgeRecords3);
    System.out.println(desiredList);

    desiredList = getWrongEntryEmployees(badgeRecords4);
    System.out.println(desiredList);

    desiredList = getWrongEntryEmployees(badgeRecords5);
    System.out.println(desiredList);

    desiredList = getWrongEntryEmployees(badgeRecords6);
    System.out.println(desiredList);
  }

  public static List<List<String>> getWrongEntryEmployees(String[][] entries) {
    Set<String> exit = new HashSet<>();
    Set<String> enter = new HashSet<>();
    Map<String, String> tracker = new HashMap<>();
    for(String[] currentEntry : entries) {
      String employee = currentEntry[0];
      String status = currentEntry[1];
      //System.out.println(employee + " " + status);
      if(tracker.containsKey(employee)) {
        String oldStatus = tracker.get(employee);
        if(oldStatus.equals(status)) {
          if(oldStatus.equals(ENTER)) {
            exit.add(employee);
          } else {
            enter.add(employee);
          }
        }
      } else {
        if(status.equals(EXIT)) {
          enter.add(employee);
        }
      }
      tracker.put(employee, status);
    }

    for(Map.Entry<String,String> lastEntry : tracker.entrySet()) {
        String employee = lastEntry.getKey();
        String status = lastEntry.getValue();
        if(status.equals(ENTER)) {
            exit.add(employee);
          }
    }

    List<List<String>> violations = new LinkedList<>();
    violations.add(new LinkedList<String>(exit));
    violations.add(new LinkedList<String>(enter));
    return violations;

  }
}
