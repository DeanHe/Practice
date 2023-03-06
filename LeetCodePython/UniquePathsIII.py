"""
You are given an m x n integer array grid where grid[i][j] could be:

1 representing the starting square. There is exactly one starting square.
2 representing the ending square. There is exactly one ending square.
0 representing empty squares we can walk over.
-1 representing obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Example 1:
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

Example 2:
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

Example 3:
Input: grid = [[0,1],[2,0]]
Output: 0
Explanation: There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 20
1 <= m * n <= 20
-1 <= grid[i][j] <= 2
There is exactly one starting cell and one ending cell.
"""
from typing import List


class UniquePathsIII:
    def uniquePathsIII(self, grid: List[List[int]]) -> int:
        self.res, self.empty = 0, 1
        dirs = [0, 1, 0, -1, 0]
        rows, cols, start = len(grid), len(grid[0]), (0, 0)
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 0:
                    self.empty += 1
                elif grid[r][c] == 1:
                    start = (r, c)

        def dfs(r, c):
            if grid[r][c] == 2:
                if self.empty == 0:
                    self.res += 1
                return
            self.empty -= 1
            grid[r][c] = -2
            for i in range(len(dirs) - 1):
                nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                if 0 <= nb_r < rows and 0 <= nb_c < cols and (grid[nb_r][nb_c] == 0 or grid[nb_r][nb_c] == 2):
                    dfs(nb_r, nb_c)
            grid[r][c] = 0
            self.empty += 1

        dfs(start[0], start[1])
        return self.res

