public class Permutations {
    /*
     *  To obtain all the possible permutations of a string
     *  e.g. abc = abc acb bac bca cab cba
     *
     *  aaa is also considered to be consists of 3 different a's
     *
     *  allowed[] : boolean array to keep track of allowed char at each level
     *
     *  for abc : 3 * 2 * 1 = 6 possible outcomes so at each level no of allowed chars decreases
     *
     *  following map shows allowed map after considering particular character
     *  f  f  f
     *  t  f  f
     *  t  t  f
     *
     *  a       a // blanks in vertical column indicate the chars not allowed
     *     b    ab// each column represents one recursive call
     *        c abc --
     *     c    a
     *          ac
     *        b acb --
     *
     *
     */

    public static void perms(String i, StringBuffer perm, boolean[] allowed) {
        if(perm.length() == i.length()) {
            System.out.println(perm.toString());
            return;
        } else {
            for(int j = 0; j < i.length(); ++j) {
                if(allowed[j]) {
                    perm.append(i.charAt(j));
                    allowed[j] = false;
                    perms(i, perm, allowed);
                    allowed[j] = true; // reset the map to allow the char to be considered in prev recursive call
                    perm.deleteCharAt(perm.length() - 1);// also remove char from the buffer
                }
            }
        }

    }

    public static void main(String[] args) {
        String input = args[0];
        int len = input.length();
        boolean[] allowed = new boolean[len];
        for(int i = 0; i < len; ++i) {
            allowed[i] = true;
        }
        StringBuffer permutation = new StringBuffer(input.length());
        perms(input, permutation, allowed);

    }
}
