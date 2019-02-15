import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SubListIndex {
	/*
	 * finds and returns index of list2 in list1
	 * if not found returns -1
	 * */
    public ArrayList<Integer> list1 = null;
    public ArrayList<Integer> list2 = null;

    public static void main(String args[] ) throws Exception {
		SubListIndex soln = new	SubListIndex();
		soln.init();
		soln.printLists();
		System.out.println("Index : " + soln.find());
    }


	public	SubListIndex() {
		list1 = new ArrayList<Integer>();
		list2 = new ArrayList<Integer>();
	}

	public void init() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Please Enter a len 1: ");
            int len1 = in.nextInt();
            System.out.println("Please Enter a elements: ");
            for(int i = 0; i < len1; ++i) {
                list1.add(in.nextInt());
            }
            System.out.println("Please Enter a len 2: ");
            int len2 = in.nextInt();
            System.out.println("Please Enter a elements: ");
            for(int i = 0; i < len2; ++i) {
                list2.add(in.nextInt());
            }
        } catch (InputMismatchException ims) {

        }
    }

	public void printLists(){
		for(int i : list1) {
			System.out.println(i);
		}
		System.out.println("-------");
		for(int i : list2) {
			System.out.println(i);
		}
	}

    public int find() {
		if(list1.size() > 0 && list2.size() > 0) {
			for(int i = 0; i < list1.size(); ++i) {
				if(list1.get(i) == list2.get(0)) {
					if(findHelper(i + 1)) {
						return i;
					}
				}
			}
		}
		return -1;
    }

	private boolean findHelper(int startIndex) {
		int list2Tracker = 1;
		boolean result = false;
		while(startIndex < list1.size() && list2Tracker < list2.size()) {
			if(list1.get(startIndex) != list2.get(list2Tracker)) {
				break;
			}
			startIndex++;
			list2Tracker++;
		}
		if(list2Tracker == list2.size()) {
			result = true;
		}
		return result;
	}

}
