package com.practice.testing;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {
    List<String> cloud = Arrays.asList("AWS", "GCP", "Azure");
    @Test
    public void test() {
        assertNotNull(cloud);
        assertEquals(3, cloud.size());
        assertTrue(cloud.contains("AWS"), "List contains AWS");
        assertFalse(cloud.contains("On Prim"), "List does not contain On Prim");
        assertArrayEquals(new int[] {1, 2}, new int[]{1,2});
    }
}
