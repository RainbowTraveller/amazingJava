/*
 * Problem 1:
 * Given a list or array of words. The words in the array are not unique, they may repeat.
 * Now given input 2 words contained in the array, find the shortest distance between them
 *
 *
 * Problem 2:
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between the occurrence of these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 *
 * Example 1:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 * Example 2:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * Output: 3
 *
 * Problem 3:
 * Make it into a class and method
 *
 */

// This is solution for all the above 3 problems combined
public class ShortestDistanceWords {
  // Storing the input words
  String[] words;

  public static void main(String[] args) {

    // String[] words = { "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c",
    // "d",  "a", "b","c", "d",  "a", "b","c", "d","a", "k", "d" };
    // String[] words = {"a", "b", "c", "d"};
    String[] words = {"practice", "makes", "perfect", "coding", "makes"};
    ShortestDistanceWords sdw = new ShortestDistanceWords(words);
    System.out.println(
        "Min Distance : makes, coding : " + sdw.shortestWordDistance("makes", "coding"));
    System.out.println(
        "Min Distance : makes, makes : " + sdw.shortestWordDistance("makes", "makes"));
    System.out.println(
        "Min Distance : practice, coding : " + sdw.shortestWordDistance("practice", "coding"));
  }

  public ShortestDistanceWords(String[] words) {
    this.words = words;
  }

  /**
   * This method finds the shortest distance between two words in the array.
   * It handles the case when both words are same and when they are different.
   *
   * @param word1 First word
   * @param word2 Second word
   * @return Shortest distance between the two words
   */
  public int shortestWordDistance(String word1, String word2) {
    int w1 = -1, w2 = -1;
    int distance = Integer.MAX_VALUE;
    int index = 0;
    int oldIndex = -1;

    for (String word : words) {
      if (!word1.equals(word2)) {
        if (word.equals(word1)) {
          w1 = index;
        } else if (word.equals(word2)) {
          w2 = index;
        }

        if (w1 >= 0 && w2 >= 0) {
          distance = Math.min(distance, Math.abs(w1 - w2));
        }
      } else {
        if (word.equals(word1)) {
          if (oldIndex < 0) {
            oldIndex = index;
          } else {
            distance = Math.min(distance, Math.abs(oldIndex - index));
            oldIndex = index;
          }
        }
      }
      index++;
    }
    return distance;
  }

  /** This is a simpler version of the above method which does not handle the case when both words are same.
   * @param wordsDict Array of words
   * @param word1 First word
   * @param word2 Second word
   * @return Shortest distance between the two words
   */
   public int shortestDistanceSimple(String[] wordsDict, String word1, String word2) {

        int w1Index = -1;
        int w2Index = -1;
        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < wordsDict.length; ++i) {
            if(wordsDict[i].equals(word1)) {
                w1Index = i;
            }
            if(wordsDict[i].equals(word2)) {
                w2Index = i;
            }
            if(w2Index >= 0 && w1Index >= 0) {
                minDistance = Math.min(minDistance, Math.abs(w1Index - w2Index));
            }
        }
        return minDistance;
    }
}
}
