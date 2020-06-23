public class RegularExpression {
    // For top down approach we will define a enum
    enum Result {
        TRUE, FALSE;
    }

    // Keeping track of enum result to avoid multiple calls
    Result[][] memo;

    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        System.out.println("Text : aaab Pattern : ac*aab " + re.isMatch("aaab", "ac*aab"));
        System.out.println("Text : caaab Pattern : ca*b " + re.isMatch("aaab", "ac*aab"));
        System.out.println("Text : aaab Pattern : .* " + re.isMatch("aaab", ".*"));
        System.out.println("Text : aaab Pattern : * " + re.isMatch("aaab", "*"));
        System.out.println("Text : a Pattern : .*..a* " + re.isMatch("a", ".*..a*"));
        System.out.println("Text : ab Pattern : .*c " + re.isMatch("ab", ".*c"));
    }

    public boolean isMatch(String text, String pattern) {
        // if both the text and patten reached the end then
        // we have perfect match
        if (pattern.isEmpty())
            return text.isEmpty();

        // Check only first character for match
        boolean firstCharMatch = (!text.isEmpty() && text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');

        // Now if pattern has length > 2 and there is a 'char*' sequence in the pattern
        // we have following cases
        // 1. 0 or more occurrences of `char` are required, so if we skip current char
        // from text and try matching remaining text as it may not be matching
        // 2. current char may be matching so proceed with next char in the text to
        // accommodate *
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // text : aaab pattern : ac*aab
            // here we can skip c* from the pattern
            return (isMatch(text, pattern.substring(2)) ||
            // text : caaab pattern : ca*b
            // here we matched first char which is first `a` from text and `a` from a*
            // sequence from pattern
            // so we need to match aab with a*b so text index moves but pattern remains same
            // second part isMatch call is repeated firstMatch char checking
                    (firstCharMatch && text.length() >= 1 && isMatch(text.substring(1), pattern)));
        } else {
            // first char match and no * so match remaining substrings from text and pattern
            return firstCharMatch && (text.length() >= 1 && pattern.length() >= 1
                    && isMatch(text.substring(1), pattern.substring(1)));
        }
    }

    public boolean isMathTopDown(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return topDownDP(0, 0, text, pattern);
    }

    /*
     * Method is organic progression from recursive approach it translates the above
     * approach using memoisation
     */
    private boolean topDownDP(int i, int j, String text, String pattern) {
        // Boolean won't work here because of this condition
        // so we need enum
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean ans;

        if (j == pattern.length()) {
            // If one ends other should end too
            ans = i == text.length();
        } else {
            // Check for first char match
            boolean firstCharMatch = (!text.isEmpty() && text.charAt(0) == pattern.charAt(0)
                    || pattern.charAt(0) == '.');

            // If next char is * we have 2 cases
            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                // Skip current and * chars as it means 0 or more occurrences
                // so check remaining pattern with original text after skipping these 2 chars
                ans = topDownDP(i, j + 2, text, pattern) || firstCharMatch && topDownDP(i + 1, j, text, pattern);
                // or if first character matched then we need to check if few more chars match
                // with *
                // so compared text from next char onwards with current pattern

            } else {
                // if no * proceed with next char from text as well as pattern
                ans = firstCharMatch && topDownDP(i + 1, j + 1, text, pattern);
            }
        }
        // Update the current position with result
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /**
     * Optimizing further down up approach
     */
    public boolean downUpDP(String text, String pattern) {
        // Allocate boolean matrix for tracking which has 1 extra row and column
        boolean[][] tracker = new boolean[text.length() + 1][pattern.length() + 1];

        // mark this extra element true before beginning
        tracker[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length(); j >= 0; j--) {
                // This is curr char match instead of first char match
                boolean firstCharMatch = (!text.isEmpty() && text.charAt(i) == pattern.charAt(j)
                        || pattern.charAt(j) == '.');
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    // in case of * as previous char ( as it is already visited coming backwards )
                    // 1. Check if pattern with current char and * is already matching
                    // 2. Current char matches and chars for * have been accounted for
                    tracker[i][j] = tracker[i][j + 2] || firstCharMatch && tracker[i + 1][j];
                } else {
                    // Or proceed with regular matching backwards
                    tracker[i][j] = firstCharMatch && tracker[i + 1][j + 1];
                }
            }
        }
        // return final result
        return tracker[0][0];
    }
}
