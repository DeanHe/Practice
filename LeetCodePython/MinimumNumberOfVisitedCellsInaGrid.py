"""
You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).

Starting from the cell (i, j), you can move to one of the following cells:

Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
Return the minimum number of cells you need to visit to reach the bottom-right cell (m - 1, n - 1). If there is no valid path, return -1.

Example 1:
Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
Output: 4
Explanation: The image above shows one of the paths that visits exactly 4 cells.

Example 2:
Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
Output: 3
Explanation: The image above shows one of the paths that visits exactly 3 cells.

Example 3:
Input: grid = [[2,1,0],[1,0,0]]
Output: -1
Explanation: It can be proven that no path exists.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
0 <= grid[i][j] < m * n
grid[m - 1][n - 1] == 0

hints:
1 For each cell (i,j), it is critical to find out the minimum number of steps to reach (i,j), denoted dis[i][j], quickly, given the tight constraint.
2 Calculate dis[i][j] going left to right, top to bottom.
3 Suppose we want to calculate dis[i][j], keep track of a priority queue that stores (dis[i][k], i, k) for all k ≤ j, and another priority queue that stores (dis[k][j], k, j) for all k ≤ i.
"""
import heapq
from typing import List


class MinimumNumberOfVisitedCellsInaGrid:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dist = [[None] * cols for _ in range(rows)]
        pq = [(1, 0, 0)]
        while pq:
            d, r, c = heapq.heappop(pq)
            if not dist[r][c]:
                dist[r][c] = d
                if r == rows - 1 and c == cols - 1:
                    return d
                for k in range(1, grid[r][c] + 1):
                    nb_r = r + k
                    if nb_r < rows and not dist[nb_r][c]:
                        heapq.heappush(pq, (d + 1, nb_r, c))
                    nb_c = c + k
                    if nb_c < cols and not dist[r][nb_c]:
                        heapq.heappush(pq, (d + 1, r, nb_c))
        return -1