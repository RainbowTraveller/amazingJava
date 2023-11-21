/*
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words
in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below
or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates.
If there are multiple matches, return any one.

grid1 = [
    ['c', 'c', 'c', 'a', 'r', 's'],
    ['c', 'c', 'i', 't', 'n', 'b'],
    ['a', 'c', 'n', 'n', 't', 'i'],
    ['t', 'c', 'i', 'i', 'p', 't']
]

word1_1 = "catnip"
find_word_location(grid1, word1_1)-> [ (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 4) ]

word1_2 = "cccc"
find_word_location(grid1, word1_2)-> [(0, 1), (1, 1), (2, 1), (3, 1)]
OR [(0, 0), (1, 0), (1, 1), (2, 1)]
OR [(0, 0), (0, 1), (1, 1), (2, 1)]
OR [(1, 0), (1, 1), (2, 1), (3, 1)]


grid2 = [
    ['c', 'p', 'a', 'n', 't', 's'],
    ['a', 'b', 'i', 't', 'a', 'b'],
    ['t', 'f', 'n', 'n', 'c', 'i'],
    ['x', 's', 'c', 'a', 't', 'n'],
    ['x', 's', 'd', 'd', 'e', 'a'],
    ['s', 'q', 'w', 'x', 's', 'p']
]


word2 = "catnap"
find_word_location(grid2, word2)-> [ (3, 2), (3, 3), (3, 4), (3, 5), (4, 5), (5, 5) ]

grid3 = [
    ['c', 'r', 'c', 'a', 'r', 's'],
    ['a', 'b', 'i', 't', 'n', 'i'],
    ['t', 'f', 'n', 'n', 'x', 'p'],
    ['x', 's', 'i', 'x', 'p', 't']]
word3 = "catnip"
find_word_location(grid3, word3)-> [ (0, 2), (0, 3), (1, 3), (1, 4), (1, 5), (2, 5) ]

grid4 = [
    ["a","o","o","o","a","a"],
    ["b","b","i","t","n","i"],
    ["c","f","n","n","v","p"],
    ["o","a","a","a","o","o"]
]
word4_1 = "aaa"
word4_2 = "ooo"

find_word_location(grid4, word4_1)-> [ (3, 1), (3, 2), (3, 3) ]
find_word_location(grid4, word4_2)-> [ (0, 1), (0, 2), (0, 3) ]


n = number of rows
m = number of columns
w = length of the word
*/

import java.io.*;
import java.util.*;

public class WordJuggle {
    public static void main (String[] argv) {
        char[][] grid1 = {
                {'c', 'c', 'c', 'a', 'r', 's'},
                {'c', 'c', 'i', 't', 'n', 'b'},
                {'a', 'c', 'n', 'n', 't', 'i'},
                {'t', 'c', 'i', 'i', 'p', 't'}
        };
        String word1_1 = "catnip";
        String word1_2 = "cccc";

        char[][] grid2 = {
                {'c', 'p', 'a', 'n', 't', 's'},
                {'a', 'b', 'i', 't', 'a', 'b'},
                {'t', 'f', 'n', 'n', 'c', 'i'},
                {'x', 's', 'c', 'a', 't', 'n'},
                {'x', 's', 'd', 'd', 'e', 'a'},
                {'s', 'q', 'w', 'x', 's', 'p'}
        };
        String word2 = "catnap";

        char[][] grid3 = {
                {'c', 'r', 'c', 'a', 'r', 's'},
                {'a', 'b', 'i', 't', 'n', 'i'},
                {'t', 'f', 'n', 'n', 'x', 'p'},
                {'x', 's', 'i', 'x', 'p', 't'}
        };
        String word3 = "catnip";

        char[][] grid4 = {
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'b', 'b', 'i', 't', 'n', 'i'},
                {'c', 'f', 'n', 'n', 'v', 'p'},
                {'o', 'a', 'a', 'a', 'o', 'o'}
        };
        String word4_1 = "aaa";
        String word4_2 = "ooo";

        List<Position> result = findWordLocation(grid1, word1_1);
        result.stream()
                .forEach(System.out::println);
        result = findWordLocation(grid1, word1_2);
        System.out.println("------");
        result.stream()
                .forEach(System.out::println);

    }

    static class Position {
        private int row;
        private int col;

        public Position (int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString () {
            return "(" + row + ", " + col + ")";
        }

    }

    public static List<Position> findWordLocation (char[][] words, String ref) {
        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words[0].length; ++j) {
                if (words[i][j] == ref.charAt(0)) {
                    List<Position> positions = new LinkedList<Position>();
                    getWordPositions(words, ref, i, j, positions, 0);
                    if (positions.size() == ref.length()) {
                        return positions;
                    }
                }
            }
        }
        return null;
    }

    private static List<Position> getWordPositions (char[][] words, String ref, int row, int col, List<Position> positions,
                                                    int tracker) {
        //if out of bound return original positions found so far
        if (row < 0 || row >= words.length || col < 0 || col >= words[0].length) {
            return positions;
        }

        //if tracker has reached the end, we have found the desired path
        if (tracker == ref.length()) {
            //System.out.println(positions);
            return positions;
        }

        if (words[row][col] == ref.charAt(tracker)) {
            //char at row,col matching, so add the position
            Position curr = new Position(row, col);
            positions.add(curr);
            //Go right
            positions = getWordPositions(words, ref, row, col + 1, positions, tracker + 1);
            if (positions.size() == ref.length()) {
                //Found so return
                return positions;
            } else {
                positions = getWordPositions(words, ref, row + 1, col, positions, tracker + 1);
                if (positions.size() == ref.length()) {
                    //found, so return
                    return positions;
                }
            }
            //Added row, col char which was matching, but did not get the result
            //even after going right and down, so better remove this char as well
            //and return to original list and return it
            if (positions.size() > 0) {
                positions.remove(positions.size() - 1);
            }
        }
        //if row,col char not matching, not point in exploring right or down,
        //return original list
        return positions;
    }

    /*
     * Problem 1 : take input a list of words and reference string only one word
     * from the list can be constructed using letters in the string find that word
     */

    // O(n * m) : running complexity / space complexity

    public static String findEmbeddedWord (String[] words, String ref) {
        Map<Character, Integer> refMap = getCharMap(ref);
        // System.out.println(refMap);

        for (String eachWord : words) { // n words
            boolean isFound = true;
            Map<Character, Integer> currWordMap = getCharMap(eachWord);
            // System.out.println("Word :" + eachWord + " :::" + currWordMap);
            for (Map.Entry<Character, Integer> entry : currWordMap.entrySet()) { // m characters
                char key = entry.getKey();
                int val = entry.getValue();
                if (!refMap.containsKey(key) || refMap.get(key) < val) {
                    isFound = false;
                    break;
                }
            }
            if (isFound) {
                return eachWord;
            }
        }
        return "None";
    }

    private static Map<Character, Integer> getCharMap (String ref) {
        Map<Character, Integer> refMap = new HashMap<>();
        for (int i = 0; i < ref.length(); i++) {
            char currChar = ref.charAt(i);
            refMap.put(currChar, refMap.getOrDefault(currChar, 0) + 1);
        }
        return refMap;
    }
}
