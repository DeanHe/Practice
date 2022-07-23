"""
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6

Example 2:
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12

Constraints:
1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n

hint:
1 Is traversing every path feasible? There are many possible paths for a small matrix. Try to optimize it.
2 Can we use some space to store the number of paths and update them after every move?
"""

class OutOfBoundaryPaths:

    def findPaths(self, m: int, n: int, maxMove: int, startRow: int, startColumn: int) -> int:
        MOD = 10 ** 9 + 7
        dirs = [0, 1, 0, -1, 0]
        res = 0
        dp = [[0] * n for _ in range(m)]
        dp[startRow][startColumn] = 1
        for step in range(maxMove):
            tmp = [[0] * n for _ in range(m)]
            for r in range(m):
                for c in range(n):
                    for i in range(len(dirs) - 1):
                        nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                        if nb_r < 0 or nb_r >= m or nb_c < 0 or nb_c >= n:
                            res = (res + dp[r][c]) % MOD
                        else:
                            tmp[nb_r][nb_c] = (tmp[nb_r][nb_c] + dp[r][c]) % MOD
            dp = tmp
        return res