"""
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

hint:
1 Do a breadth first search to find the shortest path.
"""
import collections
from typing import List


class ShortestPathInBinaryMatrix:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dirs = [(0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
        if grid[0][0] == 1 or grid[rows - 1][cols - 1] == 1:
            return -1
        step = 1
        visited = [[False] * cols for _ in range(rows)]
        q = collections.deque()
        q.append((0, 0))
        visited[0][0] = True
        while q:
            sz = len(q)
            for _ in range(sz):
                r, c = q.popleft()
                if r == rows - 1 and c == cols - 1:
                    return step
                for dr, dc in dirs:
                    nb_r, nb_c = r + dr, c + dc
                    if 0 <= nb_r < rows and 0 <= nb_c < cols:
                        if grid[nb_r][nb_c] == 0 and not visited[nb_r][nb_c]:
                            q.append((nb_r, nb_c));
                            visited[nb_r][nb_c] = True
            step += 1
        return -1
