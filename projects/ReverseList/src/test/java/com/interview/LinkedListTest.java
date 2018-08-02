package com.interview;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LinkedListTest {
    /**
     *  Check instantiation
     */
	@Test
    public void testLinkeList_initial(){
		LinkedList ll = new LinkedList();
		assertNull(ll.head);
		assertNull(ll.tail);
    }
	@Test
    public void testLinkeList_reverse(){
		LinkedList input = new LinkedList();
		input.add(1);
		input.add(2);
		input.add(3);
		LinkedList ref = new LinkedList();
		ref.add(3);
		ref.add(2);
		ref.add(1);
		input.head = input.reverse(input.head);
		String input_str = input.toString();
		String ref_str = ref.toString();
		input.head = input.reverse(input.head);
		assertEquals(input_str, ref_str);
    }

	@Test
    public void testLinkeList_reverse_fail(){
		LinkedList input = new LinkedList();
		input.add(1);
		input.add(2);
		input.add(3);
		LinkedList ref = new LinkedList();
		ref.add(4);
		ref.add(3);
		ref.add(1);
		input.head = input.reverse(input.head);
		String input_str = input.toString();
		String ref_str = ref.toString();
		input.head = input.reverse(input.head);
		assertNotSame(input_str, ref_str);
    }
}
