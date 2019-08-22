import java.util.Map;
import java.util.HashMap;

public class Base62Encoding {
    public static void main(String[] args) {
        String numStr = args[0];
        String baseStr = args[1];

        Map<Integer, String> encoding = new HashMap<Integer, String>();
        for(int i = 0; i < 26; ++i) {
            encoding.put(i,  Character.toString((char)('A' + i)));
        }

        for(int i = 26; i < 52; ++i) {
            encoding.put(i,  Character.toString((char)('a' + (i - 26))));
        }

        int index = 0;
        for(int i = 52; i < 62; ++i) {
            encoding.put(i,  Integer.toString(index++));
        }

        //for(Integer i : encoding.keySet()) {
        //    System.out.println("Key : " + i + " Value : " + encoding.get(i) );
        //}

        encode(Integer.valueOf(numStr), Integer.valueOf(baseStr), encoding);
    }

    public static void encode(int num, int base, Map<Integer, String> encoding) {
        StringBuffer buf = new StringBuffer();
        while(num > 0) {
            int digit = num % base;
            buf.append(encoding.get(digit));
            num /= base;
        }

        while (buf.length() < 8) {
            buf.append("0");
        }

        System.out.println("Encoded : " + buf.toString());
    }
}

