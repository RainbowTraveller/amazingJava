/*
 * Given a list or array of words. The words in the array are not unique, they may repeat.
 * Now given input 2 words contained in the array, find the shortest distance between them
 */
public class ShortestDistanceWords {
	public static void main( String [] args ) {


		//String[] words = { "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c", "d",  "a", "b","c", "d","a", "k", "d" };
		String[] words = { "a", "b","c", "d"};
		ShortestDistanceWords sdw = new ShortestDistanceWords();
		String word1 = "a";
		String word2 = "d";
		int minDistance = sdw.shortestDistance( words, word1, word2 );
		System.out.println( "Min Distance : " + minDistance);
	}

    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int distance = 0;
        for(int i = 0; i < words.length; ++i) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(words[i].equals(word1)) {
                    index1 = i;
                }

                if(words[i].equals(word2)) {
                    index2 = i;
                }

                if(index1 >= 0 && index2 >= 0 ) {
                    distance = Math.max(distance, Math.abs(index1 - index2));
                }
            }
        }
        return distance;
    }
}
