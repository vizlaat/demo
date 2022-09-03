package com.example;

import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;

import java.security.*;

public class TictactoeTest {

	@Test
	public void testIllegalArgument_EmptyArray() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(new char[0][0]));
	}

	@Test
	public void testIllegalArgument_HalfEmptyArray1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(new char[3][0]));
	}

	@Test
	public void testIllegalArgument_HalfEmptyArray2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(new char[0][3]));
	}

	@Test
	public void testWinning() {
		char[][] board = new char[][]{
				{'X', 'X', 'X'},
				{'X', 'O', 'X'},
				{'O', 'X', 'O'}
		};
		Assertions.assertEquals('X', Tictactoe.getWinner(board));
	}

	@Test
	public void testEmptyGame() {
		char[][] board = new char[][]{
				{'-', '-', '-'},
				{'-', '-', '-'},
				{'-', '-', '-'}
		};
		Assertions.assertEquals('-', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningRow1() {
		char[][] board = new char[][]{
				{'X', 'X', 'X'},
				{'-', 'O', 'O'},
				{'-', '-', 'X'}
		};
		Assertions.assertEquals('X', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningRow2() {
		char[][] board = new char[][]{
				{'X', 'X', 'O'},
				{'O', '-', '-'},
				{'O', 'O', 'O'}
		};
		Assertions.assertEquals('O', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningColumn1() {
		char[][] board = new char[][]{
				{'X', '-', 'O'},
				{'X', '-', 'X'},
				{'X', 'O', 'O'}
		};
		Assertions.assertEquals('X', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningColumn2() {
		char[][] board = new char[][]{
				{'O', 'X', 'O'},
				{'X', '-', 'O'},
				{'-', '-', 'O'}
		};
		Assertions.assertEquals('O', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningDiagonal1() {
		char[][] board = new char[][]{
				{'X', 'X', 'O'},
				{'-', 'X', 'O'},
				{'-', '-', 'X'}
		};
		Assertions.assertEquals('X', Tictactoe.getWinner(board));
	}

	@Test
	public void testWinningDiagonal2() {
		char[][] board = new char[][]{
				{'X', 'X', 'O'},
				{'-', 'O', 'O'},
				{'O', '-', 'X'}
		};
		Assertions.assertEquals('O', Tictactoe.getWinner(board));
	}

	@Test
	public void testIllegalCharacter() {
		char[][] board = new char[][]{
				{'X', '-', '-'},
				{'-', 'O', '-'},
				{'A', '-', '-'}
		};
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(board));
	}

	@Test
	public void testDuplicatedWinning1() {
		char[][] board = new char[][]{
				{'X', 'X', 'X'},
				{'-', 'O', '-'},
				{'O', 'O', 'O'}
		};
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(board));
	}

	@Test
	public void testDuplicatedWinning2() {
		char[][] board = new char[][]{
				{'X', 'X', 'X'},
				{'-', 'X', '-'},
				{'X', 'O', 'O'}
		};
		Assertions.assertThrows(IllegalArgumentException.class, () -> Tictactoe.getWinner(board));
	}
}
