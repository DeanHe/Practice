package dp.memorization;
/*
The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:


We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.



Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3
Output: 46
Example 4:

Input: n = 4
Output: 104
Example 5:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.


Constraints:

1 <= n <= 5000
*/

public class KnightDialer {
    int MOD = (int)(1e9 + 7);
    long[][][] dp; // dp[k][r][c] means # of distinct number dialed after k hop ended in pad[r][c]

    public int knightDialer(int N) {
        long res = 0;
        dp = new long[N + 1][4][3];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                res = (res + dfs(r, c, N)) % MOD;
            }
        }
        return (int) res;
    }

    // topDown with memorization
    private long dfs(int r, int c, int n) {
        if (r < 0 || r > 3 || c < 0 || c > 2 || (r == 3 && c != 1)) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (dp[n][r][c] > 0) {
            return dp[n][r][c];
        }
        long res = 0;
        res = (res + dfs(r - 1, c - 2, n - 1)) % MOD;
        res = (res + dfs(r - 2, c - 1, n - 1)) % MOD;
        res = (res + dfs(r - 2, c + 1, n - 1)) % MOD;
        res = (res + dfs(r - 1, c + 2, n - 1)) % MOD;
        res = (res + dfs(r + 1, c + 2, n - 1)) % MOD;
        res = (res + dfs(r + 2, c + 1, n - 1)) % MOD;
        res = (res + dfs(r + 2, c - 1, n - 1)) % MOD;
        res = (res + dfs(r + 1, c - 2, n - 1)) % MOD;
        return dp[n][r][c] = res;
    }
}
