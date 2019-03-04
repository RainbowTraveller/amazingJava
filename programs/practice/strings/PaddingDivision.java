/*
 * Input : a string and a limit no.
 *
 * Output : collect the words from the original string append them separated by space, but limiting each
 * section to length <= limit
 *
 *
 */
public class PaddingDivision {

    private String append(String output, String word) {
        if(output == null) {
            output = word;
        } else {
            output = output + " " + word;
        }
        return output;
    }

    private void padAndPrint(String output, int limit) {
        while(output.length() < limit) {
            output =  output + " ";
        }
        System.out.println(output + "*");
    }

    public void devideWithPadding(String input, int limit) {
        int remaining = limit;
        String output = null;
        String [] words =  input.split(" ");
        for(String word : words) {
            if(remaining < word.length()) {
                padAndPrint(output, limit);
                output = null;
            }
            output = append(output, word);
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
