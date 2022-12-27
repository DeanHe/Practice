"""
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

Example 1:
Input: n = 3
Output: 5
Explanation: The five different ways are show above.

Example 2:
Input: n = 1
Output: 1

Constraints:
1 <= n <= 1000

analysis:
dp[c][s] means the # of ways to fill board 2 * c as shape s
"""

class DominoAndTrominoTiling:
    def numTilings(self, n: int) -> int:
        MOD = 10 ** 9 + 7
        dp = [[0] * 3 for _ in range(n + 1)]
        dp[0][0] = dp[1][0] = 1
        for c in range(2, n + 1):
            dp[c][0] = (dp[c - 1][0] + dp[c - 2][0] + dp[c - 1][1] + dp[c - 1][2]) % MOD
            dp[c][1] = (dp[c - 2][0] + dp[c - 1][2]) % MOD
            dp[c][2] = (dp[c - 2][0] + dp[c - 1][1]) % MOD
        return dp[n][0]
