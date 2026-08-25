"""
You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.

Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.

Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.

Notice that the modulo is performed after getting the maximum product.

Example 1:
Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
Output: -1
Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.

Example 2:
Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).

Example 3:
Input: grid = [[1,3],[0,-4]]
Output: 0
Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 15
-4 <= grid[i][j] <= 4

hints:
1 Use Dynamic programming. Keep the highest value and lowest value you can achieve up to a point.

analysis:
TC:O(rows * cols)
"""
from typing import List


class MaximumNonNegativeProductInaMatrix:
    def maxProductPath(self, grid: List[List[int]]) -> int:
        MOD = 10 ** 9 + 7
        rows = len(grid)
        cols = len(grid[0])
        max_dp = [[0] * cols for _ in range(rows)]
        min_dp = [[0] * cols for _ in range(rows)]
        # init
        min_dp[0][0] = max_dp[0][0] = grid[0][0]
        for r in range(1, rows):
            max_dp[r][0] = min_dp[r][0] = max_dp[r - 1][0] * grid[r][0]
        for c in range(1, cols):
            max_dp[0][c] = min_dp[0][c] = max_dp[0][c - 1] * grid[0][c]
        # transfer
        for r in range(1, rows):
            for c in range(1, cols):
                if grid[r][c] >= 0:
                    max_dp[r][c] = max(max_dp[r - 1][c],  max_dp[r][c - 1]) * grid[r][c]
                    min_dp[r][c] = min(min_dp[r - 1][c],  min_dp[r][c - 1]) * grid[r][c]
                else:
                    max_dp[r][c] = min(min_dp[r - 1][c],  min_dp[r][c - 1]) * grid[r][c]
                    min_dp[r][c] = max(max_dp[r - 1][c],  max_dp[r][c - 1]) * grid[r][c]
        if max_dp[rows - 1][cols - 1] < 0:
            return -1
        return max_dp[rows - 1][cols - 1] % MOD
