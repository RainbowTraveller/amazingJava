import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;


public class PanagramChecker {

    private TreeSet<Character> alphabets;//Required Set of alphabets
    private TreeSet<Character> inputSet;// Set of input alphabets

    public PanagramChecker() {
        alphabets = new TreeSet<Character>();
        for(int i = 'a'; i <= 'z'; ++i) {
            alphabets.add((char)i);
        }
        inputSet = new TreeSet<Character>();
    }
    /*
     * Gets the missing alphabets
     */
    public String getMissingLetters(String input) {
        String missingAlphabets = "";
        if(input == null) {
            input = "";
        }

        char[] inputArray = input.toCharArray();
        this.inputSet.clear();
        for(Character c : inputArray) {
            //Create set from input String characters
            char currentChar = Character.toLowerCase(c);
            if(this.alphabets.contains(currentChar)) {
                this.inputSet.add(currentChar);
            }
        }
        return getSetDiff();
    }

    /*
     * Get difference of 2 Sets to deduce missing characters
     */
    private String getSetDiff(){
        List<Character> result = this.alphabets.stream()
                                               .filter(x-> !this.inputSet.contains(x))
                                               .collect(Collectors.toList());
        Collections.sort(result);
        StringBuffer sb = new StringBuffer();
        for(Character c : result) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PanagramChecker pc = new PanagramChecker();
        System.out.println("Missing = "  + pc.getMissingLetters("A quick brown fox jumps over the lazy dog"));
        System.out.println(pc.getMissingLetters(""));
        System.out.println(pc.getMissingLetters("A slow yellow frog crawls under the proactive dog"));
        System.out.println(pc.getMissingLetters("Lions, and tigers, and bears, oh my!"));
    }
}
