package dfs;

/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:
Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Example 2:
Input: n=1
Output: 1

Constraints:
1 <= n <= 9

Follow up for N-Queens problem.
Now, instead outputting board configurations, return the total number of distinct solutions.
*/
public class NQueensII {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     *
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        //write your code here
        if (n < 1) {
            return 0;
        }
        int[] board = new int[n];
        return dfs(board, 0, n);

    }

    private int dfs(int[] board, int r, int n) {
        if (r == n) {
            return 1;
        }
        int res = 0;
        for (int c = 0; c < n; c++) {
            if (isValid(board, r, c)) {
                board[r] = c;
                res += dfs(board, r + 1, n);
                board[r] = 0;
            }
        }
        return res;
    }

    boolean isValid(int[] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r] == col) {
                return false;
            }
            if (Math.abs(r - row) == Math.abs(board[r] - col)) {
                return false;
            }
        }
        return true;
    }
}
