package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class AllPermutationPrimeTest {
    
    @Test
    public void testAllCircledPermutationsArePrimes() {
        assertEquals(Arrays.asList(new Integer[0]), AllPermutationPrime.allCircledPermutationsArePrimes(1));
        assertEquals(Arrays.asList(2, 3), AllPermutationPrime.allCircledPermutationsArePrimes(3));
        assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17), AllPermutationPrime.allCircledPermutationsArePrimes(25));
}
    
}
