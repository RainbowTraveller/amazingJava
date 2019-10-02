/*
 * Input : a string and a limit no.
 *
 * Output : collect the words from the original string append them separated by space, but limiting each
 * section to length <= limit
 *
 *
 */
public class PaddingDivision {

    private void append(StringBuffer output, String word) {
        output.append(" ");
        output.append(word);
    }

    private void padAndPrint(StringBuffer output, int limit) {
        while(output.length() < limit) {
            output.append(" ");
        }
        System.out.println(output + "*");
    }

    public void devideWithPadding(String input, int limit) {
        int remaining = limit;
        StringBuffer output = new StringBuffer();
        String [] words =  input.split(" ");
        for(String word : words) {
            if(remaining < word.length()) {
                padAndPrint(output, limit);
                output.delete(0, output.length());
            }
            append(output, word);
            remaining = limit - output.length();
        }
        if(output != null) {
            padAndPrint(output, limit);
        }
    }

    public static void main(String[] args) {
        PaddingDivision pd = new PaddingDivision();
        pd.devideWithPadding("XYZ is a online music streaming company", 10);

    }
}
