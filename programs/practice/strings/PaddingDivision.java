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
    while (output.length() < limit) {
      output.append(" ");
    }
    System.out.println(output + "*");
  }

  /**
   * Divide the input string into sections of length <= limit and pad with spaces to reach the limit
   * if necessary. This logic is as follows:
   *
   * <p>1. Split the input string into words using space as a delimiter.
   *
   * <p>2. Initialize a StringBuffer to collect words for the current section and a variable to
   * track remaining space.
   *
   * <p>3. Iterate through each word:
   *
   * <p>a. If the remaining space is less than the length of the current word, print the current
   * section with padding and reset the StringBuffer.
   *
   * <p>b. Append the current word to the StringBuffer and update the remaining space.
   *
   * <p>4. After processing all words, print any remaining words in the StringBuffer with padding.
   *
   * @param input the input string to be divided
   * @param limit the maximum length of each section
   */
  public void devideWithPadding(String input, int limit) {
    int remaining = limit;
    StringBuffer output = new StringBuffer();
    String[] words = input.split(" ");
    for (String word : words) {
      if (remaining < word.length()) {
        padAndPrint(output, limit);
        output.delete(0, output.length());
      }
      append(output, word);
      remaining = limit - output.length();
    }
    if (output != null) {
      padAndPrint(output, limit);
    }
  }

  public static void main(String[] args) {
    PaddingDivision pd = new PaddingDivision();
    pd.devideWithPadding("XYZ is a online music streaming company", 10);
  }
}
