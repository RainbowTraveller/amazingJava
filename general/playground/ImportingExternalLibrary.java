import java.util.List;
import java.util.ArrayList;
import java.lang.Exception;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.CollectionUtils;

public class ImportingExternalLibrary {
    public static void main(String [] args) {
        List<Integer>  mylist= new ArrayList<Integer>();
        try {
            mylist.add(1);
            mylist.add(2);
            mylist.add(3);
            mylist.add(4);
            mylist.add(5);
            mylist.add(6);
            mylist.add(7);
            mylist.add(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListDemo.printList(mylist);
        if( ListDemo.find(11) != null) {
            System.out.println("Number Found");
        }
        System.out.println("Deleting A number");
       // try{
            //mylist.remove(new Integer(11));
            mylist.remove("Integer");
        //} catch(Exception e) {
         //   e.printStackTrace();
        //}
        ListDemo.printList(mylist);
    }

   public static void printList(List<?> mylist) {
       for(Object o : mylist) {
           System.out.println(o);
       }
   }

   public Integer find(int num, List<Integer>lst) throws Exception {
       return (Integer) CollectionUtils.find(lst, new Predicate() {
           public boolean evaluate(Object o) {
               return ((Integer)o == id);
           }
       });
   }

}
