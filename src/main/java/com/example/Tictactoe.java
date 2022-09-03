package com.example;

public class Tictactoe {

	public static char getWinner(char[][] board) {
		validateBoard(board);

		int checkRow1 = 0;
		int checkRow2 = 0;
		int checkRow3 = 0;
		int checkColumn1 = 0;
		int checkColumn2 = 0;
		int checkColumn3 = 0;
		int checkDiagonal1 = 0;
		int checkDiagonal2 = 0;
		int rowIndex;
		int columnIndex;

		//checking rows
		rowIndex = 0;
		while (rowIndex < 3) {
			columnIndex = 0;
			while (columnIndex < 3) {
				switch (rowIndex) {
					case 0 -> checkRow1 = increaseCheck(board[rowIndex][columnIndex], checkRow1);
					case 1 -> checkRow2 = increaseCheck(board[rowIndex][columnIndex], checkRow2);
					case 2 -> checkRow3 = increaseCheck(board[rowIndex][columnIndex], checkRow3);
				}
				columnIndex++;
			}
			rowIndex++;
		}

		//checking columns
		columnIndex = 0;
		while (columnIndex < 3) {
			rowIndex = 0;
			while (rowIndex < 3) {
				switch (columnIndex) {
					case 0 -> checkColumn1 = increaseCheck(board[rowIndex][columnIndex], checkColumn1);
					case 1 -> checkColumn2 = increaseCheck(board[rowIndex][columnIndex], checkColumn2);
					case 2 -> checkColumn3 = increaseCheck(board[rowIndex][columnIndex], checkColumn3);
				}
				rowIndex++;
			}
			columnIndex++;
		}

		//checking diagonals
		columnIndex = 0;
		while (columnIndex < 3) {
			checkDiagonal1 = increaseCheck(board[columnIndex][columnIndex], checkDiagonal1);
			checkDiagonal2 = increaseCheck(board[2 - columnIndex][columnIndex], checkDiagonal2);
			columnIndex++;
		}

		//results
		int flag = checkToFlag(checkRow1) * checkToFlag(checkRow2) * checkToFlag(checkRow3) *
				checkToFlag(checkColumn1) * checkToFlag(checkColumn2) * checkToFlag(checkColumn3) *
				checkToFlag(checkDiagonal1) * checkToFlag(checkDiagonal2);
		return switch (flag) {
			case 1 -> '-';
			case 2 -> 'X';
			case 3 -> 'O';
			default -> throw new IllegalArgumentException();
		};
	}

	private static int checkToFlag(int check) {
		return switch (check) {
			case 3 -> 2; //winner is X
			case 30 -> 3; //winner is O
			default -> 1; //no winner
		};
	}

	private static int increaseCheck(char cell, int check) {
		return switch (cell) {
			case 'X' -> check + 1;
			case 'O' -> check + 10;
			default -> check;
		};
	}

	private static void validateBoard(char[][] board) {
		if (board.length == 3) {
			for (char[] row : board) {
				if (row.length != 3) {
					throw new IllegalArgumentException();
				}
				for (char cell : row) {
					if (!((cell == 'X') || (cell == 'O') || (cell == '-'))) {
						throw new IllegalArgumentException();
					}
				}
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
