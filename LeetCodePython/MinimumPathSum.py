"""
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100
"""
from typing import List


class MinimumPathSum:
    def minPathSum(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dp = [[0] * cols for _ in range(rows)]
        dp[0][0] = grid[0][0]
        for r in range(1, rows):
            dp[r][0] = dp[r - 1][0] + grid[r][0]
        for c in range(1, cols):
            dp[0][c] = dp[0][c - 1] + grid[0][c]
        for r in range(1, rows):
            for c in range(1, cols):
                dp[r][c] = float('inf')
                dp[r][c] = min(dp[r][c], dp[r][c - 1] + grid[r][c], dp[r - 1][c] + grid[r][c])
        return dp[-1][-1]
