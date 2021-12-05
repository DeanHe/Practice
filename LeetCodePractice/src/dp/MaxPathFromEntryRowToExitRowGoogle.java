package dp;

/*
Given a matrix, we can consider any point on the first row as entry point and any point on last row as exit point. # is a wall and . is empty space.
Find the path with max length. You can only go down, right or left and visit each cell only once.

the interviewer wanted a DP solution.

eg matrix:

"#.#..#",
"#.#..#",
"#.##.#",
"#..#.#",
"#..#.#",
"#..#.#"

analysis:
https://leetcode.com/discuss/interview-question/1150720/google-onsite-max-path-from-entry-row-to-exit-row
dp[r][c] means max path length from first row and ended in cell[r][c]

TC O(rows * (2 * cols)) -> O(N^2)
 */
public class MaxPathFromEntryRowToExitRowGoogle {
    public int maxPath(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length, res = 0;
        int[][] dp = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            int[] leftSum = new int[cols];
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '.') {
                    if(r == 0){
                        leftSum[c] = 1;
                    } else {
                        leftSum[c] = dp[r - 1][c] + 1;
                    }
                    if (c > 0) {
                        leftSum[c] = Math.max(leftSum[c], leftSum[c - 1] + 1);
                    }
                }
            }
            int[] rightSum = new int[cols + 1];
            for (int c = cols - 1; c >= 0; c--) {
                if (matrix[r][c] == '.') {
                    if(r == 0){
                        rightSum[c] = 1;
                    } else {
                        rightSum[c] = dp[r - 1][c] + 1;
                    }
                    if (c + 1 < cols) {
                        rightSum[c] = Math.max(rightSum[c], rightSum[c + 1] + 1);
                    }
                    dp[r][c] = Math.max(leftSum[c], rightSum[c]);
                }
            }
        }
        for (int c = 0; c < cols; c++) {
            res = Math.max(res, dp[rows - 1][c]);
        }
        return res;
    }
}
