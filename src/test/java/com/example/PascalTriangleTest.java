package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PascalTriangleTest {
    @Test
    public void testNthRow() {
        assertArrayEquals(new int[]{1}, PascalTriangle.NthRow(0));
        assertArrayEquals(new int[]{1, 1}, PascalTriangle.NthRow(1));
        assertArrayEquals(new int[]{1, 2, 1}, PascalTriangle.NthRow(2));
        assertArrayEquals(new int[]{1, 3, 3, 1}, PascalTriangle.NthRow(3));
        assertArrayEquals(new int[]{1, 4, 6, 4, 1}, PascalTriangle.NthRow(4));
        assertArrayEquals(new int[]{1, 5, 10, 10, 5, 1}, PascalTriangle.NthRow(5));
    }
}
