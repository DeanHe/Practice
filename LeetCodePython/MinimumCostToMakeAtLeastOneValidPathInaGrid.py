"""
Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.

Example 1:
Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.

Example 2:
Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).

Example 3:
Input: grid = [[1,2],[4,3]]
Output: 1


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 100
1 <= grid[i][j] <= 4

hints:
1 Build a graph where grid[i][j] is connected to all the four side-adjacent cells with weighted edge. the weight is 0 if the sign is pointing to the adjacent cell or 1 otherwise.
2 Do BFS from (0, 0) visit all edges with weight = 0 first. the answer is the distance to (m -1, n - 1).

Analysis:
Dijkstra's TC: O(n⋅m⋅log(n⋅m))
0-1 BFS TC: O(n⋅m)
"""
import math
from collections import deque
from typing import List


class MinimumCostToMakeAtLeastOneValidPathInaGrid:

    # 0-1 BFS
    def minCost(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        # match sign order: right, left, down, up
        dirs = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        q = deque([(0, 0)])
        dist = [[math.inf] * cols for _ in range(rows)]
        dist[0][0] = 0
        while q:
            r, c = q.popleft()
            for i, (dr, dc) in enumerate(dirs):
                cost = 1
                if i + 1 == grid[r][c]:
                    cost = 0
                nb_r = r + dr
                nb_c = c + dc
                if 0 <= nb_r < rows and 0 <= nb_c < cols and dist[r][c] + cost < dist[nb_r][nb_c]:
                    dist[nb_r][nb_c] = dist[r][c] + cost
                    if cost == 0:
                        q.appendleft((nb_r, nb_c))
                    else:
                        q.append((nb_r, nb_c))
        return dist[rows - 1][cols - 1]
