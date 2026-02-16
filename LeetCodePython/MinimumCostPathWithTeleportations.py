"""
You are given a m x n 2D integer array grid and an integer k. You start at the top-left cell (0, 0) and your goal is to reach the bottom‐right cell (m - 1, n - 1).

There are two types of moves available:

Normal move: You can move right or down from your current cell (i, j), i.e. you can move to (i, j + 1) (right) or (i + 1, j) (down). The cost is the value of the destination cell.

Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that grid[x][y] <= grid[i][j]; the cost of this move is 0. You may teleport at most k times.

Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).

Example 1:
Input: grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2
Output: 7

Explanation:
Initially we are at (0, 0) and cost is 0.

Current Position	Move	New Position	Total Cost
(0, 0)	Move Down	(1, 0)	0 + 2 = 2
(1, 0)	Move Right	(1, 1)	2 + 5 = 7
(1, 1)	Teleport to (2, 2)	(2, 2)	7 + 0 = 7
The minimum cost to reach bottom-right cell is 7.

Example 2:
Input: grid = [[1,2],[2,3],[3,4]], k = 1
Output: 9

Explanation:
Initially we are at (0, 0) and cost is 0.

Current Position	Move	New Position	Total Cost
(0, 0)	Move Down	(1, 0)	0 + 2 = 2
(1, 0)	Move Right	(1, 1)	2 + 3 = 5
(1, 1)	Move Down	(2, 1)	5 + 4 = 9
The minimum cost to reach bottom-right cell is 9.

Constraints:
2 <= m, n <= 80
m == grid.length
n == grid[i].length
0 <= grid[i][j] <= 10^4
0 <= k <= 10

hint:
1 Use dynamic programming to solve the problem efficiently.
2 Think of the solution in terms of up to k teleportation steps. At each step, compute the minimum cost to reach each cell, either through a normal move or a teleportation from the previous step.

analysis:
TC: O(rows * cols * k)
"""
from typing import List


class MinimumCostPathWithTeleportations:
    def minCost(self, grid: List[List[int]], k: int) -> int:
        rows = len(grid)
        cols = len(grid[0])
        most = 10 ** 18
        grid_max = 0
        dp = [[[most] * (k + 1) for _ in range(cols)] for _ in range(rows)]
        # init
        dp[0][0][0] = 0
        for r in range(rows):
            for c in range(cols):
                if r > 0:
                    dp[r][c][0] = min(dp[r][c][0], dp[r - 1][c][0] + grid[r][c])
                if c > 0:
                    dp[r][c][0] = min(dp[r][c][0], dp[r][c - 1][0] + grid[r][c])
                grid_max = max(grid_max, grid[r][c])
        # translation
        for kk in range(1, k + 1):
            min_cost_from_value = [most] * (grid_max + 1)
            for r in range(rows):
                for c in range(cols):
                    val = grid[r][c]
                    min_cost_from_value[val] = min(min_cost_from_value[val], dp[r][c][kk - 1])
            # this is from kk - 1 iteration
            min_cost_from_ge_value = [most] * (grid_max + 1)
            min_cost_from_ge_value[grid_max] = min_cost_from_value[grid_max]
            for val in range(grid_max - 1, -1, -1):
                min_cost_from_ge_value[val] = min(min_cost_from_ge_value[val + 1], min_cost_from_value[val])
            for r in range(rows):
                for c in range(cols):
                    if r > 0:
                        dp[r][c][kk] = min(dp[r][c][kk], dp[r - 1][c][kk] + grid[r][c])
                    if c > 0:
                        dp[r][c][kk] = min(dp[r][c][kk], dp[r][c - 1][kk] + grid[r][c])
                    val = grid[r][c]
                    dp[r][c][kk] = min(dp[r][c][kk], min_cost_from_ge_value[val])
        return dp[rows - 1][cols - 1][k]
