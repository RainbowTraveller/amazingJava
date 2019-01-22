import java.util.List;
import java.util.LinkedList;
import java.util.*;
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
   Word ladders were invented by Lewis Carroll in 1878, the author of Alice in Wonderland.
   A ladder is a sequence of words that starts at the starting word, ends at the ending word, In a word ladder puzzle you have to change one word into another by altering a single letter at each step.
   Each word in the ladder must be a valid English word, and must have the same length.
   For example, to turn stone into money, one possible ladder is given on the left.
Many ladder puzzles have more than one possible solutions. Your program must determine a shortest word ladder. Another path from stone to money is

stone  store  shore  chore  choke  choky  cooky  cooey  coney  money
 */
public class WordLadder {
    private Set<String>             dictionary; // dictionary
    private Deque<LinkedList<String>>    wordLadder;
    private String                  start;
    private String                  end;


    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first word");
        String start = sc.nextLine();
        System.out.println("Enter the last word");
        String end = sc.nextLine();
        WordLadder wl = new WordLadder( start, end );
        wl.getInput(sc);
        wl.findShortestLadder();
    }

    public WordLadder( String start, String end ){
        this.start = start;
        this.end = end;
        dictionary = new LinkedHashSet<String>();
        wordLadder = new LinkedList<LinkedList<String>>();
        LinkedList<String> startList = new LinkedList<String>();
        startList.addFirst( this.start );
        wordLadder.add(startList);
    }

    public void getInput(Scanner sc) {
        System.out.println("Please enter the no. of words in the dictionary: ");
        int numwords = sc.nextInt();
        for(int i = 0; i < numwords; ++i) {
            System.out.println("Enter next word : ");
            String word = sc.nextLine();
            dictionary.add( word );
        }
    }

    private boolean isNextWord(String current, String next) {
        int len = current.length();
        int count = 0;
        for(int i = 0; i < len; ++i) {
            if(current.charAt(i) != next.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }



    public void setDictionary( Set<String> modifiedDictionary ){
        this.dictionary = modifiedDictionary;
    }

    public void findShortestLadder() {
        int length = Integer.MAX_VALUE;
        while(!wordLadder.isEmpty()) {
            //Remove first stack of string from the word ladder queue
            LinkedList<String> currentStackOfStrings = wordLadder.pollFirst();
            //Get the string at the stack top
            String candidate = currentStackOfStrings.getFirst();
            if(!dictionary.isEmpty() && !this.end.equals(candidate)) {
                Set<String> newDictionary = new LinkedHashSet<String>();
                for(String word : dictionary) {
                    if(isNextWord(candidate, word)) {
                        //Copy the original stack, then add on top if more string are found
                        LinkedList<String> newStack = currentStackOfStrings;
                        newStack.addLast(word);
                        wordLadder.addLast(newStack);
                    } else {
                        newDictionary.add( word );
                    }
                }
                dictionary = newDictionary;
            }
            if(this.end.equals(candidate)) {
                while(!wordLadder.isEmpty()) {
                    currentStackOfStrings = wordLadder.pollFirst();
                    if(currentStackOfStrings.size() < length) {
                        length = currentStackOfStrings.size();
                    }
                }
            }
        }
        System.out.println("Shortest length : " + length);
    }
}
