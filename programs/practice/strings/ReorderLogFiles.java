import java.util.Arrays;

/*
*  You have an array of logs.  Each log is a space delimited string of words.
*
*  For each log, the first word in each log is an alphanumeric identifier.  Then, either:
*
*  Each word after the identifier will consist only of lowercase letters, or;
*  Each word after the identifier will consist only of digits.
*  We will call these two varieties of logs letter-logs and digit-logs.
*   It is guaranteed that each log has at least one word after its identifier.
*
*  Reorder the logs so that all of the letter-logs come before any digit-log.
*   The letter-logs are ordered lexicographically ignoring identifier,
*   with the identifier used in case of ties. The digit-logs should be put in their original order.
*
*  Return the final order of the logs.
*
*
*  Example 1:
*
*  Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
*  Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
*
* Asked in Am : identifiers were alphanumeric and numeric instead only letters and digits
*/
public class ReorderLogFiles {
    public static void main(String[] args) {
		String[] input = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
		reorderLogFiles(input);
		System.out.println(Arrays.asList(input));
    }

	public static void reorderLogFiles(String[] logs) {
		Arrays.sort(logs,(log1, log2) -> {
			String[] log1Details = log1.split(" ", 2);
			String[] log2Details = log2.split(" ", 2);
			String key1 = log1Details[0];
			String key2 = log2Details[0];
			String value1 = log1Details[1];
			String value2 = log2Details[1];

			boolean isDigitl1 = Character.isDigit(value1.charAt(0));
			boolean isDigitl2 = Character.isDigit(value2.charAt(0));
			if(!isDigitl1 && !isDigitl2) {
				return value1.compareTo(value2) == 0 ? key1.compareTo(key2) : value1.compareTo(value2);
			}
			//0	: if (x==y)
			//1	: if (x > y)
			//-1: if (x < y)
			// True : True ==> both digits so return 0, order as is
			// True : False ==> log2 should appear before hence log1 > log2
			// False: True  ==> log1 should appear before hence log1 < log2
			return isDigitl1 ? (isDigitl2 ? 0 : 1) :  -1;
		});
	}
}


