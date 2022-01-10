package dp;
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for(int r = 1; r < rows; r++){
            dp[r][0] = dp[r - 1][0] + grid[r][0];
        }
        for(int c = 1; c < cols; c++){
            dp[0][c] = dp[0][c - 1] + grid[0][c];
        }
        for(int r = 1; r < rows; r++){
            for(int c = 1; c < cols; c++){
                dp[r][c] = Integer.MAX_VALUE;
                dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + grid[r][c]);
                dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + grid[r][c]);
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
