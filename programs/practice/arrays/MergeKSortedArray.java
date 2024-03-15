import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MergeKSortedArray {

    static class Tracker {
        int arrayIndex;
        int index;
        int element;
        
        public Tracker(int arrIndex, int index, int num) {
            this.arrayIndex = arrIndex;
            this.index = index;
            this.element = num;
        }
        
        public String toString() {
            return new String("ROW : " + arrayIndex + " Col :" + index + " NUM :" + element);
        }
    }    
    
    
    
        
    static List<Integer> mergeKSortedArrays(int[][] sortedArrs) {
        List<Integer> result = new LinkedList<>(); 
        int k = sortedArrs.length;
        PriorityQueue<Tracker> pq = new PriorityQueue<>(k,(i1,i2) -> (i1.element - i2.element));
        for(int i = 0; i < k; i++) {
            Tracker currTracker = new Tracker(i, 0, sortedArrs[i][0]);
            pq.offer(currTracker);
        }
        //pq.stream().forEach(System.out::println);
                
        while(!pq.isEmpty()) {
            Tracker cTracker = pq.poll();
            result.add(cTracker.element);
            int currArrayIndex = cTracker.arrayIndex;
            int currIndex = cTracker.index;
            if(sortedArrs[cTracker.arrayIndex].length > (currIndex + 1)) { 
                //System.out.println(cTracker);
                pq.offer(new Tracker(currArrayIndex,  currIndex+ 1, sortedArrs[currArrayIndex][currIndex + 1]));
            }   
        }
        
        return result;
        
    }

    public static void main(String[] args) {
       
       int [][] a =  {
           {2,3,4,6,7},
           {1,5,8,9,11},
           {0,10,12,13,14,15}
       };
    
       List<Integer> result = MergeKSortedArray.mergeKSortedArrays(a);
       
       result.stream().forEach(System.out::println);
       
       
       
    }
}
