package Contest;

/*
You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.

Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.

Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.

Notice that the modulo is performed after getting the maximum product.



Example 1:

Input: grid = [[-1,-2,-3],
               [-2,-3,-3],
               [-3,-3,-2]]
Output: -1
Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
Example 2:

Input: grid = [[1,-2,1],
               [1,-2,1],
               [3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
Example 3:

Input: grid = [[1, 3],
               [0,-4]]
Output: 0
Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
Example 4:

Input: grid = [[ 1, 4,4,0],
               [-2, 0,0,1],
               [ 1,-1,1,1]]
Output: 2
Explanation: Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 * 1 = 2).


Constraints:

1 <= rows, cols <= 15
-4 <= grid[i][j] <= 4
 */
public class MaximumNonNegativeProductInaMatrix {
    int MOD = (int) (1e9 + 7);

    public int maxProductPath(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        long[][] neg = new long[rows][cols];
        long[][] pos = new long[rows][cols];
        pos[0][0] = grid[0][0];
        neg[0][0] = grid[0][0];
        for (int r = 1; r < rows; r++) {
            pos[r][0] = pos[r - 1][0] * grid[r][0];
            neg[r][0] = neg[r - 1][0] * grid[r][0];
        }
        for (int c = 1; c < cols; c++) {
            pos[0][c] = pos[0][c - 1] * grid[0][c];
            neg[0][c] = neg[0][c - 1] * grid[0][c];
        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (grid[r][c] >= 0) {
                    pos[r][c] = Math.max(pos[r - 1][c], pos[r][c - 1]) * grid[r][c];
                    neg[r][c] = Math.min(neg[r - 1][c], neg[r][c - 1]) * grid[r][c];
                } else {
                    pos[r][c] = Math.min(neg[r - 1][c], neg[r][c - 1]) * grid[r][c];
                    neg[r][c] = Math.max(pos[r][c - 1], pos[r][c - 1]) * grid[r][c];
                }
            }
        }
        int res = (int)(pos[rows - 1][cols - 1] % MOD);
        return res < 0 ?  -1 : res;
    }
}
