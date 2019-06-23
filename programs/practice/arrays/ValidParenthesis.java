/*
*  Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
*
*  Note: The input string may contain letters other than the parentheses ( and ).
*
*  Example 1:
*
*  Input: "()())()"
*  Output: ["()()()", "(())()"]
*  Example 2:
*
*  Input: "(a)())()"
*  Output: ["(a)()()", "(a())()"]
*  Example 3:
*
*  Input: ")("
*  Output: [""]
*
*/
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;

class ValidParenthesis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the bracket pattern :");
        String pattern = sc.next();
        ValidParenthesis vp = new ValidParenthesis();
        System.out.println("Possible Valid Patterns : " + vp.removeInvalidParentheses(pattern));
    }

    public List<String> removeInvalidParentheses(String s) {
        Set<String> valid = new HashSet<String>();
        if (s != null && s.length() > 0) {
            valid.clear();
            //PART - 1
            //Exhaustive processing by checking each and every open and closed bracket
            //exhaustiveProcessing( s, new StringBuffer(), 0, 0, 0, valid, Integer.MAX_VALUE, 0);
            //exhaustiveSimplified( s, new StringBuffer(), 0, 0, 0, valid, 0)

            //PART - 2
            //Smart processing by considering only faulty parenteses
            //Calculating only faulty open or close brackets
            int leftRem = 0 , rightRem = 0;
            for(int i = 0; i < s.length(); ++i) {
                if(s.charAt(i) == '(') {
                    //Just count open brackets
                    leftRem++;
                } else if (s.charAt(i) == ')') {
                    //Check if there were previously found left ones, if not this is faulty closing bracket, count it
                    rightRem = leftRem == 0 ? rightRem + 1 : rightRem;
                    //Just reduce the number of matching left brackets
                    //as in if there are equal opening and closing brackets, both left and right will be 0 after this loop
                    leftRem = leftRem > 0 ? leftRem - 1 : 0;
                }
            }
            smartProcessing( s, new StringBuffer(), valid, 0, 0, 0, leftRem, rightRem);
        }
        if (valid.size() == 0) {
            valid.add(new String());
        }
        return new ArrayList<String>(valid);
    }

    /* Recursive function that is called to check each possibility by toggling choice of a character.
     * For each character first it is not considered and then again subsequent character is not considered and
     * considered. Then the current character is considered and then again following characters are considered and not
     * considered. Thus an exhaustive search is done. Each time we find we have reached the end of the input string
     * we check if left and right parentheses are matching. In that case we also track, how many brackets we have removed.
     * if we have removed less no. of brackets then we have better solution, so we removed all previous ones and insert this.
     * So we finally get all the valid bracket expressions with minimum invalid brackets removed
     *
     * @param s input string
     * @param b string buffer which collects the characters
     * @param index which character from input string is to be considered now
     * @param open no. of opening brackets encountered so far
     * @param close no. of closing brackets encountered so far
     * @param valid set of valid outputs so far
     * @param minRemoved min no of brackets removed so far
     * @param removed no. of brackets not considered so far
     *
     */
    public int exhaustiveProcessing(String s, StringBuffer b, int index, int open, int close,
                Set<String> valid, int minRemoved, int removed ) {
        if(index == s.length()) {
            if(open == close ) {
                //No point if we have already removed less no. of brackets, ignore if more no. of brackets removed
                if(removed <= minRemoved) {
                    String candidate = b.toString();
                    if(removed < minRemoved) {
                        //if less then we need to modify minRemoved value as we have found a better solution
                        valid.clear();
                        minRemoved = removed;
                    }
                    valid.add(candidate);
                    //System.out.println("Candidate : " + candidate);
                }
            }
        } else  {
            char curr = s.charAt(index);
            int len = b.length();
            if(  curr == '(' ||  curr == ')' ) {
                //First don't consider current bracket
                minRemoved = exhaustiveProcessing( s, b, index + 1, open, close, valid, minRemoved, removed + 1);
            }

            b.append(curr);
            if(curr != '(' && curr != ')') {
                minRemoved = exhaustiveProcessing( s, b, index + 1, open, close, valid, minRemoved, removed);
            }

            if(curr == '(') {
                minRemoved = exhaustiveProcessing( s, b, index + 1, open + 1, close, valid, minRemoved, removed);
            } else if( open > close ) {//We only consider the ) bracket if they are less in number than opening ones or obviously expression is
            //invalid, so no point in continuing recursion
                minRemoved = exhaustiveProcessing( s, b, index + 1, open, close + 1, valid, minRemoved, removed);
            }
            //This deletion does not mean not considering, we need to make sure previous call in recursion gets buffer agnostic of what happened in this recursive call
            b.deleteCharAt(len);
        }
        return minRemoved;
    }

    /*
     * The core approach remains the same, but follows a more logical flow which also gives same results
     * @param input input string
     * @param buff string buffer which collects the characters
     * @param currIndex which character from input string is to be considered now
     * @param open no. of opening brackets encountered so far
     * @param close no. of closing brackets encountered so far
     * @param valid set of valid outputs so far
     * @param maxLen max length of the output obtained so far
     */
    public int exhaustiveSimplified(String input, StringBuffer buf, int currIndex, int open, int close,
            Set<String> valid, int maxLen) {
        if (currIndex == input.length()) {
            if (open == close) {
                if (buff.length() >= maxLen) {
                    if (buff.length() > maxLen) {
                        maxLen = buff.length();
                        valid.clear();
                    }
                    valid.add(buff.toString());
                }
            }
        } else {
            char curr = input.charAt(currIndex);
            //if not one of the braces, add, consider subsequent patterns and then remove
            if (curr != ')' && curr != '(') {
                maxLen = removeInvalidParenthesis(valid, input, currIndex + 1, open, close, buff.append(curr), maxLen);
                buff.deleteCharAt(buff.length() - 1);
            } else {
                //First consider the current brace But only if it is '(',
                //for ')' number of open brackets should be more than close or expression is invalid
                //so no need to proceed
                if (curr == '(' || (curr == ')' && close < open)) {
                    maxLen = removeInvalidParenthesis(valid, input, currIndex + 1, curr == '(' ? open + 1 : open,
                            curr == ')' ? close + 1 : close, buff.append(curr), maxLen);
                    buff.deleteCharAt(buff.length() - 1);
                }
                maxLen = removeInvalidParenthesis(valid, input, currIndex + 1, open, close, buff, maxLen);
            }
        }
        //When we remove min no. of invalid brackets length will be max so keep
        //track of it and only consider string with max length
        return maxLen;
    }

    /* Reduces the running time considerably by only looking at faulty opening and closing brackets.Like previous approach
     * it toggles the selection of a particular character. But only keeping track of faulty ones when we are NOT CONSIDERING a character.
     * that way we only recurse for limited no. of times only for faulty no. of brackets by no considering them instead trying for all
     * brackets
     * @param s input string
     * @param b string buffer which collects the characters
     * @param valid set of valid outputs so far
     * @param index which character from input string is to be considered now
     * @param open no. of opening brackets encountered so far
     * @param close no. of closing brackets encountered so far
     * @param openRem no, remaining faulty open brackets
     * @param closeRem  no, remaining faulty close brackets
     *
     */
    public void smartProcessing(String s, StringBuffer b, Set<String> valid, int index, int open, int close, int openRem, int closeRem) {
        if(index == s.length()) {
            if(openRem == 0 && closeRem == 0) {
                valid.add(b.toString());
            }
        } else {
            char curr = s.charAt(index);
            int len = b.length();

            if( ( curr == '(' && openRem > 0 ) || ( curr == ')' && closeRem > 0) ) {
                //When we are not considering then only decrement the count of not considered brackets
                smartProcessing( s, b, valid, index + 1, open, close, openRem - ( curr == '(' ? 1 : 0), closeRem - ( curr == ')' ?  1 : 0));
            }

            b.append(curr);
            if(curr != '(' && curr != ')') {
                smartProcessing( s, b,valid, index + 1, open, close, openRem, closeRem);
            }
            if(curr == '(') {
                smartProcessing( s, b,valid, index + 1, open + 1, close, openRem , closeRem );
            } else if( open > close ) {//We only consider the ) bracket if they are less in number than opening ones or obviously expression is
            //invalid, so no point in continuing recursion
                smartProcessing( s, b,valid, index + 1, open, close + 1, openRem , closeRem );
            }
            //This deletion does not mean not considering, we need to make sure previous call in recursion gets buffer agnostic of what happened in this recursive call
            b.deleteCharAt(len);
        }
    }
}
