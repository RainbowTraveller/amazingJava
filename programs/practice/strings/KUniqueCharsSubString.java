/*
 *   Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that has exactly k distinct characters.
 *   Examples:
 *   Input: abc, k = 2
 *   Output: 2
 *   Possible substrings are {"ab", "bc"} // ab has 2 distinct characters a and b, bc has 2 distinct character b and c
 *   Input: aba, k = 2
 *   Output: 3
 *   Possible substrings are {"ab", "ba", "aba"}
 *   Input: aa, k = 1
 *   Output: 3
 *   Possible substrings are {"a", "a", "aa"}
 *
 **/

public class KUniqueCharsSubString {

	public static void main(String [] args) {
		String input = args[0];
		int num = Integer.valueOf(args[1]);
		getKUniqueCharSubString(input, num);
	}

	public static void getKUniqueCharSubString( String input , int k) {
		if( input != null ) {
			int len = input.length();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < len; ++i) {
				//Resetting
				int count = 0;
				buffer.delete(0, buffer.length());
				for(int j = i; j < len; ++j) {
					char current = input.charAt(j);
					if( buffer.toString().indexOf(current) < 0 ) {
						//Check if a character is already present in the string
						//if not we found unique char in a string so increment counter
						count++;
					}
					buffer.append( current );
					if(count == k) {// if unique char count is equal to desired value print string
						System.out.println(buffer);
					}
				}
			}
		}
	}
}
