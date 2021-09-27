package dp;
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.



Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

hint:
1 The robot can only move either down or right. Hence any cell in the first row can only be reached from the cell left to it. However, if any cell has an obstacle, you don't let that cell contribute to any path. So, for the first row, the number of ways will simply be
if obstacleGrid[i][j] is not an obstacle
     obstacleGrid[i,j] = obstacleGrid[i,j - 1]
else
     obstacleGrid[i,j] = 0
You can do a similar processing for finding out the number of ways of reaching the cells in the first column.

2 For any other cell, we can find out the number of ways of reaching it,
by making use of the number of ways of reaching the cell directly above it and the cell to the left of it in the grid.
This is because these are the only two directions from which the robot can come to the current cell.

3 Since we are making use of pre-computed values along the iteration, this becomes a dynamic programming problem.
if obstacleGrid[i][j] is not an obstacle
     obstacleGrid[i,j] = obstacleGrid[i,j - 1]  + obstacleGrid[i - 1][j]
else
     obstacleGrid[i,j] = 0
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        dp[1][1] = 1;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(obstacleGrid[r][c] == 1){
                    dp[r + 1][c + 1] = 0;
                } else {
                    dp[r + 1][c + 1] += dp[r][c + 1];
                    dp[r + 1][c + 1] += dp[r + 1][c];
                }
            }
        }
        return dp[rows][cols];
    }
}

