package com.practice.testing;

import java.util.Arrays;

public class SumCalculator {
    public int calculateSumOfArray(int[] numbers) {
        int sum = Arrays.stream(numbers)
                .reduce(0, Integer::sum);
        return sum;
    }
}