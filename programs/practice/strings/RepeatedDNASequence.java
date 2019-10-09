/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]

*/
import java.util.*;
public class RepeatedDNASequence {


    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> answer = findRepeatedDnaSequences(input);
        for(String s : answer) {
            System.out.println(s);
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> seq = new LinkedList<String>();
        Map<String, Integer> tracker = new HashMap<String, Integer>();
        if(s != null && s.length() >= 10) {

            for(int i = 0; i + 10 <= s.length(); ++i) {
                String current = s.substring(i, i + 10);
                //This will print without needing extra space
                if(s.indexOf(current, i + 1) >= 0) {
                    //Checking for occurrence from
                    //next index from current start index
                    //of the current string
                    System.out.println(current);
                }
                int frequency = tracker.getOrDefault(current, 0);
                tracker.put(current, frequency + 1);
            }
        }
        for(String str : tracker.keySet()) {
            int count = tracker.get(str);
            if(count > 1) {
                seq.add(str);
            }
        }
        return seq;
    }
}
