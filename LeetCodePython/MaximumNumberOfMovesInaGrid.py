"""
You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

Example 1:
Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.

Example 2:
Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.


Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
1 <= grid[i][j] <= 10^6

hints:
1 Consider using dynamic programming to find the maximum number of moves that can be made from each cell.
2 The final answer will be the maximum value in cells of the first column.
"""
from typing import List


class MaximumNumberOfMovesInaGrid:
    def maxMoves(self, grid: List[List[int]]) -> int:
        res = 0
        rows, cols = len(grid), len(grid[0])
        dp = [[0] * cols for _ in range(rows)]
        for c in range(cols - 2, -1, -1):
            for r in range(rows):
                if r - 1 >= 0:
                    if grid[r][c] < grid[r - 1][c + 1]:
                        dp[r][c] = max(dp[r][c], dp[r - 1][c + 1] + 1)
                if grid[r][c] < grid[r][c + 1]:
                    dp[r][c] = max(dp[r][c], dp[r][c + 1] + 1)
                if r + 1 < rows:
                    if grid[r][c] < grid[r + 1][c + 1]:
                        dp[r][c] = max(dp[r][c], dp[r + 1][c + 1] + 1)
                if c == 0:
                    res = max(res, dp[r][c])
        return res