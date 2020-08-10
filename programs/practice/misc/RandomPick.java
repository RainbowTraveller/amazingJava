/*  This is actually a very practical problem which appears often in the scenario where we need to do sampling over a set of data.
*
*  Nowadays, people talk a lot about machine learning algorithms. As many would reckon, one of the basic operations involved in training a machine learning algorithm (e.g. Decision Tree) is to sample a batch of data and feed them into the model, rather than taking the entire data set. There are several rationales behind doing sampling over data, which we will not cover in detail, since it is not the focus of this article.
*
*  If one is interested, one can refer to our Explore card of Machine Learning 101 which gives an overview on the fundamental concepts of machine learning, as well as the Explore card of Decision Tree which explains in detail on how to construct a decision tree algorithm.
*
*  Now, given the above background, hopefully one is convinced that this is an interesting problem, and it is definitely worth solving.
*
*  Intuition
*
*  Given a list of positive values, we are asked to randomly pick up a value based on the weight of each value. To put it simple, the task is to do sampling with weight.
*
*  Let us look at a simple example. Given an input list of values [1, 9], when we pick up a number out of it, the chance is that 9 times out of 10 we should pick the number 9 as the answer.
*
*  In other words, the probability that a number got picked is proportional to the value of the number, with regards to the total sum of all numbers.
*
*  To understand the problem better, let us imagine that there is a line in the space, we then project each number into the line according to its value, i.e. a large number would occupy a broader range on the line compared to a small number. For example, the range for the number 9 should be exactly nine times as the range for the number 1.
*
*  Now, let us throw a ball randomly onto the line, then it is safe to say there is a good chance that the ball will fall into the range occupied by the number 9. In fact, if we repeat this experiment for a large number of times, then statistically speaking, 9 out of 10 times the ball will fall into the range for the number 9.
*
*  Voila. That is the intuition behind this problem.
*
*
*  If one comes across this problem during an interview, one can consider the problem almost resolved, once one reduces the original problem down to the problem of inserting an element into a sorted list.
*
*  Concerning the above problem, arguably the most intuitive solution would be linear search. Many of you might have already thought one step ahead, by noticing that the input list is sorted, which is a sign to apply a more advanced search algorithm called binary search.
*
*  Let us do one thing at one time. In this approach, we will first focus on the linear search algorithm so that we could work out other implementation details. In the next approach, we will then improve upon this approach with a binary search algorithm.
*
*  So far, there is one little detail that we haven't discussed, which is how to randomly generate a target offset for the ball. By "randomly", we should ensure that each point on the line has an equal opportunity to be the target offset for the ball.
*
*  In most of the programming languages, we have some random() function that generates a random value between 0 and 1. We can scale up this randomly-generated value to the entire range of the line, by multiplying it with the size of the range. At the end, we could use this scaled random value as our target offset.
*
*  As an alternative solution, sometimes one might find a randomInteger(range) function that could generate a random integer from a given range. One could then directly use the output of this function as our target offset.
*
*  Here, we adopt the random() function, since it could also work for the case where the weights are float values.
*
*  Algorithm
*
*  We now should have all the elements at hand for the implementation.
*
*  First of all, before picking an index, we should first set up the playground, by generating a list of prefix sums from a given list of numbers. The best place to do so would be in the constructor of the class, so that we don't have to generate it again and again at the invocation of pickIndex() function.
*
*  In the constructor, we should also keep the total sum of the input numbers, so that later we could use this total sum to scale up the random number.
*  For the pickIndex() function, here are the steps that we should perform.
*
*  Firstly, we generate a random number between 0 and 1. We then scale up this number, which will serve as our target offset.
*
*  We then scan through the prefix sums that we generated before by linear search, to find the first prefix sum that is larger than our target offset.
*
*  And the index of this prefix sum would be exactly the right place that the target should fall into. We return the index as the result of pickIndex() function.
*/


import java.util.Random;
import java.util.TreeMap;

public class RandomPick {
    Random random = new Random();
    TreeMap<Integer, Integer> treemap = new TreeMap<>();
    int total = 0;

    public static void main(String[] args) {

    }

    public RandomPick(int[] w) {
        int sum = 0;
        //Calculate cumulative sum
        //This defines the range and more the probability value greater is the range
        //which also means when a random number is obtained within the entire range
        //it is likely to fall within in the range of a element with higher priority making it
        //selection candidate

        for (int i = 0; i < w.length; i++) {
            treemap.put(sum, i);
            sum += w[i];
        }
        this.total = sum;
    }

    public int pickIndex() {
        int random_num = random.nextInt(this.total);
        Integer key = treemap.floorKey(random_num);
        return treemap.get(key);
    }
}
