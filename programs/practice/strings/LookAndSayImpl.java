/*
 * Input : 1112233455436
 * Output : 3122231425141316
 *
 * Convert a number into another such that is indicates no. of consecutive appearances of a number followed by the number in the input
 *
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LookAndSayImpl {
    public ArrayList<Integer> list1 = null;
    public ArrayList<Integer> list2 = null;

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        LookAndSayImpl soln = new LookAndSayImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number e.g. 11233133222: ");
        String num  = in.nextLine();
        System.out.println("Look and Say output : e.g. Two ones,Three 2s etc: " + soln.LookAndSay(num));
        System.out.println("Please enter the iterations: ");
        int itr  = in.nextInt();
        System.out.println("Look and Say output after : "  + itr  + " iterations : " + soln.LookAndSay(num, itr));
    }

    public String LookAndSay(String numeric) {
        ArrayList<HashMap<Character, Integer>> tracking = new ArrayList<HashMap<Character, Integer>>();
        char[] input = numeric.toCharArray();
        int len = numeric.length();

        int index = 0;
        char prev = input[0];
        int frequency = 1;
        for(int i = 1; i < len; ++i) {
            char curr = input[i];
            if(prev == curr) {
                frequency++;
            } else {
                HashMap<Character, Integer> freq = new HashMap<Character,Integer>();
                freq.put(prev, frequency);
                tracking.add(freq);
                prev = curr;
                frequency = 1;
            }
        }

        HashMap<Character, Integer> freq = new HashMap<Character,Integer>();
        freq.put(prev, frequency);
        tracking.add(freq);
        StringBuffer strBuff = new StringBuffer();
        for(Map<Character,Integer> eachFreq : tracking) {
            for(Map.Entry<Character,Integer> entry : eachFreq.entrySet()) {
                 strBuff.append(Integer.toString(entry.getValue()));
                 strBuff.append(entry.getKey());
            }
        }
        return strBuff.toString();
    }

    public String LookAndSay(String numeric, int itr) {
        String input = numeric;
        for(int i = 0; i < itr; ++i) {
            input  = LookAndSay(input);
        }
        return input;
    }
}
