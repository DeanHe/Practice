"""
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
"""
from typing import List


class NumberOfIslands:

    def numIslands(self, grid: List[List[str]]) -> int:
        self.dirs = [(0, 1), (0, -1), (-1, 0), (1, 0)]
        if not grid:
            return 0
        res = 0
        for r in range(0, len(grid)):
            for c in range(0, len(grid[0])):
                if grid[r][c] == '1':
                    self.dfs(r, c, grid)
                    res += 1
        return res

    def dfs(self, r: int, c: int, grid: List[List[str]]) -> None:
        grid[r][c] = '0'
        for dr, dc in self.dirs:
            if 0 <= r + dr < len(grid) and 0 <= c + dc < len(grid[0]) and grid[r + dr][c + dc] == '1':
                self.dfs(r + dr, c + dc, grid)
