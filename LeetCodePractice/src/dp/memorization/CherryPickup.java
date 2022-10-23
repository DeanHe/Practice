package dp.memorization;

/*
In a N x N grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.

Your task is to collect maximum number of cherries possible by following the rules below:

Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.

Example 1:
Input: grid =
[[0, 1, -1],
[1, 0, -1],
[1, 1,  1]]
Output: 5
Explanation:
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.


Note:
grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.

analysis:
Let dp[r1][c1][c2] be the most number of cherries obtained by two people starting at (r1, c1) and (r2, c2)
and walking towards (N-1, N-1) picking up cherries, where r2 = r1+c1-c2.
*/

public class CherryPickup {
    int N;
    int[][][] dp;
    int[][] grid;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        dp = new int[N][N][N];
        return Math.max(0, helper(0, 0, 0));
    }

    private int helper(int r1, int c1, int c2) {
        int r2 = c1 + r1 - c2;
        if (c1 == N || r1 == N || c2 == N || r2 == N || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        if (dp[r1][c1][c2] != 0) {
            return dp[r1][c1][c2];
        }
        if (r1 == N - 1 && c1 == N - 1) {
            return grid[r1][c1];
        }
        int res = grid[r1][c1];
        if (r1 != r2 && c1 != c2) {
            res += grid[r2][c2];
        }
        //both move right; both move down; one move down one move right
        int temp = Math.max(helper(r1, c1 + 1, c2 + 1), helper(r1 + 1, c1, c2));
        temp = Math.max(temp, helper(r1, c1 + 1, c2));
        temp = Math.max(temp, helper(r1 + 1, c1, c2 + 1));
        res += temp;
        return dp[r1][c1][c2] = res;
    }
}
