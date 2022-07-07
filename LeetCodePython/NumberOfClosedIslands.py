"""
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.



Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2


Constraints:
1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1

hint:
1 Exclude connected group of 0s on the corners because they are not closed island.
2 Return number of connected component of 0s on the grid.
"""
from typing import List


class NumberOfClosedIslands:
    def closedIsland(self, grid: List[List[int]]) -> int:
        dirs = [0, 1, 0, -1, 0]
        res, rows, cols = 0, len(grid), len(grid[0])

        def dfs(r, c, val):
            if grid[r][c] == 0:
                grid[r][c] = val
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols:
                        dfs(nb_r, nb_c, val)

        for r in range(0, rows):
            if grid[r][0] == 0:
                dfs(r, 0, 2)
            if grid[r][cols - 1] == 0:
                dfs(r, cols - 1, 2)

        for c in range(0, cols):
            if grid[0][c] == 0:
                dfs(0, c, 2)
            if grid[rows - 1][c] == 0:
                dfs(rows - 1, c, 2)

        for r in range(0, rows):
            for c in range(0, cols):
                if grid[r][c] == 0:
                    res += 1
                    dfs(r, c, 2)
        return res