/*
 * Several approaches to find longest palindrome substring in a given string
 * it also gives implementation for count all possible palindromes in a given string
 * In case of same palindrome found at a different starting index it is considered as different

*/

import java.util.*;

public class LongestPalindromeSubstring {


	public static void main ( String[] args ) {

		//Naive Approach
		System.out.println("Total no. of Palindrome strings : " + LongestPalindromeSubstring.countSubstrings("aaa"));

		System.out.println("LPS DYNAMIC ");

		System.out.println("Total no. of Palindrome strings : " + LongestPalindromeSubstring.lpsDynamic("aaa"));

		System.out.println("LPS");

		System.out.println("Total no. of Palindrome strings : " + LongestPalindromeSubstring.lps("aaaa"));
	}

	/*
	 * Does not work for even length palindrome with all same characters viz : aaaa
	 */
	public static int lps(String s) {
		List<String> palindrome = new LinkedList<String>();
		int count = 0;
		int start = 0;
		int end = 0;
		int maxLength = 0;
		int length = s.length();
		int low, high;
		if(s != null ) {
			//All single character strings
			for(int i = 0; i < length ; ++i) {
				palindrome.add(Character.toString(s.charAt(i)));
				count++;
			}

			for(int i = 1 ; i < length; ++i) {
				low = i - 1;
				high = i;
				while( low >= 0 && high  < length ) {
					if(s.charAt(low) == s.charAt(high) ) {
						count++;
						palindrome.add(s.substring(low, high + 1));
						if(high - low + 1 > maxLength) {
							start = low;
							maxLength = high - low + 1;
						} else {
							//Consider string like "faskdhadf", here first and last character is same
							//so it will be false palindrome based on just 1 character match
							//so when first character does not match don't consider subsequent characters
							break;
						}
					}
					--low;
					++high;
				}
				low = i - 1;
				high = i + 1;
				while(  low >= 0 && high < length ) {
					if(s.charAt(low) == s.charAt(high)) {
						palindrome.add(s.substring(low, high + 1));
						count++;
						if(high - low + 1 > maxLength) {
							start = low;
							maxLength = high - low + 1;
						} else {
							break;
						}
					}
					--low;
					++high;
				}
			}
		}
	   	System.out.println("Longest Palindrome String : " + s.substring(start, start + maxLength) + " Length : " + maxLength);
		System.out.println( "All the palindrome Strings possible : " + palindrome);
		return count;
	}
	/*
	 * It keeps track of palindrome strings starting with single character and then 2 character strings
	 * Then it checks for strings with length 3 characters or more. It uses already obtained knowledge
	 * of palindrome strings
	 * This is O(n^2) solution but at the same time it also requires O(n^2) space
	 */
	public static int lpsDynamic(String s) {
		List<String> palindrome = new LinkedList<String>();
		int count = 0;
		int start = 0;
		int end = 0;
		int maxLength = 0;
		if( s != null ) {
			int length = s.length();
			boolean[][] tracker = new boolean[length][length];

			//All single character strings
			for(int i = 0; i < length ; ++i) {
				tracker[i][i] = true;
				palindrome.add(Character.toString(s.charAt(i)));
				count++;
			}

			//All double char strings starting at each index in the string
			//same string with different starting index is considered different
			for(int i = 0; i < length - 1; ++i) {
				if(s.charAt(i) == s.charAt(i + 1)) {
					tracker[i][i+1] = true;
					palindrome.add(s.substring(i, i + 2));
					count++;
				}
			}

			for( int k = 3; k <= length; ++k) {//all string with length 3 or more
				for( int i = 0; i < length - k + 1; ++i ) {// i = 0 to length governed by K e.g. if k = 3, i will be max (length - 3)
					int j  = i + k - 1;// last character of string of length K
					//Consider line nested braces {{}}, both inner ones needed to be compared
					//so i + 1 and j -1 if they are same check current ones
					if(tracker[i + 1][j - 1 ] == true && s.charAt(i) == s.charAt(j)) {
						count++;
						palindrome.add(s.substring(i, j + 1));
						tracker[i][j] = true;
						if( k > maxLength) {
							start = i;
							end = j;
							maxLength = k;
						}
					}
				}
			}
	    	System.out.println("Longest Palindrome String : " + s.substring(start, end + 1) + " Length : " + maxLength);
			System.out.println( "All the palindrome Strings possible : " + palindrome);
		}
		return count;
	}

	//************ NAIVE APPROACH ******************** O(n^3)
	public static int countSubstrings(String s) {
		//To store all the palindrome strings
		List<String> palindrome = new LinkedList<String>();
		int count = 0;
		int start = 0;
		int end = 0;
		int maxLength = 0;
		if(s != null && s.length() > 1) {
			//Start from each index in string
			for(int i = 0; i < s.length(); ++i) {
				//j = 0, 1,2 : check all the substring starting at i till end of the string
				for(int j = 0; j + i < s.length(); ++j) {
					//Check if any of them is palindrome
					if(isPalindrome(i, i + j, s)) {
						if( i < s.length() && i + j < s.length() ) {
							//Add to list
							palindrome.add(s.substring(i, i + j + 1));
							//Check for longest one
							if( j - i + 1 > maxLength) {
								start = i;
								end = i + j + 1;
								maxLength = j - i + 1;
							}
							count++;
						}
					}
				}
			}
		}
	    System.out.println("Longest Palindrome String : " + s.substring(start, end) + " Length : " + maxLength);
		System.out.println( "All the palindrome Strings possible : " + palindrome);
		return count;
	}


	public static boolean isPalindrome(int start, int end, String str) {
		while(start < end) {
			if(str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

}
