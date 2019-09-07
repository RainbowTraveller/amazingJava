import java.lang.NullPointerException;
import java.lang.Exception;

/*
 * Converts numeric string to long value
 *
 */
public class StrToLongConverter {
    private long result;// stores result
    private int signIndex;// index for sign
    private boolean isNegative;// flag to indicate -ve number

    private final int TEN = 10;// multiplying factor
    private final char SIGN = '-';// -ve sign
    private final char ZERO = '0';// base to calculate offset for a number

    /*
     * Constructor
     */
    public StrToLongConverter() {
        result = 0;
        signIndex = 0;
        isNegative = false;
    }

    /*
     * Converts given string to equivalent long number
     *
     * @throws Nullpointerexception, Invalidinputexception
     */
    public long stringToLong(String str) throws NullPointerException, InvalidInputException {
        result = 0;
        if (str != null) {
            // Check for -ve number
            if (str.charAt(signIndex) == SIGN) {
                isNegative = true;
                // get only number part of string excluding sign character
                str = str.substring(signIndex + 1);
            }

            for (char c : str.toCharArray()) {
                result *= TEN;
                // Deduce the number from its distance from ACSII value of zero
                int diff = (int) c - (int) ZERO;
                if (diff > 9) {
                    // throw exception if string contains anything else than numbers
                    throw new InvalidInputException("Input contains non numeric characters");
                } else {
                    result += diff;
                }
            }
            if (isNegative == true) {
                // adjustment for -ve number
                result *= (-1);
            }
            return result;
        }
        throw new NullPointerException("Input String is empty");
    }

    public static int myAtoi(String str) {
        boolean isNegative = false;

        if (str == null || str.trim().isEmpty()) {
            return 0;
        }

        str = str.trim();
        char firstChar = str.charAt(0);
        if (firstChar != '-' && firstChar != '+' && !Character.isDigit(firstChar)) {
            return 0;
        }

        if (str.charAt(0) == '-') {
            isNegative = true;
        }
        int index = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            index = 1;
        }

        StringBuffer buff = new StringBuffer();

        while (index < str.length()) {
            char curr = str.charAt(index);
            if (!Character.isDigit(curr)) {
                break;
            }
            buff.append(curr);
            index++;
        }
        int num = 0;
        // System.out.println(buff);

        for (int i = 0; i < buff.length(); i++) {
            int currDigit = Character.getNumericValue(buff.charAt(i));

            if (num > (Integer.MAX_VALUE - currDigit) / 10) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            // System.out.println(currDigit + " " + num);
            num = (num * 10) + currDigit;
        }

        if (isNegative) {
            num *= -1;
        }
        return num;
    }

    /*
     * Exception used in case of invalid input string
     */
    @SuppressWarnings("serial")
    class InvalidInputException extends Exception {
        InvalidInputException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        StrToLongConverter worker = new StrToLongConverter();
        long testNumber = 0;

        System.out.println("-------Testing implementation-------");
        // Positive number
        try {
            testNumber = worker.stringToLong("123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (testNumber == 123) {
            System.out.println("Success :" + testNumber);
        }

        // Negative number
        try {
            testNumber = worker.stringToLong("-123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (testNumber == -123) {
            System.out.println("Success :" + testNumber);
        }

        // Overflow
        try {
            testNumber = worker.stringToLong(Long.toString(Long.MAX_VALUE + 200));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Overflow Test :" + testNumber);

        // Invalid number
        try {
            testNumber = worker.stringToLong("-ab3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Build in functionality check
        System.out.println("-------Testing Built in function-------");
        System.out.println(Long.parseLong("123"));
        System.out.println(Long.parseLong("-123"));
        System.out.println(Long.parseLong(Long.toString(Long.MAX_VALUE + 200)));
        // System.out.println(Long.parseLong("-a23"));
        // **************************************
        // Converting to integer

        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("       42"));
        System.out.println(myAtoi("       -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("91283472332"));

    }
}
