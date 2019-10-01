/*
* Given an encoded string, return its decoded string.
*
* The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
*
* You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
*
* Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
*
* Examples:
*
* s = "3[a]2[bc]", return "aaabcbc".
* s = "3[a2[c]]", return "accaccacc".
* s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/
import java.util.Deque;

import java.util.Scanner;
import java.util.LinkedList;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println("Please enter the encoded string : ");
        Scanner sc = new Scanner(System.in);
        String input =  sc.nextLine();
        System.out.println("Decoded String  : " + decodeString(input));
    }

    public static String decodeString(String s) {

		// Tracking actual characters
        Deque<String> track = new LinkedList<>();
		//tracking the count
        Deque<Integer> counters = new LinkedList<>();

        if (s != null && !s.isEmpty()) {
            int index = 0;
            StringBuffer buff = new StringBuffer();
            int count = 0;
            while (index < s.length()) {
				//Scan the input string character by character
                char currChar = s.charAt(index++);

                if (Character.isDigit(currChar)) {
					//Gather the count, as it may be multiple digit number
                    count = (count * 10) + Character.getNumericValue(currChar);
                } else if (currChar == ']') {
					//Here one set is complete,
					//So check the topmost count from stack and
					//have those many occurrences of this string
                    adjustStrings(counters,  track);
                } else {
                    if(currChar == '[') {
						// New set of characters starts
						//so push the count obtained for far to count stack
                        counters.push(count);
                        count = 0;
                    }
                    track.addFirst(Character.toString(currChar));
                }
            }
			//Something fishy, string not is correct form may be
            if (!counters.isEmpty()) {
                return null;
            }
        }
		//In case of nested brackets, there will be 1 count at the beginning which will
		//dictate final repeat count leading to only 1 string at the top of the stack
		//or there can be multiple string separately controlled by their individual counts
		//hence multiple strings in the stack which will have to be put together to form final string
        return track.size() == 1 ? track.removeFirst() : getResultString(track);
    }

    public static void adjustStrings(Deque<Integer> counters, Deque<String> track) {
        StringBuffer currBuff = new StringBuffer();
        int count = 0;
		//Get topmost count
        if(counters.size() > 0) {
            count = counters.removeFirst();
        }
        //System.out.println("TRACK : " + track);
		//Get all the character till we get a '['
        while (track.size() >= 1 && !track.peekFirst().equals(Character.toString('['))) {
			//the stack order is reverse so make sure add the next character at
			//beginning to get string in original sequence
            currBuff.insert(0, track.removeFirst());
        }

		//Make sure to remove '['
        if(track.size() > 0) {
            track.removeFirst();
        }
        //System.out.println("Curr String : " + currBuff.toString() + "  Counter : " + count);
        StringBuffer multiple = new StringBuffer();
		//Now that we have the string, repeat it count no. of times
        while (count > 0) {
            multiple.append(currBuff);
            count--;
        }
		//Add it back to the stack
        track.addFirst(multiple.toString());
    }

	//Gather all the strings from the stack and make a single one
    public static String getResultString(Deque<String> track) {
        StringBuffer result = new StringBuffer();
        while(!track.isEmpty()) {
            result.insert(0, track.removeFirst());
        }
        return result.toString();
    }
}
