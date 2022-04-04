package dp;

/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:

Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] mat) {
        int res = 0;
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] horizontal = new int[rows + 1][cols + 1];
        int[][] vertical = new int[rows + 1][cols + 1];
        int[][] diagonal = new int[rows + 1][cols + 1];
        int[][] antiDiagonal = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1) {
                    horizontal[r + 1][c + 1] = horizontal[r + 1][c] + 1;
                    vertical[r + 1][c + 1] = vertical[r][c + 1] + 1;
                    diagonal[r + 1][c + 1] = diagonal[r][c] + 1;
                    if (c + 2 <= cols) {
                        antiDiagonal[r + 1][c + 1] = antiDiagonal[r][c + 2] + 1;
                    } else {
                        antiDiagonal[r + 1][c + 1] = 1;
                    }
                    res = Math.max(res, horizontal[r + 1][c + 1]);
                    res = Math.max(res, vertical[r + 1][c + 1]);
                    res = Math.max(res, diagonal[r + 1][c + 1]);
                    res = Math.max(res, antiDiagonal[r + 1][c + 1]);
                }
            }
        }
        return res;
    }
}
