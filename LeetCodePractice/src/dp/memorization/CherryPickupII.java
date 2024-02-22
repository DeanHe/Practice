package dp.memorization;

/*
Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
Return the maximum number of cherries collection using both robots  by following the rules below:

From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
When both robots stay on the same cell, only one of them takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in the grid.

Example 1:
Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.

Example 2:
Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

Example 3:
Input: grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
Output: 22

Example 4:
Input: grid = [[1,1],[1,1]]
Output: 4

Constraints:
rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100

hint:
1 Use dynamic programming, define DP[i][j][k]: The maximum cherries that both robots can take starting on the ith row, and column j and k of Robot 1 and 2 respectively.
*/

public class CherryPickupII {
    public int cherryPickup(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Integer[][][] dp = new Integer[rows][cols][cols];
        return dfs(grid, dp, 0, 0, cols - 1);
    }

    private int dfs(int[][] grid, Integer[][][] dp, int r, int c1, int c2) {
        int rows = grid.length, cols = grid[0].length;
        if (c1 < 0 || c1 >= cols || c2 < 0 || c2 >= cols || r >= rows) {
            return 0;
        }
        if (dp[r][c1][c2] != null) {
            return dp[r][c1][c2];
        }
        int res = 0;
        if (c1 == c2) {
            res = grid[r][c1];
        } else {
            res = grid[r][c1] + grid[r][c2];
        }
        int lowLvl = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                lowLvl = Math.max(lowLvl, dfs(grid, dp, r + 1, c1 + i, c2 + j));
            }
        }
        return dp[r][c1][c2] = res + lowLvl;
    }
}
