package com.example;

import java.util.ArrayList;

/**
 * Készíts egy olyan függvényt, aminek
 * bemenő paramétere n,
 * és válaszként kilistázza azokat a számokat 1-től n-ig,
 * amelyeknek az összes körkörös permutációja prím.
 */
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

    private static int[] generateCircledPermutations(int checkedNumber) {
        String number = String.valueOf(checkedNumber);       
        int numberOfDigits = number.length();
        number += number;
        int[] permutations = new int[numberOfDigits];
        for (int i = 0; i < numberOfDigits; i++) {
            permutations[i] = Integer.parseInt(number.substring(i, i + numberOfDigits));
        }
        return permutations;
    }

    public static int[] allCircledPermutationsArePrimes(int n) {
        ArrayList<Integer> goodNumbers = new ArrayList<>();
        for (int checkedNumber = 2; checkedNumber <= n; checkedNumber++) {
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
        for (int i = 0, l = goodNumbers.size(); i < l; i++) {
            goodNumbersArray[i] = goodNumbers.get(i);
        }
        return goodNumbersArray;
    }
}
