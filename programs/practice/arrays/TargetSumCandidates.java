/*
 *  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 *  The same repeated number may be chosen from candidates unlimited number of times.
 *
 *  Note:
 *
 *  All numbers (including target) will be positive integers.
 *  The solution set must not contain duplicate combinations.
 *  Example 1:
 *
 *  Input: candidates = [2,3,6,7], target = 7,
 *  A solution set is:
 *  [
 *    [7],
 *    [2,2,3]
 *  ]
 *  Example 2:
 *
 *  Input: candidates = [2,3,5], target = 8,
 *  A solution set is:
 *  [
 *    [2,2,2,2],
 *    [2,3,3],
 *    [3,5]
 *  ]
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class TargetSumCandidates {
    public static void main(String[] args) {
		int [] input = {2,3,6,7};
		int target = 7;
		System.out.println("Combination Sum  : " + combinationSum(input, target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Set<List<Integer>> ulist = new HashSet<>();

        Arrays.sort(candidates);
        helper(candidates, target, 0, ulist, new LinkedList<Integer>() );
        for(List<Integer> valid : ulist) {
            list.add(valid);
        }
        return list;
    }

    public static void helper(int[] candidates, int target, int currSum, Set<List<Integer>>list,  List<Integer> curr) {
        if(currSum == target) {
            //System.out.println(curr + " :: " + currSum);
            LinkedList<Integer> valid = new LinkedList<>(curr);
            Collections.sort(valid);
            list.add(valid);
        } else {
            for(int i = 0; i < candidates.length; ++i) {
                currSum += candidates[i];
                if(currSum <= target) {
                    curr.add(candidates[i]);
                    helper(candidates, target, currSum, list, curr);
                    curr.remove(curr.size() - 1);
                    //System.out.println(curr + " :: " + currSum);
                }
                currSum -= candidates[i];
            }
        }
    }
}



import java.io.*;
import java.util.*;

/*
A group of friends went to a small restaurant for dinner. They split the bill with a bunch of credit cards after the dinner. Unfortunately some of these cards were rejected. Even more unfortunately the small restaurant uses a very poor point of sale, outputting very limited information. It only reports the number of rejected tenders and the total rejected amount. The cashier has already known how much should be charged on each card. Could you help the cashier figure out which cards are rejected?

Example:
Tenders: {1, $18}, {2, $14}, {3, $21}, {4, $10}, {5, $32}
Num of rejected cards : 2
Rejected amount : $50

Expected Output: <1, 5>

start with each card
    decrese amount and card no.

       card no. 0 and amount is remaining
       card and amount is 0 : found a solution

   10,14,18,21,32

1 : 40 1
        26 0
        28 0
        19 0
        32 0
2 : 36 1
    18 0
3 : 29 1


 */

class Solution {
  public static void main(String[] args) {

    Map<Integer, Integer> data = new HashMap<>();
    data.put(1, 18);
    data.put(2, 14);
    data.put(3, 21);
    data.put(4, 10);
    data.put(5, 32);

    findCards(data,2,50);

  }

  public static void findCards(Map<Integer, Integer> cardAmounts, int noOfCards, int balance) {

    List<Map.Entry<Integer, Integer>> cardEntries = new LinkedList<>(cardAmounts.entrySet());
    Collections.sort(cardEntries, (e1, e2) -> e1.getValue() - e2.getValue());
    //System.out.println(cardEntries);

    List<Map.Entry<Integer, Integer>> foundCards = new LinkedList<>();
    for(int i = 0; i < cardEntries.size(); ++i) {
      Map.Entry<Integer, Integer> currCard = cardEntries.get(i);
      foundCards.add(currCard);
      boolean found = findCardHelper(i + 1, noOfCards - 1, balance - currCard.getValue(), foundCards,  cardEntries, noOfCards);
      if (found) {
        break;
      }
      foundCards.clear();
    }

    System.out.println(foundCards);
  }


  /*
   10  40 26
       14 18 21

  */


  public static boolean findCardHelper(int index, int noOfCards, int balance, List<Map.Entry<Integer, Integer>> foundCards, List<Map.Entry<Integer, Integer>> cardEntries, int TotalNoOfCards) {

    boolean result = false ;
    for( int i = index; i < cardEntries.size(); ++i) {

      Map.Entry<Integer, Integer> currCard = cardEntries.get(i);

      if(noOfCards == 1 && balance - currCard.getValue() == 0 ) {
        foundCards.add(currCard);
        return true;
      }

      if( balance < currCard.getValue() || (noOfCards == 0 && balance != 0) ) {
        foundCards.clear();
        break;
      }

      foundCards.add(currCard);
      result = findCardHelper(index + 1, noOfCards - 1, balance - currCard.getValue(), foundCards,  cardEntries,TotalNoOfCards);

      if( !result && !foundCards.isEmpty()) {
        foundCards.remove(foundCards.size() - 1);
        System.out.println("Removed : " + foundCards);
      }
    }
    return result;
  }


}

