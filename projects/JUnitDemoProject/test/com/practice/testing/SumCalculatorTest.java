package com.practice.testing;

import com.practice.testing.SumCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumCalculatorTest {
    SumCalculator calculator = new SumCalculator();
    @Test
    public void testSimpleArray(){
        assertEquals(15, calculator.calculateSumOfArray(new int[] {1,2,3,4,5}));

    }

    @Test
    public void testEmptyArray() {

        assertEquals(0, calculator.calculateSumOfArray(new int [] { }));
    }
}
