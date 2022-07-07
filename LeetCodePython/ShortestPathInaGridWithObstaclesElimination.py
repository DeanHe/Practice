"""
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

Example 1:
Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:
Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0

hint:
1 Use BFS.
2 BFS on (x,y,r) x,y is coordinate, r is remain number of obstacles you can remove.

"""
import collections
from typing import List


class ShortestPathInaGridWithObstaclesElimination:
    def shortestPath(self, grid: List[List[int]], k: int) -> int:
        steps, rows, cols = 0, len(grid), len(grid[0])
        dirs = [0, 1, 0, -1, 0]
        q = collections.deque()
        visited = [[[False] * (k + 1) for _ in range(cols)] for _ in range(rows)]
        visited[0][0][0] = True
        q.append((0, 0, 0))
        while q:
            sz = len(q)
            for _ in range(sz):
                r, c, obs = q.popleft()
                if r == rows - 1 and c == cols - 1:
                    return steps
                for i in range(len(dirs) - 1):
                    nb_r, nb_c, nb_obs = r + dirs[i], c + dirs[i + 1], obs
                    if 0 <= nb_r < rows and 0 <= nb_c < cols:
                        if grid[nb_r][nb_c] == 1:
                            nb_obs += 1
                        if nb_obs <= k and not visited[nb_r][nb_c][nb_obs]:
                            visited[nb_r][nb_c][nb_obs] = True
                            q.append((nb_r, nb_c, nb_obs))
            steps += 1
        return -1