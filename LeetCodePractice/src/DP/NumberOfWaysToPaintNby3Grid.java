package DP;
/*
        You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green
        while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).
        You are given n the number of rows of the grid.
        Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.

        Example 1:
        Input: n = 1
        Output: 12
        Explanation: There are 12 possible way to paint the grid as shown:

        Example 2:
        Input: n = 2
        Output: 54
        Example 3:

        Input: n = 3
        Output: 246

        Example 4:
        Input: n = 7
        Output: 106494

        Example 5:
        Input: n = 5000
        Output: 30228214


        Constraints:
        n == grid.length
        grid[i].length == 3
        1 <= n <= 5000
*/
public class NumberOfWaysToPaintNby3Grid {
    int MOD = (int) (1e9 + 7);
    int[] colors = {1, 2, 3}; // represent Red, Yellow or Green

    public int numOfWays(int n) {
        //dp[i][a][b][c] means the total # of ways from row 0:i with row i + 1 ending color in a, b, c
        int[][][][] dp = new int[n + 1][4][4][4];
        return dfs(n, 0, 0, 0, dp);
    }

    private int dfs(int n, int a, int b, int c, int[][][][] dp) {
        if (n == 0) {
            return 1;
        }
        if (dp[n][a][b][c] != 0) {
            return dp[n][a][b][c];
        }
        int res = 0;
        for (int c1 : colors) {
            if (c1 == a) {
                continue;
            }
            for (int c2 : colors) {
                if (c2 == b || c2 == c1) {
                    continue;
                }
                for (int c3 : colors) {
                    if (c3 == c || c3 == c2) {
                        continue;
                    }
                    res += dfs(n - 1, c1, c2, c3, dp);
                    res %= MOD;
                }
            }
        }
        return dp[n][a][b][c] = res;
    }
}
