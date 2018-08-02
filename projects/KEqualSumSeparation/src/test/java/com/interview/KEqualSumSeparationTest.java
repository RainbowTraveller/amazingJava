package com.interview;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class KEqualSumSeparationTest {
    /**
     *  Check instantiation
     */
	@Test
    public void testKSeparation_sucess(){
		List<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(3);
		input.add(5);
		input.add(2);
		input.add(6);
		input.add(1);
		KEqualSumSeparation findkseparations = new KEqualSumSeparation();
		assertTrue(findkseparations.separate(input, 3));
    }

	@Test
    public void testKSeparation_fail(){
		List<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(3);
		input.add(5);
		input.add(2);
		input.add(6);
		input.add(6);
		KEqualSumSeparation findkseparations = new KEqualSumSeparation();
		assertFalse(findkseparations.separate(input, 3));
    }

	@Test
    public void testKSeparation_Null(){
		List<Integer> input = null;
		KEqualSumSeparation findkseparations = new KEqualSumSeparation();
		assertFalse(findkseparations.separate(input, 3));
    }
}
