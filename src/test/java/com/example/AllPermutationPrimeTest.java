package com.example;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class AllPermutationPrimeTest {
    
    @Test
    public void testAllCircledPermutationsArePrimes() {
        assertArrayEquals(new int[0], AllPermutationPrime.allCircledPermutationsArePrimes(0));
        assertArrayEquals(new int[0], AllPermutationPrime.allCircledPermutationsArePrimes(1));
        assertArrayEquals(new int[]{2}, AllPermutationPrime.allCircledPermutationsArePrimes(2));
        assertArrayEquals(new int[]{2, 3}, AllPermutationPrime.allCircledPermutationsArePrimes(3));
        assertArrayEquals(new int[]{2, 3}, AllPermutationPrime.allCircledPermutationsArePrimes(4));
        assertArrayEquals(new int[]{2, 3, 5}, AllPermutationPrime.allCircledPermutationsArePrimes(5));
        assertArrayEquals(new int[]{2, 3, 5}, AllPermutationPrime.allCircledPermutationsArePrimes(6));
        assertArrayEquals(new int[]{2, 3, 5, 7}, AllPermutationPrime.allCircledPermutationsArePrimes(7));
        assertArrayEquals(new int[]{2, 3, 5, 7}, AllPermutationPrime.allCircledPermutationsArePrimes(10));
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13}, AllPermutationPrime.allCircledPermutationsArePrimes(13));
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17}, AllPermutationPrime.allCircledPermutationsArePrimes(25));
    }
    
}
