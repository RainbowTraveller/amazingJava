import java.util.HashMap;

public class UniqueChars {

    /*
     * The program demonstrates different ways of
     * checking if a string contains all unique characters or not
     */

    public static void main(String[] args) {
        String str = args[0];
        System.out.println("Input String : " + str);

        if(checkUniquenessMap(str) == true) {
            System.out.println("String has all unique characters");
        } else {
            System.out.println("String has some duplicate characters");
        }

        if(checkUniquenessDoubleLoop(str) == true) {
            System.out.println("String has all unique characters");
        } else {
            System.out.println("String has some duplicate characters");
        }

        if(checkUniquenessDoubleLessLoop(str) == true) {
            System.out.println("String has all unique characters");
        } else {
            System.out.println("String has some duplicate characters");
        }

        if(checkUniquenessArr(str) == true) {
            System.out.println("String has all unique characters");
        } else {
            System.out.println("String has some duplicate characters");
        }

        if(checkUniquenessBitwise(str) == true) {
            System.out.println("String has all unique characters");
        } else {
            System.out.println("String has some duplicate characters");
        }
    }

    public static boolean checkUniquenessMap(String str) {
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> chmap = new HashMap<Character, Integer>();
        for(char c : chars) {
            c = Character.toLowerCase(c);
            if(chmap.get(c) == null) {
                chmap.put(c, 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUniquenessDoubleLoop(String str) {
        char[] chars = str.toCharArray();
        for(char c : chars) {
            int count = 0;
            for(char c2 : chars) {
                if(Character.toLowerCase(c) == Character.toLowerCase(c2)) {
                    count++;
                    if(count > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkUniquenessDoubleLessLoop(String str) {
        char[] chars = str.toCharArray();
        int arrlen = chars.length;
        for(int i = 0; i < arrlen ; ++i) {
            int count = 0;
            for(int j = i+1; j < arrlen ; ++j) {
                if(Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) {
                        return false;
                }
            }
        }
        return true;
   }

    public static boolean checkUniquenessArr(String str) {
        int len = str.length();
        if(len > 128) {
            return false;
        }
        boolean[] chk_track = new boolean[256];
        for(int i = 0; i < len ; ++i) {
            int val = Character.toLowerCase(str.charAt(i));
            if(chk_track[val] == true) {
                return false;
            }
            chk_track[val] = true;
        }
        return true;
   }


   public static boolean checkUniquenessBitwise(String str) {
        int len = str.length();
        if(len > 128) {
            return false;
        }
        int check = 0;
        for(int i = 0; i < len ; ++i) {
            char lch = Character.toLowerCase(str.charAt(i));
            int val = lch - 'a';
            int bitter = 1 << val;// Rotate 1 to left val no. of times
            System.out.println("Value : " + val);
            System.out.println("Checker : " + Integer.toString(check, 2));
            System.out.println("Bitter : " + Integer.toString(bitter, 2));
            System.out.println("-----------------------------------------");
            if((check & bitter) > 0) {//Two set bits at same position will be > 0
                return false;
            }
            check |= bitter;// Add the bit mask to checker
        }
        return true;

   }
}
