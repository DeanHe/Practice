"""
You are given an m x n matrix grid consisting of positive integers. You can move from a cell in the matrix to any other cell that is either to the bottom or to the right (not necessarily adjacent). The score of a move from a cell with the value c1 to a cell with the value c2 is c2 - c1.

You can start at any cell, and you have to make at least one move.

Return the maximum total score you can achieve.

Example 1:
Input: grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
Output: 9
Explanation: We start at the cell (0, 1), and we perform the following moves:
- Move from the cell (0, 1) to (2, 1) with a score of 7 - 5 = 2.
- Move from the cell (2, 1) to (2, 2) with a score of 14 - 7 = 7.
The total score is 2 + 7 = 9.

Example 2:
Input: grid = [[4,3,2],[3,2,1]]
Output: -1
Explanation: We start at the cell (0, 0), and we perform one move: (0, 0) to (0, 1). The score is 3 - 4 = -1.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
1 <= grid[i][j] <= 10^5

hints:
1 Any path from a cell (x1, y1) to another cell (x2, y2) will always have a score of grid[x2][y2] - grid[x1][y1].
2 Let’s say we fix the starting cell (x1, y1), how to the find a cell (x2, y2) such that the value grid[x2][y2] - grid[x1][y1] is the maximum possible?

analysis:
Move from c1 to ck with path c1,c2,..,ck,
res = (c2 - c1) + (c3 - c2) + .... = ck - c1
find the minimum c1 on current top left area, use the 2D DP to track
"""
import math
from typing import List


class MaximumDifferenceScoreInaGrid:
    def maxScore(self, grid: List[List[int]]) -> int:
        res = -math.inf
        rows, cols = len(grid), len(grid[0])
        for r in range(rows):
            for c in range(cols):
                least = min(grid[r - 1][c] if r else math.inf, grid[r][c - 1] if c else math.inf)
                res = max(res, grid[r][c] - least)
                grid[r][c] = min(least, grid[r][c])
        return res

