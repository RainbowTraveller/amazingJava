import java.lang.StringBuffer;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;


public class TextJustification {
    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String [] arr = {"This", "is", "an", "example", "of", "text", "justification."};
        // String [] arr = {"What","must","be","acknowledgment","shall","be"};
        // String [] arr = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        // String [] arr = {"a", "b", "c", "d", "e", "f", "abdsljadal"};
        for(String eachLine : tj.fullJustify(arr, 13)) {
            System.out.println("Justified Line  :" +  eachLine + ":" + eachLine.length());
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        //Tracking the words in the array
        int index = 0;
        //Current word
        String currWord;
        //Final list of justified lines
        List<String> justified = new ArrayList<>();
        while(index < words.length) {
            StringBuffer line = new StringBuffer();
            int limit = maxWidth;
            int wc = 0;
            while(index < words.length && limit >= words[index].length()) {
                currWord = words[index];
                index++;
                wc++;
                line.append(currWord);
                line.append(" ");
                limit -= currWord.length() + 1;
            }

            if(line.charAt(line.length() - 1) == ' ') {
                line.deleteCharAt(line.length() - 1);
                limit++;
            }

            System.out.println("LINE: :" + line + ":" + " LIMIT : " + limit);
            if(limit > 0 && index < words.length) {
                List<Integer> spaceDistribution = getSpaceDistribution(limit, wc - 1);
                if(spaceDistribution != null) {
                    String[]parts = line.toString().split("\\s+");
                    line.delete(0, line.length());
                    int spaceIndex = 0;
                    for(String word : parts) {
                        line.append(word);
                        if(line.length() < maxWidth && spaceIndex < spaceDistribution.size() && line.length() + spaceDistribution.get(spaceIndex) < maxWidth) {
                            line.append(generate(()->" ").limit(spaceDistribution.get(spaceIndex++) + 1).collect(joining()));
                            System.out.println("Length : " + line.length());
                        }
                    }
                }
            }

            if(line.length() < maxWidth) {
                line.append(generate(()->" ").limit(maxWidth - line.length()).collect(joining()));
            }
            // System.out.println("JUST LINE " + line);
            justified.add(line.toString());
        }
        return justified;
    }

    private List<Integer> getSpaceDistribution(int spacesRemaining, int spaceCount) {
        if(spaceCount == 0) {
            return null;
        }
        int distributed = spacesRemaining / spaceCount;
        int remaining  = spacesRemaining % spaceCount;
        System.out.println("distributed : " + distributed + " ::: " + remaining);
        List<Integer> spaceDistribution = new ArrayList<>();
        for(int i = 0; i < spaceCount; ++i) {
            spaceDistribution.add(distributed);
        }
        spaceDistribution.add(remaining);
        System.out.println("Dist : " + spaceDistribution);
        return spaceDistribution;
    }
}
