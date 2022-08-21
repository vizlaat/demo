package com.example;

public class SortingUtil {
    
    private static int[] sortRecursive(int[] array, int indexLow, int indexHigh) {
        if (array[indexLow] > array[indexHigh]) {
            int temp = array[indexLow];
            array[indexLow] = array[indexHigh];
            array[indexHigh] = temp;
        }
        if (indexHigh == array.length - 1) {
            indexLow++;
            indexHigh = indexLow + 1;
        }
        else {
            indexHigh++;
        }
        if (indexLow == array.length - 1) {
            return array;
        }
        return sortRecursive(array, indexLow, indexHigh);
    }

    public static int[] sortWithoutCycle(int[] somethingToSort) {
        if (somethingToSort.length < 2) {
            return somethingToSort;
        }
        int indexLow = 0, indexHigh = 1;
        return sortRecursive(somethingToSort, indexLow, indexHigh);
    }

}
