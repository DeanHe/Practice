package Backtracking;
/*Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the number 0.

You may assume that there will be only one unique solution.*/
public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		helper(board, 0, 0);
	}

	public boolean helper(char[][] board, int i, int j) {
		if (j == board[0].length) {
			return helper(board, i + 1, 0);
		}
		if (i == board.length) {
			return true;
		}
		if (board[i][j] == '.') {
			for (int k = 1; k <= 9; k++) {
				board[i][j] = (char) ('0' + k);
				if (isValid(board, i, j)) {
					if (helper(board, i, j + 1)) {
						return true;
					}
				}
				board[i][j] = '.';
			}
			return false;
		} else {
			return helper(board, i, j + 1);
		}
	}

	public boolean isValid(char[][] board, int i, int j) {
		// check if col contains duplicates
		for (int k = 0; k < board.length; k++) {
			if (board[i][j] == board[k][j] && k != i) {
				return false;
			}
		}
		// check if row contains duplicates
		for (int k = 0; k < board[0].length; k++) {
			if (board[i][k] == board[i][j] && k != j) {
				return false;
			}
		}
		// check the 3*3 matrix contains duplicates
		int startRow = 3 * (i / 3);
		int startCol = 3 * (j / 3);
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				if (board[startRow + m][startCol + n] == board[i][j]
						&& (startRow + m != i || startCol + n != j)) {
					return false;
				}
			}
		}
		return true;
	}
}
