public class MergeStringAlternate {
    public static void main(String[] args) {
        MergeStringAlternate msa = new MergeStringAlternate();
        System.out.println(msa.mergeAlternately("abc","pqr"));
        System.out.println(msa.mergeAlternately("ab","pqrs"));
        System.out.println(msa.mergeAlternately("zhriq","af"));
    }

     public String mergeAlternately(String word1, String word2) {
        StringBuffer buffer = new StringBuffer();
        int l1 = word1.length();
        int l2 = word2.length();
        int i = 0 ,j = 0;
        while(i < l1 && j < l2) {
            buffer.append(word1.charAt(i));
            buffer.append(word2.charAt(j));
            i++;
            j++;
        }
        word1 = l1 > l2 ? word1 : word2;
        int index = 0;
        if(i == l1) {
            index = j;
        } else {
            index = i;
        }
        buffer.append(word1.substring(index));
        return buffer.toString();
    }
}
