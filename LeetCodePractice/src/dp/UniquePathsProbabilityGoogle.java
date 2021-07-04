package dp;
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

show all cells' visit probability, consider all paths are equally weighted



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
Example 3:

Input: m = 7, n = 3
Output: 28
Example 4:

Input: m = 3, n = 3
Output: 6


Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10^9.
 */
public class UniquePathsProbabilityGoogle {
    int rows, cols;
    double[][] dp;
    public double[][] uniquePathsProbability(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        dp = new double[rows][cols];
        dfs(0, 0, 1.0);
        return dp;
    }

    private void dfs(int r, int c, double prob) {
        if(r == rows || c == cols){
            return;
        }
        dp[r][c] += prob;
        if(r == rows - 1){
            dfs(r, c + 1, prob);
            return;
        }
        if(c == cols - 1){
            dfs(r + 1, c, prob);
            return;
        }
        dfs(r, c + 1, prob * 0.5);
        dfs(r + 1, c, prob * 0.5);
    }
}

