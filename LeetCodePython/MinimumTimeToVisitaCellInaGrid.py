"""
You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.



Example 1:



Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[1][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.
Example 2:



Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
Output: -1
Explanation: There is no path from the top left to the bottom-right cell.


Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
0 <= grid[i][j] <= 10^5
grid[0][0] == 0

hints:
1 Try using some algorithm that can find the shortest paths on a graph.
2 Consider the case where you have to go back and forth between two cells of the matrix to unlock some other cells.

analysis:
dikjstra
consider the diff between neighbor required time and cur time,
if the diff is odd, we can reach neighbor as soon as required time
if the diff is even, we can reach neighbor as soon as required time + 1
"""
import heapq
from typing import List


class MinimumTimeToVisitaCellInaGrid:
    def minimumTime(self, grid: List[List[int]]) -> int:
        if grid[0][1] > 1 and grid[1][0] > 1:
            return -1
        rows, cols = len(grid), len(grid[0])
        dirs = [0, 1, 0, -1, 0]
        visited = set()
        pq = [(grid[0][0], 0, 0)]
        while pq:
            time, r, c = heapq.heappop(pq)
            if (r, c) not in visited:
                visited.add((r, c))
                if r == rows - 1 and c == cols - 1:
                    return time
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols:
                        wait = 1 if (grid[nb_r][nb_c] - time) % 2 == 0 else 0
                        if (nb_r, nb_c) not in visited:
                            heapq.heappush(pq, (max(time + 1, grid[nb_r][nb_c] + wait), nb_r, nb_c))

