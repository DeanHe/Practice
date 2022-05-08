package contest;
/*
ou are given a 2D integer array grid of size m x n, where each cell contains a positive integer.

A cornered path is defined as a set of adjacent cells with at most one turn. More specifically, the path should exclusively move either horizontally or vertically up to the turn (if there is one), without returning to a previously visited cell. After the turn, the path will then move exclusively in the alternate direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited cell.

The product of a path is defined as the product of all the values in the path.

Return the maximum number of trailing zeros in the product of a cornered path found in grid.

Note:

Horizontal movement means moving in either the left or right direction.
Vertical movement means moving in either the up or down direction.


Example 1:


Input: grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
Output: 3
Explanation: The grid on the left shows a valid cornered path.
It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
It can be shown that this is the maximum trailing zeros in the product of a cornered path.

The grid in the middle is not a cornered path as it has more than one turn.
The grid on the right is not a cornered path as it requires a return to a previously visited cell.
Example 2:


Input: grid = [[4,3,2],[7,6,1],[8,8,8]]
Output: 0
Explanation: The grid is shown in the figure above.
There are no cornered paths in the grid that result in a product with a trailing zero.


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= grid[i][j] <= 1000

hint:
1 What actually tells us the trailing zeros of the product of a path?
2 It is the sum of the exponents of 2 and sum of the exponents of 5 of the prime factorizations of the numbers on that path.
The smaller of the two is the answer for that path.
3 We can then treat each cell as the elbow point and calculate the largest minimum (sum of 2 exponents, sum of 5 exponents) from the combination of top-left, top-right, bottom-left and bottom-right.
4 To do this efficiently, we should use the prefix sum technique.
 */

public class MaximumTrailingZerosInaCorneredPath {
    public int maxTrailingZeros(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        int[][] preSum2Row = new int[rows + 1][cols + 1];
        int[][] preSum5Row = new int[rows + 1][cols + 1];
        int[][] preSum2Col = new int[rows + 1][cols + 1];
        int[][] preSum5Col = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cnt2 = count2(grid[r][c]);
                int cnt5 = count5(grid[r][c]);
                preSum2Col[r + 1][c + 1] = preSum2Col[r + 1][c] + cnt2;
                preSum5Col[r + 1][c + 1] = preSum5Col[r + 1][c] + cnt5;
            }
        }
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int cnt2 = count2(grid[r][c]);
                int cnt5 = count5(grid[r][c]);
                preSum2Row[r + 1][c + 1] = preSum2Row[r][c + 1] + cnt2;
                preSum5Row[r + 1][c + 1] = preSum5Row[r][c + 1] + cnt5;
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cnt2up = preSum2Row[r + 1][c + 1] - preSum2Row[0][c + 1];
                int cnt5up = preSum5Row[r + 1][c + 1] - preSum5Row[0][c + 1];
                int cnt2down = preSum2Row[rows][c + 1] - preSum2Row[r][c + 1];
                int cnt5down = preSum5Row[rows][c + 1] - preSum5Row[r][c + 1];
                int cnt2left = preSum2Col[r + 1][c + 1] - preSum2Col[r + 1][0];
                int cnt5left = preSum5Col[r + 1][c + 1] - preSum5Col[r + 1][0];
                int cnt2right = preSum2Col[r + 1][cols] - preSum2Col[r + 1][c];
                int cnt5right = preSum5Col[r + 1][cols] - preSum5Col[r + 1][c];
                res = Math.max(res,
                        Math.min(cnt2up + cnt2left - count2(grid[r][c]), cnt5up + cnt5left - count5(grid[r][c])));
                res = Math.max(res,
                        Math.min(cnt2up + cnt2right - count2(grid[r][c]), cnt5up + cnt5right - count5(grid[r][c])));
                res = Math.max(res,
                        Math.min(cnt2down + cnt2left - count2(grid[r][c]), cnt5down + cnt5left - count5(grid[r][c])));
                res = Math.max(res,
                        Math.min(cnt2down + cnt2right - count2(grid[r][c]), cnt5down + cnt5right - count5(grid[r][c])));
            }
        }
        return res;
    }

    private int count2(int n) {
        int res = 0;
        while (n % 2 == 0) {
            res++;
            n /= 2;
        }
        return res;
    }

    private int count5(int n) {
        int res = 0;
        while (n % 5 == 0) {
            res++;
            n /= 5;
        }
        return res;
    }

}
