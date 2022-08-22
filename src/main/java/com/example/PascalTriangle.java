package com.example;

public class  PascalTriangle{
	public static int factorial(int number) {
		int fact = 1;
		for(int i = 1; i <= number; ++i) {
			fact *= i;
		}
		return fact;
  }

  /*
  private static int[] rowCalc(int[] array, int actualRow, int rowNum) {
    if (actualRow == rowNum) {
      return array;  
    }
    int[] newArray = new int[array.length + 1];
    actualRow++;
    newArray[0] = 1;
    newArray[newArray.length - 1] = 1;
    for (int i = 1; i < actualRow; i++) {
      newArray[i] = array[i - 1] + array[i];
    }
    return rowCalc(newArray, actualRow, rowNum);
  }
  */
  
  // It returns the Nth row from the Pascal Triangle
  //N: index of row, starting with 0
  //K: index of column, starting with 0
  //length of row: N + 1
  //number in Nth row, Kth column: (N, K) -> N! / [ ( N - K )! * K! ]
  public static int[] NthRow(int rowNum) {
    int[] row = new int[rowNum + 1];
    for (int column = 0; column < row.length; column++) {
        row[column] = factorial(rowNum) / ((factorial(rowNum - column) * factorial(column)));
    }
    return row;
    /*
    if (rowNum == 0) {
      return new int[]{1};
    }
    int actualRow = 1;
    int[] array = new int[]{1, 1};
    return rowCalc(array, actualRow, rowNum);
    */
  }
  
}
