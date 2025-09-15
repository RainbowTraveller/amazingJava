import java.util.*;
import java.util.LinkedList;
import java.util.List;

/*
   stone
   Atone
   aLone
   Clone
   clonS
   cOons
   coNns
   conEs
   coneY
   Money

  Word ladders were invented by Lewis Carroll in 1878, the author of Alice in
  Wonderland. A ladder is a sequence of words that has a starting and ending
  word. In a word ladder puzzle you have to change one word into another by
  altering a single letter at each step. Each word in the ladder must be a
  valid English word, and must have the same length. For example, to turn stone into money,
  one possible ladder is given on the left. Many ladder puzzles have more than one possible solutions.
  Your program must determine a shortest word ladder. Another path from stone to money is

  stone  store  shore  chore  choke  choky  cooky  cooey  coney  money

  https://www.cs.cmu.edu/~adamchik/15-121/labs/HW-4%20Word%20Ladder/lab.html : explanation and further testing

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

  /*
   * Accepts the words in the dictionary for reference
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

  private List<String> collectNextWords(
      String word, Set<String> dictionary, Deque<String> wordLadder) {
    List<String> collectedNeighbours = new LinkedList<String>();
    char[] wordArray = word.toCharArray();
    for (int i = 0; i < wordArray.length; ++i) {
      for (char j = 'a'; j < 'z'; ++j) {
        if (j != wordArray[i]) {
          char original = wordArray[i];
          wordArray[i] = j;
          String newWord = new String(wordArray);
          wordArray[i] = original;
          if (dictionary.remove(newWord)) {
            collectedNeighbours.add(newWord);
          }
        }
      }
    }
    return collectedNeighbours;
  }

  /*
   *  Uses BFS.
   *   Add Start word in the queue and level = 1
   *   Perform until queue is empty
   *
   *  1. queueSize = size Of the queue
   *  2. for all words indicated by queueSize
   *  2. currentWord = first word in queue
   *  3. Neighbours of the current word are found
   *  4. if end word is among the neighbours then level is shorted distance
   *  5. else add them in the queue
   *  6. size --
   *  7. When size  = 0 then level++
   *  8 follow step 1
   *
   *
   *  Classis BFS. In this case we look at neighboring words for each word
   *  at a given depth. If we find the end word then that level is the
   *  shortest ladder
   */
  public void findShortestSimpleLadder() {
    int level = 1;
    while (!simpleWordLadder.isEmpty()) {
      System.out.println("Current Ladder" + simpleWordLadder);
      int size = simpleWordLadder.size();
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
