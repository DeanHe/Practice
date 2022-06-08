"""
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
"""
import collections
from typing import List


class RottingOranges:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        fresh, days = 0, 0
        dirs = [0, 1, 0, -1, 0]
        q = collections.deque()
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    fresh += 1
                elif grid[r][c] == 2:
                    q.append((r, c))
        while q:
            sz = len(q)
            for _ in range(sz):
                r, c = q.popleft()
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols and grid[nb_r][nb_c] == 1:
                        fresh -= 1
                        grid[nb_r][nb_c] = 2
                        q.append((nb_r, nb_c))
            days += 1
        if fresh > 0:
            return -1
        if days > 0:
            return days - 1
        return days