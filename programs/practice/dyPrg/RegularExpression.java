public class RegularExpression {
    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        System.out.println("Text : aaab Pattern : ac*aab " + re.isMatch("aaab", "ac*aab"));
        System.out.println("Text : caaab Pattern : ca*b " + re.isMatch("aaab", "ac*aab"));
        System.out.println("Text : aaab Pattern : .* " + re.isMatch("aaab", ".*"));
        System.out.println("Text : aaab Pattern : * " + re.isMatch("aaab", "*"));
    }

    public boolean isMatch(String text, String pattern) {
        // if both the text and patten reached the end then
        // we have perfect match
        if (pattern.isEmpty()) return text.isEmpty();

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
                    (firstCharMatch && isMatch(text.substring(1), pattern)));
        } else {
            // first char match and no * so match remaining substrings from text and pattern
            return firstCharMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }
}
