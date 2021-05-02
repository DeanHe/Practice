package contest;

/*
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

A grid is said to be valid if all the cells above the main diagonal are zeros.

Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).



Example 1:


Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3
Example 2:


Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.
Example 3:


Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 200
grid[i][j] is 0 or 1
 */

public class MinimumSwapsToArrangeaBinaryGrid {
    public int minSwaps(int[][] grid) {
        int len = grid.length, res = 0;
        int[] zeros = zerosAtEnd(grid);
        for (int r = 0; r < len; r++) {
            if (zeros[r] < len - r - 1) {
                int i = r;
                while (i < len && zeros[i] < len - r - 1) {
                    i++;
                }
                if (i == len) {  // Did not find any number greater than or equal to len-r-1.
                    return -1;
                }
                while (i > r) {
                    swap(zeros, i, i - 1); // bubble up
                    res++;
                    i--;
                }
            }
        }
        return res;
    }

    private int[] zerosAtEnd(int[][] grid) {
        int len = grid.length;
        int[] res = new int[len];
        for (int r = 0; r < len; r++) {
            int zeros = 0;
            for (int c = len - 1; c >= 0; c--) {
                if (grid[r][c] == 0) {
                    zeros++;
                } else {
                    break;
                }
            }
            res[r] = zeros;
        }
        return res;
    }

    private void swap(int[] g, int i, int j) {
        int temp = g[i];
        g[i] = g[j];
        g[j] = temp;
    }
}
