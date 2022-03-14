"""
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.



Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
"""
import collections
from typing import List


class ShortestBridge:
    def shortestBridge(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dirs = [(0, 1), (0, -1), (-1, 0), (1, 0)]
        q = collections.deque()
        visited = [[False] * cols for _ in range(rows)]

        def findFirstIsland() -> None:
            def dfs(x, y):
                if 0 <= x < rows and 0 <= y < cols and not visited[x][y] and grid[x][y] == 1:
                    q.append((x, y))
                    visited[x][y] = True
                    for d in dirs:
                        dfs(x + d[0], y + d[1])

            for r in range(rows):
                for c in range(cols):
                    if grid[r][c] == 1:
                        dfs(r, c)
                        return

        def bfs() -> int:
            step = 0
            while q:
                sz = len(q)
                for i in range(sz):
                    r, c = q.popleft()
                    if grid[r][c] == 1 and step > 0:
                        return step - 1
                    for d in dirs:
                        nb_r, nb_c = r + d[0], c + d[1]
                        if 0 <= nb_r < rows and 0 <= nb_c < cols and not visited[nb_r][nb_c]:
                            q.append((nb_r, nb_c))
                            visited[nb_r][nb_c] = True
                step += 1

        findFirstIsland()
        return bfs()
