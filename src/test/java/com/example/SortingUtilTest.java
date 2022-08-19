package com.example;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortingUtilTest {

    @Test
    public void simpleSortingTest() {

        assertArrayEquals(new int[]{1,2,3,4}, SortingUtil.sortWithoutCycle(new int[]{3,4,1,2}));
    }
}
