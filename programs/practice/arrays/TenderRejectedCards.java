import java.io.*;
import java.util.*;

/*
A group of friends went to a small restaurant for dinner. They split the bill with a bunch of credit cards after the dinner.
Unfortunately some of these cards were rejected. Even more unfortunately the small restaurant uses a very poor point of sale, outputting very limited information.
It only reports the number of rejected tenders and the total rejected amount. The cashier has already known how much should be charged on each card.
Could you help the cashier figure out which cards are rejected?

Example:
Tenders: {1, $18}, {2, $14}, {3, $21}, {4, $10}, {5, $32}
Num of rejected cards : 2
Rejected amount : $50

Expected Output: <1, 5>
*/

public class TenderRejectedCards {
  public static void main(String[] args) {

    Map<Integer, Integer> data = new HashMap<>();
    data.put(1, 18);
    data.put(2, 14);
    data.put(3, 21);
    data.put(4, 10);
    data.put(5, 32);

    findCards(data, 2, 50);
  }

  public static void findCards(Map<Integer, Integer> cardAmounts, int noOfCards, int balance) {

    // Get list of Map entries for convenience of iterating
    List<Map.Entry<Integer, Integer>> cardEntries = new LinkedList<>(cardAmounts.entrySet());
    // Soring fort systematic managing the cards
    Collections.sort(cardEntries, (e1, e2) -> e1.getValue() - e2.getValue());
    // Final list of cards to be used
    List<Map.Entry<Integer, Integer>> foundCards = new LinkedList<>();
    boolean found = findCardHelper(0, noOfCards, balance, foundCards, cardEntries, noOfCards);
    if (found) {
      System.out.println(foundCards);
    }
  }

  public static boolean findCardHelper(
      int index,
      int noOfCards,
      int balance,
      List<Map.Entry<Integer, Integer>> foundCards,
      List<Map.Entry<Integer, Integer>> cardEntries,
      int TotalNoOfCards) {
    boolean result = false;
    if (balance == 0 && noOfCards == 0) {
      // Desired state, all good return
      result = true;
    } else if (balance > 0) {
      // The card at previous index is already considered
      // start with the card at next index
      for (int i = index; i < cardEntries.size(); ++i) {
        // Get card at currnt index and add to list and call the function with next index and
        // updated balance
        Map.Entry<Integer, Integer> currCard = cardEntries.get(i);
        foundCards.add(currCard);

        // Collect the result with next index and updated balance amount
        result =
            findCardHelper(
                index + 1,
                noOfCards - 1,
                balance - currCard.getValue(),
                foundCards,
                cardEntries,
                TotalNoOfCards);
        // If not found
        if (!result && !foundCards.isEmpty()) {
          // remove the previous card
          // no need to update balance as we changes it locally for next function call
          foundCards.remove(foundCards.size() - 1);
          //                    System.out.println("Removed : " + foundCards);
        } else {
          // in case true, return the result
          return result;
        }
      }
    }
    // If balance is zero or less than zero this will reach
    return result;
  }
}
