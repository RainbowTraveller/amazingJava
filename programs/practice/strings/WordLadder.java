import java.util.*;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 127. Word Ladder https://leetcode.com/problems/word-ladder/
 *
 * <p>Given two words (beginWord and endWord), and a dictionary's word list, find the length of
 * shortest transformation sequence from beginWord to endWord, such that: Only one letter can be
 * changed at a time. Each transformed word must exist in the word list.
 *
 * <ul>
 *   <il>Note that beginWord is not a transformed word <il> Note: Return 0 if there is no such
 *   transformation sequence. <il> All words have the same length. <il> All words contain only
 *   lowercase alphabetic characters. <il> You may assume no duplicates in the word list. <il> You
 *   may assume beginWord and endWord are non-empty and are not the same.
 * </ul>
 *
 * Example 1:
 *
 * <p>Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * <p>Output: 5
 *
 * <p>Explanation:As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * <p>Example 2: Input: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
 *
 * <p>Output: 0
 *
 * <p>Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * <p>Word ladders were invented by Lewis Carroll in 1878, the author of Alice in Wonderland. A
 * ladder is a sequence of words that has a starting and ending word. In a word ladder puzzle you
 * have to change one word into another by altering a single letter at each step. Each word in the
 * ladder must be a valid English word, and must have the same length. For example, to turn stone
 * into money, one possible ladder is given on the left. Many ladder puzzles have more than one
 * possible solutions. Your program must determine a shortest word ladder.
 */
public class WordLadder {
  // dictionary contains available words
  private Set<String> dictionary;
  // Queue of stack of word ladders. Each stack contains a ladder from starting
  // word to an intermediate word in the dictionary
  //    private Deque<LinkedList<String>>   wordLadder;
  private Deque<String> simpleWordLadder;
  private String start;
  private String end;

  public static void main(String[] args) {
    // Get start and end words
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the first word");
    String start = sc.nextLine();
    System.out.println("Enter the last word");
    String end = sc.nextLine();
    WordLadder wl = new WordLadder(start, end);
    wl.findShortestSimpleLadder();
  }

  /**
   * Constructor initializes the dictionary and the queue of stacks
   *
   * @param start starting word
   * @param end ending word
   */
  public WordLadder(String start, String end) {
    this.start = start;
    this.end = end;
    dictionary = new LinkedHashSet<String>();
    dictionary.add("conk");
    dictionary.add("blue");
    dictionary.add("cork");
    dictionary.add("blae");
    dictionary.add("pork");
    dictionary.add("perk");
    dictionary.add("blad");
    dictionary.add("perl");
    dictionary.add("bead");
    /*dictionary.add( "hot");
    dictionary.add( "dot");
    dictionary.add( "dog");
    dictionary.add( "lot");
    dictionary.add( "log");
    dictionary.add( "cog");
    */
    simpleWordLadder = new LinkedList<String>();
    // Create initial stack with only start word
    // and add that stack to the queue
    LinkedList<String> startList = new LinkedList<String>();
    startList.addFirst(this.start);
    simpleWordLadder.add(this.start);
  }

  /**
   * Accepts the words in the dictionary for reference
   *
   * @param sc Scanner to read input
   */
  public void getInput(Scanner sc) {
    System.out.println("Please enter the no. of words in the dictionary: ");
    int numwords = sc.nextInt();
    for (int i = 0; i < numwords; ++i) {
      System.out.println("Enter next word : ");
      String word = sc.nextLine();
      dictionary.add(word);
    }
  }

  /**
   * Collects all words that are one letter different from the given word
   *
   * @param word current word
   * @param dictionary set of available words
   * @param wordLadder current ladder
   * @return list of words that are one letter different from the given word
   */
  private List<String> collectNextWords(
      String word, Set<String> dictionary, Deque<String> wordLadder) {

    // List to collect neighbours
    List<String> collectedNeighbours = new LinkedList<String>();
    // Change each letter in the word to all possible letters
    char[] wordArray = word.toCharArray();
    for (int i = 0; i < wordArray.length; ++i) {
      for (char j = 'a'; j < 'z'; ++j) {
        if (j != wordArray[i]) {
          char original = wordArray[i];
          wordArray[i] = j;
          String newWord = new String(wordArray);
          wordArray[i] = original;
          // If the new word is in the dictionary then collect it
          if (dictionary.remove(newWord)) {
            // Also ensure that the new word is not already in the current ladder
            collectedNeighbours.add(newWord);
          }
        }
      }
    }
    return collectedNeighbours;
  }

  /**
   * Uses BFS. Add Start word in the queue and level = 1 Perform until queue is empty
   *
   * <p>1. queueSize = size Of the queue
   *
   * <p>2. for all words indicated by queueSize
   *
   * <p>3. currentWord = first word in queue
   *
   * <p>4. Neighbours of the current word are found
   *
   * <p>5. if end word is among the neighbours then level is shorted distance 5. else add them in
   * the queue
   *
   * <p>6. size -- 7. When size = 0 then level++ 8 follow step 1
   *
   * <p>Classic BFS. In this case we look at neighboring words for each word at a given depth. If we
   * find the end word then that level is the shortest ladder
   *
   * <p>Time Complexity : O(M*N) where M is length of each word and N is number of words in the
   * dictionary
   *
   * <p>Space Complexity : O(N) where N is number of words in the dictionary
   *
   * @return void
   */
  public void findShortestSimpleLadder() {
    int level = 1;
    while (!simpleWordLadder.isEmpty()) {
      System.out.println("Current Ladder" + simpleWordLadder);
      // Get current queue size
      int size = simpleWordLadder.size();
      // Process only these many words
      for (int i = 0; i < size; ++i) {
        String candidate = simpleWordLadder.removeFirst();
        System.out.println("Current Word : " + candidate);
        if (candidate.equals(this.end)) {
          System.out.println("Shortest length Found : " + level);
          return;
        }
        List<String> neighbours = collectNextWords(candidate, dictionary, simpleWordLadder);
        for (String neighbour : neighbours) {
          simpleWordLadder.add(neighbour);
        }
        // System.out.println(" Current Queue : " + simpleWordLadder);
      }
      level++;
    }
    System.out.println("Shortest length : " + level);
  }
}
