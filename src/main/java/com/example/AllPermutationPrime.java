package com.example;

import java.util.ArrayList;

public class AllPermutationPrime {

    private static boolean isPrime(int n) {        
        boolean prime = true;
        int divider = n - 1;
        while ((divider > 1) && prime) {
            if (n % divider == 0) {
                prime = false;
            }
            else {
                divider--;
            }
        }
        return prime;
    }

    private static int[] generateCircledPermutations(int n) {
        String number = String.valueOf(n);
        int[] permutations = new int[number.length()];
        return permutations;
    }

    public static int[] allCircledPermutationsArePrimes(int n) {
        ArrayList<Integer> goodNumbers = new ArrayList<>();
        if (n < 2) {
            return new int[0];
        }
        else if (n < 10) {
            if (isPrime(n)) {
                return new int[]{n};
            }
            else {
                return new int[0];
            }
        }
        for (int checkedNumber = n; checkedNumber > 1; checkedNumber--) {
            int[] permutations = generateCircledPermutations(checkedNumber);
            boolean allPrime = true;
            int index = 0;
            while ((index < permutations.length) && allPrime) {
                if (!isPrime(permutations[index])) {
                    allPrime = false;
                }
                else {
                    index++;
                }
            }
            if (allPrime) {
                goodNumbers.add(checkedNumber);
            }
        }
        int[] goodNumbersArray = new int[goodNumbers.size()];
        goodNumbers.toArray(goodNumbersArray);
        return goodNumbersArray;
    }
}
