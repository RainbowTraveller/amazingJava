import java.util.ArrayList;
/*
 * Program to count frequency of the char and compress it
 * e.g. input aabbccddeee
 * output = a2b2c2d2e3
 *
 * input:  abcd
 * output: abcd (as 'compressed' string a1b1c1d1 is greater than original)
 *
 */

public class CompressedString {

    public static String getCompressed(String s) {
        char token = ' ';
        int count = 0;
        ArrayList<Character> compressed = new ArrayList<Character>();
        for(int i = 0; i < s.length(); ++i) {
            if(i > 0) {
                if(s.charAt(i) == token) {
                    count++;
                } else {
                    compressed.add(token);
                    compressed.add((Character.forDigit(count, Character.MAX_RADIX)));
                    token = s.charAt(i);
                    count = 1;
                }
            } else {
                token = s.charAt(i);
                count = 1;
            }
        }
        compressed.add(token);
        compressed.add((Character.forDigit(count, Character.MAX_RADIX)));
        if(compressed.size() < s.length()) {
            s = "";
            for(char c : compressed) {
                s += c;
            }
        }
        return s;
    }

    public static String getCompressedArr(String s) {
        char token = ' ';
        int index = 0;
        int count = 0;
        int len = s.length();
        char[] compressed = new char[2 * len];
        for(int i = 0; i < s.length(); ++i) {
            if(i > 0) {
                if(s.charAt(i) == token) {
                    count++;
                } else {
                    compressed[index++] = token;
                    compressed[index++] = Character.forDigit(count, Character.MAX_RADIX);
                    token = s.charAt(i);
                    count = 1;
                }
            } else {
                token = s.charAt(i);
                count = 1;
            }
        }
        compressed[index++] = token;
        compressed[index] = Character.forDigit(count, Character.MAX_RADIX);

        if(index < s.length()) {
            s = new String(compressed);
        }
        return s;
    }


    public static void main(String[] args) {
        String input = args[0];
        System.out.println("Compressed String : " + getCompressed(input));
        System.out.println("Compressed String : " + getCompressedArr(input));
    }
}
