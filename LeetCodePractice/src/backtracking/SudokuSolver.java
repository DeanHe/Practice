package backtracking;

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
*/
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
