package com.example;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class  PascalTriangle{
  // It returns the Nth row from the Pascal Triangle
  //N: index of row, starting with 0
  //K: index of column, starting with 0
  //length of row: N + 1
  //number in Nth row, Kth column: (N, K) -> N! / [ ( N - K )! * K! ]
  public static int[] NthRow(int rowNum) {
    int[] row = new int[rowNum + 1];
    for (int column = 0; column < row.length; column++) {
      row[column] = (int) (CombinatoricsUtils.factorial(rowNum) / ((CombinatoricsUtils.factorial(rowNum - column) * CombinatoricsUtils.factorial(column))));      
    }
    return row;
  }
}
