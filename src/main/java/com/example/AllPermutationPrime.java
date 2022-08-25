package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Készíts egy olyan függvényt, aminek
 * bemenő paramétere n,
 * és válaszként kilistázza azokat a számokat 1-től n-ig,
 * amelyeknek az összes körkörös permutációja prím.
 */
public class AllPermutationPrime {

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        boolean prime = true;
        int divider = 2;
        int limit = (int) Math.sqrt(n) + 1;
        while ((divider < limit) && prime) {
            if (n % divider == 0) {
                prime = false;
            }
            else {
                divider++;
            }
        }
        return prime;
    }

    private static List<Integer> generateCircledPermutations(int checkedNumber) {
        String pattern = String.valueOf(checkedNumber);       
        int numberOfDigits = pattern.length();
        pattern += pattern;
        List<Integer> permutations = new ArrayList<>();
        for (int i = 0; i < numberOfDigits; i++) {
            permutations.add(Integer.valueOf(pattern.substring(i, i + numberOfDigits)));
        }
        return permutations;
    }

    public static List<Integer> allCircledPermutationsArePrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
            .map(AllPermutationPrime::generateCircledPermutations)
            .filter(permutations -> permutations.stream().allMatch(AllPermutationPrime::isPrime))
            .map(permutations -> permutations.get(0))
            .collect(Collectors.toList());
    }
}
