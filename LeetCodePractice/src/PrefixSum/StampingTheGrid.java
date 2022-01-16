package PrefixSum;

/*
You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:

Cover all the empty cells.
Do not cover any of the occupied cells.
We can put as many stamps as we want.
Stamps can overlap with each other.
Stamps are not allowed to be rotated.
Stamps must stay completely inside the grid.
Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.

Example 1:
Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
Output: true
Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.

Example 2:
Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
Output: false
Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.


Constraints:
m == grid.length
n == grid[r].length
1 <= m, n <= 10^5
1 <= m * n <= 2 * 10^5
grid[r][c] is either 0 or 1.
1 <= stampHeight, stampWidth <= 10^5

hint:
1 We can check if every empty cell is a part of a consecutive row of empty cells that has a width of at least stampWidth
as well as a consecutive column of empty cells that has a height of at least stampHeight.
2 We can prove that this condition is sufficient and necessary to fit the stamps while following the given restrictions and requirements.
3 For each row, find every consecutive row of empty cells, and mark all the cells where the consecutive row is at least stampWidth wide.
Do the same for the columns with stampHeight. Then, you can check if every cell is marked twice.
 */

public class StampingTheGrid {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int rows = grid.length, cols = grid[0].length;
        int[][] blockPreSum = preSum(grid);
        int[][] stampAllow = new int[rows][cols];
        for (int r = stampHeight - 1; r < rows; r++) {
            for (int c = stampWidth - 1; c < cols; c++) {
                if(sumRange(blockPreSum, r - stampHeight + 1, c - stampWidth + 1, r, c) == 0){
                    stampAllow[r][c] = 1;
                }
            }
        }
        int[][] stampAllowSum = preSum(stampAllow);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0
                        && sumRange(stampAllowSum, r, c, Math.min(rows - 1, r + stampHeight - 1), Math.min(cols - 1, c + stampWidth - 1)) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int sumRange(int[][] preSum, int r0, int c0, int r1, int c1) {
        return preSum[r1 + 1][c1 + 1] - preSum[r0][c1 + 1] - preSum[r1 + 1][c0] + preSum[r0][c0];
    }

    private int[][] preSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] preSum = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                preSum[r + 1][c + 1] = grid[r][c] + preSum[r + 1][c] + preSum[r][c + 1] - preSum[r][c];
            }
        }
        return preSum;
    }
}
