package com.example;

public class  PascalTriangle{
  
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

  // It returns the Nth row from the Pascal Triangle
  public static int[] NthRow(int rowNum) {
    if (rowNum == 0) {
      return new int[]{1};
    }
    int actualRow = 1;
    int[] array = new int[]{1, 1};
    return rowCalc(array, actualRow, rowNum);
}
  
}
