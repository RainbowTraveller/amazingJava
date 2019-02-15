import java.util.LinkedList;
import java.util.ListIterator;

public class TestLinkedList {

    private LinkedList<Integer> ilist;

    public TestLinkedList() {
        ilist = new LinkedList<Integer>();
    }

    public void variousTests(){
        ilist.addFirst(10);
        ilist.addFirst(40);
        ilist.addFirst(60);
        ilist.addFirst(80);
        ilist.addFirst(null);
        printList();
        ListIterator<Integer> liter = ilist.listIterator();
        while(liter.hasNext()) {
            Integer num = liter.next();
            if(num != null && num != 40){
                System.out.println("Print Num" + num );
                liter.remove();
            } else {
                liter.remove();
            }

        }

    }
    public void printList() {
        System.out.println("PRINTING");
        for(Object i : ilist){
            System.out.println(i);
        }
    }
    public static void main(String[] args) {

        TestLinkedList tll = new TestLinkedList();
        tll.variousTests();


    }



}
