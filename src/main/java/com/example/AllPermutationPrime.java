package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Készíts egy olyan függvényt, aminek
 * bemenő paramétere n,
 * és válaszként kilistázza azokat a számokat 1-től n-ig,
 * amelyeknek az összes körkörös permutációja prím.
 */
public class AllPermutationPrime {

    private static boolean isPrime(int n) {        
        boolean prime = true;
        int divider = 2;
        while ((divider < n) && prime) {
            if (n % divider == 0) {
                prime = false;
            }
            else {
                divider++;
            }
        }
        return prime;
    }

    private static boolean isAllPrime(ArrayList<Integer> numbers) {
        boolean allPrime = true;
        int index = 0;
        while ((index < numbers.size()) && allPrime) {
            if (!isPrime(numbers.get(index))) {
                allPrime = false;
            }
            else {
                index++;
            }
        }
        return allPrime;
    }

    private static ArrayList<Integer> generateCircledPermutations(int checkedNumber) {
        String pattern = String.valueOf(checkedNumber);       
        int numberOfDigits = pattern.length();
        pattern += pattern;
        ArrayList<Integer> permutations = new ArrayList<>();
        for (int i = 0; i < numberOfDigits; i++) {
            permutations.add(Integer.valueOf(pattern.substring(i, i + numberOfDigits)));
        }
        return permutations;
    }

    public static ArrayList<Integer> allCircledPermutationsArePrimes(int n) {
        ArrayList<Integer> checkedNumbers = new ArrayList<>();
        for (int checkedNumber = 2; checkedNumber <= n; checkedNumber++) {
            checkedNumbers.add(checkedNumber);
        }
        List<Integer> numbersWithAllTheirCircledPermutationsArePrimes = checkedNumbers.stream()
            .map((checkedNumber) -> generateCircledPermutations(checkedNumber))
            .filter((permutations) -> isAllPrime(permutations))
            .map((permutations) -> permutations.get(0))
            .collect(Collectors.toList());
        return new ArrayList<Integer>(numbersWithAllTheirCircledPermutationsArePrimes);
    }
}
