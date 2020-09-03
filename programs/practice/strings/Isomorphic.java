import java.util.Map;
import java.util.HashMap;

public class Isomorphic {
    public static void main(String[] args) {
        System.out.println(Isomorphic.isIsomorphic("paper", "titie"));
        System.out.println(Isomorphic.isIsomorphic("foo", "egg"));
        System.out.println(Isomorphic.isIsomorphic("bob", "pop"));
        System.out.println(Isomorphic.isIsomorphic("bob", "mop"));

    }

    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapping = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); ++i) {
            if (mapping.containsKey(s.charAt(i))) {
                // Check if key is present and if yes then the value should match the char
                // from the target string
                if (t.charAt(i) != mapping.get(s.charAt(i))) {
                    return false;
                }
            } else {
                // Key is not present but value is already mapped to another key
                // which is wrong
                if (mapping.containsValue(t.charAt(i))) {
                    return false;
                }
                mapping.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
}
