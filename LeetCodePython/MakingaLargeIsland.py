"""
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
"""
import collections
from typing import List


class MakingaLargeIsland:

    def largestIsland(self, grid: List[List[int]]) -> int:
        self.rows, self.cols = len(grid), len(grid[0])
        self.dirs = [(0, 1), (0, -1), (-1, 0), (1, 0)]
        color, res, memo = 1, 0, {0: 0}
        for r in range(0, self.rows):
            for c in range(0, self.cols):
                if grid[r][c] == 1:
                    color += 1
                    memo[color] = self.getArea(r, c, color, grid)
                    res = max(res, memo[color])

        for r in range(0, self.rows):
            for c in range(0, self.cols):
                if grid[r][c] == 0:
                    total = 1
                    nb_colors = set()
                    for dir in self.dirs:
                        nb_r, nb_c = r + dir[0], c + dir[1]
                        if 0 <= nb_r < self.rows and 0 <= nb_c < self.cols:
                            nb_colors.add(grid[nb_r][nb_c])
                    for nb_color in nb_colors:
                        total += memo[nb_color]
                    res = max(res, total)
        return res

    def getArea(self, r, c, color, grid):
        res = 1
        grid[r][c] = color
        for dir in self.dirs:
            nb_r, nb_c = r + dir[0], c + dir[1]
            if 0 <= nb_r < self.rows and 0 <= nb_c < self.cols and grid[nb_r][nb_c] == 1:
                res += self.getArea(nb_r, nb_c, color, grid)
        return res
