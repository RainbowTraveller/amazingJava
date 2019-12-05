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
        System.out.println(solution.compareStrings("abc#de#f#ghi#jklmn#op#","abdghjklmo"));
        System.out.println(solution.compareStrings("##geeks##for##geeks#","geefgeek"));
        System.out.println(solution.compareStrings("bxj##tw","bxo#j##tw"));
        System.out.println("::::");
        System.out.println(solution.backspaceCompare("xy#xy##","xxy##"));
        System.out.println(solution.backspaceCompare("##zyc","yc#"));
        System.out.println(solution.backspaceCompare("ab##",""));
        System.out.println(solution.backspaceCompare("abc#de#f#ghi#jklmn#op#","abdghjklmo"));
        System.out.println(solution.backspaceCompare("##geeks##for##geeks#","geefgeek"));
        System.out.println(solution.backspaceCompare("bxj##tw","bxo#j##tw"));
    }

    public boolean compareStrings( String s1, String s2) {
        String resultantS1 = getStringToCompare(s1);
        System.out.println("Result 1 :" + resultantS1);
        String resultantS2 = getStringToCompare(s2);
        System.out.println("Result 2 :" + resultantS2);
        return resultantS1.equals(resultantS2);
    }

    /*
     * Running time : O(M + N) : as we need to traverse both the strings
     * but this is also O(M + N) in terms of space as we allocated buffer for both
     * the string
     */
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

    /*
     * Running : O(M + N)
     * Space : O(1) : here instead of obtaining entire string and then comparing, we compare
     * character by character
     */

    public boolean backspaceCompare(String s1, String s2) {
        int lenS1 = s1.length();
        int lenS2 = s2.length();

        int indexS1 = lenS1 - 1;
        int indexS2 = lenS2 - 1;

        //Keep track of consecutive #
        int countS1 = 0;
        int countS2 = 0;

        while(indexS1 >= 0 || indexS2 >= 0) {
            //Start from the end
            while(indexS1 >= 0) {
                if(s1.charAt(indexS1) == SYMBOL) {
                    countS1++;
                    indexS1--;
                } else if( countS1 > 0) {
                    //Regular character encountered and count is > 0
                    //means we have few # to take care
                    //skip those many characters
                    indexS1 -= countS1;
                    //Make the count zero as we have accounted for the consecutive #
                    countS1 = 0;
                } else {
                    //Here we processed the # backspaces and the
                    //deletion led by them so far.Now characters at this place should match
                    break;
                }
            }

            while(indexS2 >= 0) {
                if(s2.charAt(indexS2) == SYMBOL) {
                    countS2++;
                    indexS2--;
                } else if( countS2 > 0) {
                    //Regular character encountered and count is > 0
                    //means we have few # to take care
                    //skip those many characters
                    indexS2 -= countS2;
                    //Make the count zero as we have accounted for the consecutive #
                    countS2 = 0;
                } else {
                    //Here we processed the # backspaces and the
                    //deletion led by them so far.Now characters at this place should match
                    break;
                }
            }

            if( indexS1 >=0 && indexS2 >= 0 && s1.charAt(indexS1) != s2.charAt(indexS2)) {
                return false;
            }

            if( (indexS1 >= 0 ) != (indexS2 >= 0)) {
                return false;
            }
            indexS1--;
            indexS2--;
        }
        return true;
    }
}
