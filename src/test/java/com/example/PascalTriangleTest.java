package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import static com.example.PascalTriangle.*;

public class PascalTriangleTest {
    @Test
    public void testNthRow() {
        int n = 0;
        assertArrayEquals(new int[]{1}, NthRow(n));
        n = 1;
        assertArrayEquals(new int[]{1, 1}, NthRow(n));
        n = 2;
        assertArrayEquals(new int[]{1, 2, 1}, NthRow(n));
        n = 3;
        assertArrayEquals(new int[]{1, 3, 3, 1}, NthRow(n));
        n = 4;
        assertArrayEquals(new int[]{1, 4, 6, 4, 1}, NthRow(n));
        n = 5;
        assertArrayEquals(new int[]{1, 5, 10, 10, 5, 1}, NthRow(n));
    }
}
