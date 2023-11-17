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

    static void findMostFrequent (String[] inputStrings) {
        Set<Character> uniqueCharacters = getUniqueKeys(inputStrings);
        Map<Character, Map<Character, Integer>> frequencyTracker = getFrquencyMap(inputStrings, uniqueCharacters);
//        System.out.println(frequencyTracker);
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
            Map<Character, Integer> currFreqMap = frequencyTracker.get(c);
            List<Map.Entry<Character, Integer>> currFreqList = new LinkedList<>();
            currFreqList.addAll(currFreqMap.entrySet());
            Collections.sort(currFreqList, (i1, i2) -> (i2.getValue() - i1.getValue()));
//            System.out.println(currFreqList);
            int max = Integer.MIN_VALUE;
            List<Character> finalList = new LinkedList<>();

            for (Map.Entry<Character, Integer> currMapEntry : currFreqList) {
//                System.out.println(currMapEntry.getKey() + " :: " + currMapEntry.getValue());
                char currKey = currMapEntry.getKey();
                if (max == Integer.MIN_VALUE) {
                    finalList.add(currKey);
//                    System.out.println("First : " + currKey);
                    max = currMapEntry.getValue();
                } else {
                    if (currMapEntry.getValue() == max) {
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

    public static Map<Character, Map<Character, Integer>> getFrquencyMap (String[] inputStrings, Set<Character> uniqueCharacters) {
        Map<Character, Map<Character, Integer>> frequencyTracker = new HashMap<>();
        for (Character c : uniqueCharacters) {
//            System.out.println("MAIN : " + c);
            for (String currString : inputStrings) {
                if (currString.indexOf(c) >= 0) {
//                    System.out.println(currString);
                    Map<Character, Integer> currTracker = null;
                    for (int i = 0; i < currString.length(); i++) {
                        char currChar = currString.charAt(i);
                        if (currChar != c) {
//                            System.out.println(":::::::: CURR : " + currChar);
                            currTracker = frequencyTracker.getOrDefault(c, new HashMap<Character, Integer>());
                            currTracker.put(currChar, currTracker.getOrDefault(currChar, 0) + 1);
                            frequencyTracker.put(c, currTracker);
                        }
                    }
                }
            }
        }
//        System.out.println(frequencyTracker);
        return frequencyTracker;
    }

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


