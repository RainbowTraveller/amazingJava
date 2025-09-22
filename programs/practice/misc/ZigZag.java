/**
 * LeetCode :
 * https://leetcode.com/problems/zigzag-conversion/description/?envType=problem-list-v2&envId=vaakbpfe
 *
 * <p>The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like
 * this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * <p>P A H N
 *
 * <p>A P L S I I G
 *
 * <p>Y I R
 *
 * <p>And then read line by line: "PAHNAPLSIIGYIR"
 */
public class ZigZag {
  public String convert(String s, int numRows) {
    // In case null, length less than no. of rows or no. of rows == 1 return the original string
    if (s == null || s.length() < numRows || numRows == 1) {
      return s;
    }

    // Get length
    int len = s.length();
    // Array of String buffers to store string at each level
    StringBuffer[] zigZag = new StringBuffer[numRows];
    // Consider a sign wave and get the window of one complete cycle i.g. wavelength
    int cycleIndex = 2 * (numRows - 1);
    for (int i = 0; i < len; i++) {
      char c = s.charAt(i);
      // Determine the index based on the wavelength
      int cycle = i % cycleIndex;
      int index;
      // If it is less than the numRows - 1 : then it is correct index
      // leave it as is
      if (cycle <= numRows - 1) {
        index = cycle;
      } else {
        // if it is greater then adjust it to be valid one
        index = cycleIndex - cycle;
      }
      // Create String buffer if not present
      if (zigZag[index] == null) zigZag[index] = new StringBuffer();
      // Append to the buffer
      zigZag[index].append(c);
    }
    StringBuffer zigZagBuff = new StringBuffer();
    for (int i = 0; i < numRows; i++) {
      zigZagBuff.append(zigZag[i]);
    }
    // Gather the final string
    return zigZagBuff.toString();
  }
}
