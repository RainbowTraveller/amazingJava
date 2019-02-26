/* # character acts as backspace indicator in the string.
 *
 * e.g. "xy#xy##" --> x
 *      "xxy##" --> x
 *      "##ab" --> ab
 * Write a function to find out resultant string and then compare 2 string to give result
 *
 */
public class BackSpaceSimulation {
    public static final char SYMBOL = '#';

    public static void main(String args[] ) throws Exception {
        BackSpaceSimulation solution = new BackSpaceSimulation();
        System.out.println(solution.compareStrings("xy#xy##","xxy##"));
        System.out.println(solution.compareStrings("##zyc","yc#"));
        System.out.println(solution.compareStrings("ab##",""));
    }

    public boolean compareStrings( String s1, String s2) {
        String resultantS1 = getStringToCompare(s1);
        System.out.println("Result 1 :" + resultantS1);
        String resultantS2 = getStringToCompare(s2);
        System.out.println("Result 2 :" + resultantS2);
        return resultantS1.equals(resultantS2);
    }

    private String getStringToCompare(String s) {
        int length = s.length();
        StringBuffer buffer = new StringBuffer();
        int index = length - 1;
        //Keep track of consecutive #
        int count = 0;

        //Start from the end
        while(index >= 0) {
            if(s.charAt(index) == SYMBOL) {
                count++;
                index--;
            } else if( count > 0) {
                //Regular character encountered and count is > 0
                //means we have few # to take care
                //skip those many characters
                index -= count;
                //Make the count zero as we have accounted for the consecutive #
                count = 0;
            } else {
                //If regular character then append to a buffer
                buffer.append(s.charAt(index));
                index--;
            }
        }
        //We considered string from end, so to get original string
        //we need to reverse it
        return buffer.reverse().toString();
    }
}
