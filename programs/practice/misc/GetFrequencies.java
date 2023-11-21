/*
Given an input list of strings, for each character appearing anywhere in the list, find the other character(s)that appear in the most number of strings with that character.

['abc', 'bcd', 'cde'] =>

 {
  a: [b, c],  # b appears in 1 string with a, c appears in 1 string with a
  b: [c],     # c appears in 2 strings with b. a and d each appear in only 1 string with b
  c: [b, d],  # b appears in 2 strings with c, d appears in 2 strings with c. But a and e each appear in only 1 string with c.
  d: [c],     # c appears in 2 strings with d. But b and e each appear in only 1 string with d.
  e: [c, d],  # c appears in 1 string with e, d appears in 1 string with e.
 }

 1. Scan all the strings
 2. Create a set of charaters ( unique chars to look at)
 3. Scan list for each char
    a. Collect other chars and their frequecies
 4. GO through this map and collect the final data
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GetFrequencies {

    /**
     * Acts as central point of processing and calls other methods to collect the processsed data
     *
     * @param inputStrings arrays of strings to be processed
     */
    static void findMostFrequent (String[] inputStrings) {
        //Get all unique chars
        Set<Character> uniqueCharacters = getUniqueKeys(inputStrings);
        //Gets map of a char and it's related chars and their frequencies
        Map<Character, Map<Character, Integer>> frequencyTracker = getFrquencyMap(inputStrings, uniqueCharacters);
//        System.out.println(frequencyTracker);
        //List of char and top related chars in terms of occurrence
        Map<Character, List<Character>> result = processFrequencies(frequencyTracker);
        for (char c : result.keySet()) {
            System.out.println(c);
            System.out.println(result.get(c));
            System.out.println();
        }
    }

    public static Map<Character, List<Character>> processFrequencies (Map<Character, Map<Character, Integer>> frequencyTracker) {
        Map<Character, List<Character>> result = new HashMap<>();
        for (char c : frequencyTracker.keySet()) {
//            System.out.println(c + " :::");
            //Get the relate char frequency map
            Map<Character, Integer> currFreqMap = frequencyTracker.get(c);
            //Create a list of Map.Entry
            List<Map.Entry<Character, Integer>> currFreqList = new LinkedList<>();
            //Add all the entries to this list
            currFreqList.addAll(currFreqMap.entrySet());
            //Sort the list based on the value and not key which is the frequcny of the char
            Collections.sort(currFreqList, (i1, i2) -> (i2.getValue() - i1.getValue()));
//            System.out.println(currFreqList);
            int max = Integer.MIN_VALUE;
            List<Character> finalList = new LinkedList<>();

            for (Map.Entry<Character, Integer> currMapEntry : currFreqList) {
//                System.out.println(currMapEntry.getKey() + " :: " + currMapEntry.getValue());
                char currKey = currMapEntry.getKey();
                if (max == Integer.MIN_VALUE) { // first value as it is with greatest freq
                    finalList.add(currKey);
//                    System.out.println("First : " + currKey);
                    max = currMapEntry.getValue();// note this frequency
                } else {
                    if (currMapEntry.getValue() == max) { // when frequecy matches record the char
//                        System.out.println("Next : " + currKey);
                        finalList.add(currKey);
                    }
                }
            }

//            System.out.println("FINAL LIST TO ADD  : " + finalList);
            result.put(c, finalList);
        }
        return result;
    }

    /**
     * Creats a map where each unique char acts as a key.
     * The value is again a map where a related char is key and its value is frequncy of those chars
     *
     * @param inputStrings     array of strings to be processed
     * @param uniqueCharacters set of all chars observed across all strings
     * @return mappping of each char to related chars with their frequencies
     */
    public static Map<Character, Map<Character, Integer>> getFrquencyMap (String[] inputStrings, Set<Character> uniqueCharacters) {
        Map<Character, Map<Character, Integer>> frequencyTracker = new HashMap<>();
        for (Character c : uniqueCharacters) {
//            System.out.println("MAIN : " + c);
            //Ignore the string if the current char is not present in a string
            for (String currString : inputStrings) {
                if (currString.indexOf(c) >= 0) {
//                    System.out.println(currString);
                    Map<Character, Integer> currTracker = null;
                    for (int i = 0; i < currString.length(); i++) {
                        char currChar = currString.charAt(i);
                        if (currChar != c) {// only char not matching current char for which map is being prepared
//                            System.out.println(":::::::: CURR : " + currChar);
                            //Get the map corr. to the char
                            currTracker = frequencyTracker.getOrDefault(c, new HashMap<Character, Integer>());
                            //Adjust the frequency of the related char
                            currTracker.put(currChar, currTracker.getOrDefault(currChar, 0) + 1);
                            // restore map into the tracker
                            frequencyTracker.put(c, currTracker);
                        }
                    }
                }
            }
        }
//        System.out.println(frequencyTracker);
        return frequencyTracker;
    }

    /**
     * Returns a set of chars occurring across all strings
     *
     * @param inputStrings array of string to be processed
     * @return a collection of unique chars accurrins across all strings
     */
    public static Set<Character> getUniqueKeys (String[] inputStrings) {
        Set<Character> uniqueCharacters = new HashSet<>();
        for (String input : inputStrings) {
            char[] inputChars = input.toCharArray();
            for (int i = 0; i < inputChars.length; ++i) {
                uniqueCharacters.add(inputChars[i]);
            }
        }
//        System.out.println(uniqueCharacters);
        return uniqueCharacters;
    }

    public static void main (String[] args) {
        String[] input = {"abc", "bcd", "cde"};
        findMostFrequent(input);
    }
}


