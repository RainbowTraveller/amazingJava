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

   Word ladders were invented by Lewis Carroll in 1878, the author of Alice in Wonderland. A ladder is a sequence of words that starts at the starting word, ends at the ending word,
   In a word ladder puzzle you have to change one word into another by altering a single letter at each step. Each word in the ladder must be a valid English word, and must have the
   same length. For example, to turn stone into money, one possible ladder is given on the left. Many ladder puzzles have more than one possible solutions. Your program must determine
   a shortest word ladder. Another path from stone to money is

   stone  store  shore  chore  choke  choky  cooky  cooey  coney  money

   https://www.cs.cmu.edu/~adamchik/15-121/labs/HW-4%20Word%20Ladder/lab.html : explaination and further testing

 */
public class WordLadder {
    // dictionary contains available words
    private Set<String>                 dictionary;
    //Queue of stack of word ladders. Each stack contains a ladder from starting
    //word to an intermediate word in the dictionary
    private Deque<LinkedList<String>>   wordLadder;
    private String                      start;
    private String                      end;

    public static void main(String [] args) {
        //Get start and end words
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first word");
        String start = sc.nextLine();
        System.out.println("Enter the last word");
        String end = sc.nextLine();
        WordLadder wl = new WordLadder( start, end );
        //Get the words in the dictionary
        //wl.getInput(sc);
        wl.findShortestLadder();
    }

    public WordLadder( String start, String end ){
        this.start = start;
        this.end = end;
        dictionary = new LinkedHashSet<String>();
        /*
        dictionary.add( "conk");
        dictionary.add( "blue");
        dictionary.add( "cork");
        dictionary.add( "blae");
        dictionary.add( "pork");
        dictionary.add( "perk");
        dictionary.add( "blad");
        dictionary.add( "perl");
        dictionary.add( "bead");
        */
        dictionary.add( "hot");
        dictionary.add( "dot");
        dictionary.add( "dog");
        dictionary.add( "lot");
        dictionary.add( "log");
        //dictionary.add( "cog");
        wordLadder = new LinkedList<LinkedList<String>>();
        //Create initial stack with only start word
        //and add that stack to the queue
        LinkedList<String> startList = new LinkedList<String>();
        startList.addFirst( this.start );
        wordLadder.add(startList);
    }

    /*
     * Accepts the words in the dictionary for reference
     */
    public void getInput(Scanner sc) {
        System.out.println("Please enter the no. of words in the dictionary: ");
        int numwords = sc.nextInt();
        for(int i = 0; i < numwords; ++i) {
            System.out.println("Enter next word : ");
            String word = sc.nextLine();
            dictionary.add( word );
        }
    }

    /*
     * Checks if 2 words differ only by 1 character
     */
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
            System.out.println("Current Stack : " + currentStackOfStrings);
            //Get the string at the stack top
            String candidate = currentStackOfStrings.getLast();
            System.out.println("Candidate : " + candidate);
            if(!dictionary.isEmpty() && !this.end.equals(candidate)) {
                Set<String> newDictionary = new LinkedHashSet<String>();
                for(String word : dictionary) {
                    System.out.println("Word : " + word);
                    if(isNextWord(candidate, word)) {
                        //Copy the original stack, then add on top if more string are found
                        //Correction : need to create new object and not just a reference
                        //LinkedList<String> newStack = currentStackOfStrings; // This will fail
                        LinkedList<String> newStack = new LinkedList<String>(currentStackOfStrings);
                        newStack.addLast(word);
                        System.out.println("Adding Word : " + word);
                        System.out.println("New Stack : " + newStack);
                        wordLadder.addLast(newStack);
                    } else {
                        newDictionary.add( word );
                    }
                }
                dictionary = newDictionary;
            }
            length = currentStackOfStrings.size();
            if(this.end.equals(candidate)) {
                while(!wordLadder.isEmpty()) {
                    currentStackOfStrings = wordLadder.pollFirst();
                    System.out.println("Current Stack 2 : " + currentStackOfStrings);
                    if(currentStackOfStrings.size() < length) {
                        length = currentStackOfStrings.size();
                    }
                }
            } else {
                length = 0;
            }
        }
        System.out.println("Shortest length : " + length);
    }
}
