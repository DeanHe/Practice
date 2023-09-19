"""
You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

Example 1:
Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
Output: 0
Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).

Example 2:
Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.

Example 3:
Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.


Constraints:
1 <= grid.length == n <= 400
grid[i].length == n
grid[i][j] is either 0 or 1.
There is at least one thief in the grid.

hints:
1 Consider using both BFS and binary search together.
2 Launch a BFS starting from all the cells containing thieves to calculate d[x][y] which is the smallest Manhattan distance from (x, y) to the nearest grid that contains thieves.
3 To check if the bottom-right cell of the grid can be reached **through a path of safeness factor v**, eliminate all cells (x, y) such that grid[x][y] < v. if (0, 0) and (n - 1, n - 1) are still connected, there exists a path between (0, 0) and (n - 1, n - 1) of safeness factor v.
4 Binary search over the final safeness factor v.

TC: O(V + ElogV)
"""
import heapq
from collections import deque
from typing import List


class FindTheSafestPathInaGrid:
    def maximumSafenessFactor(self, grid: List[List[int]]) -> int:
        dirs = [0, 1, 0, -1, 0]
        n = len(grid)
        q = deque([])
        for r in range(n):
            for c in range(n):
                # add thieves
                if grid[r][c] == 1:
                    q.append((r, c))
        while q:
            sz = len(q)
            for _ in range(sz):
                r, c = q.popleft()
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < n and 0 <= nb_c < n:
                        if grid[nb_r][nb_c] == 0:
                            grid[nb_r][nb_c] = grid[r][c] + 1
                            q.append((nb_r, nb_c))

        pq = []
        # max heap
        heapq.heappush(pq, (-grid[0][0], 0, 0))
        while pq:
            val, r, c = heapq.heappop(pq)
            if grid[r][c] > 0:
                # mark as visited
                grid[r][c] = val
                if r == c == n - 1:
                    return -val - 1
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < n and 0 <= nb_c < n:
                        if grid[nb_r][nb_c] > 0:
                            heapq.heappush(pq, (-min(-val, grid[nb_r][nb_c]), nb_r, nb_c))
        return -1



