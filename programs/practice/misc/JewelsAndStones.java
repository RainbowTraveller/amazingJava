/*
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 *  The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 *  Example 1:
 *
 *  Input: J = "aA", S = "aAAbbbb"
 *  Output: 3
 *  Example 2:
 *
 *  Input: J = "z", S = "ZZ"
 *  Output: 0
 *
 */

public class JewelsAndStones {

	public static void main(String[] args) {
    	String j = args[0];
    	String s = args[1];
		System.out.println(numJewelsInStones(j, s));
	}

	public static int numJewelsInStones(String J, String S) {
        int count = 0;
        if( J != null && S != null) {
            for(int i = 0; i < S.length(); ++i) {
                if(J.indexOf(S.charAt(i)) >= 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
