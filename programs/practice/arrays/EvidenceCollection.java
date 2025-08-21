import java.io.*;
import java.util.*;

public class EvidenceCollection {

  /*
  Imagine you are Patrick, a passionate policeman in search of evidence at a crime scene.

  You’re excited to upload all the evidence you find to Evidence.com, so you'd like to determine
  the best route to each piece of evidence you can reach.

  Through your travels of the crime scene, you may encounter various things:

  * ‘ ’ (space) represents an empty space in the crime scene. (Cost : 1 )
  * 'P' denotes Patrick's initial location.
  * Letters 'A' through 'J' denote various pieces of evidence on the crime scene.
  * 'O' denotes buildings that may not be entered.
  * 'W' denotes water, which may be passed but at a different cost. ( Cost : 5 )

  Write a program that outputs the lowest cost distance from Patrick to each piece of evidence.


  1. 2D grid
  2. Things scattered in the grid
  3. A -> shortest Path Cost , B --> SPC

  1. Scan the grid : find starting location
  2. scan in all 4 directions one by one
  3. Map evidence to cost
  4. Restore original water or land after traversing

    */

  public static void main(String[] args) {

    char[][] input = {
      {'A', ' ', 'W', 'W', 'W'},
      {'W', ' ', 'B', 'W', ' '},
      {' ', 'P', ' ', ' ', 'W'},
      {'D', 'C', 'W', 'J', ' '},
      {'W', ' ', ' ', 'W', 'E'},
    };

    EvidenceCollection.printResult(EvidenceCollection.patrickTravels(input));
  }

  /**
   * Calculates the lowest cost distance from Patrick to each piece of evidence in the crime scene.
   *
   * @param input 2D grid representing the crime scene
   * @return Map of evidence characters to their lowest cost distance from Patrick
   */
  public static Map<Character, Integer> patrickTravels(char[][] input) {

    Map<Character, Integer> costs = new HashMap<>();
    Set<Character> possibleEvidences = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      possibleEvidences.add((char) ('A' + i));
    }

    if (input != null && input.length > 0) {
      // Find start location
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[0].length; j++) {
          if (input[i][j] == 'P') {
            processCrimeScene(input, i, j, costs, possibleEvidences, 0);
          }
        }
      }
    }
    return costs;
  }

  /**
   * Prints the evidence and their corresponding costs.
   *
   * @param input Map of evidence characters to their costs
   */
  public static void printResult(Map<Character, Integer> input) {

    for (Character key : input.keySet()) {
      System.out.println("Evidence : " + key + " Cost :" + input.get(key));
    }
  }

  /**
   * Recursively processes the crime scene to find the lowest cost distance to each piece of
   * evidence.
   *
   * @param input 2D grid representing the crime scene
   * @param x Current x-coordinate
   * @param y Current y-coordinate
   * @param costs Map of evidence characters to their lowest cost distance from Patrick
   * @param possibleEvidences Set of characters representing possible evidence
   * @param currCost Current accumulated cost
   */
  public static void processCrimeScene(
      char[][] input,
      int x,
      int y,
      Map<Character, Integer> costs,
      Set<Character> possibleEvidences,
      int currCost) {
    // Check if out of bound : don't proceed

    if (x < 0 || x > input.length - 1 || y < 0 || y > input[0].length - 1) {
      return;
    }
    // Check if O : building : don't proceed
    if (input[x][y] == 'O' || input[x][y] == '$') {
      return;
    }

    char currValue = input[x][y];
    // possible evendence

    /*
        store the lowest cost or add new entry
    */

    if (possibleEvidences.contains(currValue)) {
      int oldCost = costs.getOrDefault(currValue, Integer.MAX_VALUE);
      costs.put(currValue, Math.min(currCost + 1, oldCost));
    }

    // Calculate the new cost
    if (currValue == ' ') {
      currCost += 1;
    } else if (currValue == 'W') {
      currCost += 5;
    }
    // Mark as visited
    input[x][y] = '$';

    // Go in 4 directions

    processCrimeScene(input, x + 1, y, costs, possibleEvidences, currCost);
    processCrimeScene(input, x - 1, y, costs, possibleEvidences, currCost);
    processCrimeScene(input, x, y + 1, costs, possibleEvidences, currCost);
    processCrimeScene(input, x, y - 1, costs, possibleEvidences, currCost);

    // Restore original land feature
    input[x][y] = currValue;
  }
}
