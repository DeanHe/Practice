"""
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

analysis:
TC: O(N^2)
SC: O(N^2)
"""
from collections import deque
from typing import List


class AsFarFromLandAsPossible:
    def maxDistance(self, grid: List[List[int]]) -> int:
        res = 0
        dirs = [0, 1, 0, -1, 0]
        rows, cols = len(grid), len(grid[0])
        dist = [[0] * cols for _ in range(rows)]
        q = deque()
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    q.append((r, c))
        while q:
            sz = len(q)
            for _ in range(sz):
                r, c = q.popleft()
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols and grid[nb_r][nb_c] == 0 and dist[nb_r][nb_c] == 0:
                        dist[nb_r][nb_c] = dist[r][c] + 1
                        res = max(res, dist[nb_r][nb_c])
                        q.append((nb_r, nb_c))
        return res if res > 0 else -1