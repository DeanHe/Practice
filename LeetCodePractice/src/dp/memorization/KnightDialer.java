package dp.memorization;
/*
	A chess knight can move as indicated in the chess diagram below:
	This time, we place our chess knight on any numbered key of a phone pad (indicated above),
	and the knight makes N-1 hops.
	Each hop must be from one key to another numbered key.
	Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
	How many distinct numbers can you dial in this manner?
	Since the answer may be large, output the answer modulo 10^9 + 7.

	Example 1:

	Input: 1
	Output: 10
	Example 2:

	Input: 2
	Output: 20
	Example 3:

	Input: 3
	Output: 46
	 

	Note:
	1 <= N <= 5000
*/

public class KnightDialer {
    public static final int max = (int) Math.pow(10, 9) + 7;
    long[][][] dp; // dp[k][r][c] means # of distinct number dialed after k hop ended in pad[r][c]

    public int knightDialer(int N) {
        long res = 0;
        dp = new long[N + 1][4][3];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                res = (res + dfs(r, c, N)) % max;
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
        res = (res + dfs(r - 1, c - 2, n - 1)) % max;
        res = (res + dfs(r - 2, c - 1, n - 1)) % max;
        res = (res + dfs(r - 2, c + 1, n - 1)) % max;
        res = (res + dfs(r - 1, c + 2, n - 1)) % max;
        res = (res + dfs(r + 1, c + 2, n - 1)) % max;
        res = (res + dfs(r + 2, c + 1, n - 1)) % max;
        res = (res + dfs(r + 2, c - 1, n - 1)) % max;
        res = (res + dfs(r + 1, c - 2, n - 1)) % max;
        return dp[n][r][c] = res;
    }
}
