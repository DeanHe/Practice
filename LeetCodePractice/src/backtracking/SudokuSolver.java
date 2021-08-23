package backtracking;

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

    public boolean helper(char[][] board, int r, int c) {
        int rows = board.length, cols = board[0].length;
        if (r == rows) {
            return true;
        }
        if (c == cols) {
            return helper(board, r + 1, 0);
        }
        if (board[r][c] == '.') {
            for (int k = 1; k <= 9; k++) {
                board[r][c] = (char) ('0' + k);
                if (isValid(board, r, c)) {
                    if (helper(board, r, c + 1)) {
                        return true;
                    }
                }
                board[r][c] = '.';
            }
        } else {
            return helper(board, r, c + 1);
        }
        return false;
    }

    public boolean isValid(char[][] board, int r, int c) {
        int rows = board.length, cols = board[0].length;
        // check if col contains duplicates
        for (int k = 0; k < rows; k++) {
            if (board[r][c] == board[k][c] && k != r) {
                return false;
            }
        }
        // check if row contains duplicates
        for (int k = 0; k < cols; k++) {
            if (board[r][k] == board[r][c] && k != c) {
                return false;
            }
        }
        // check the 3*3 matrix contains duplicates
        int startRow = r - r % 3;
        int startCol = c - c % 3;
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                if (board[startRow + m][startCol + n] == board[r][c]
                        && (startRow + m != r || startCol + n != c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
