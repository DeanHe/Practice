"""
You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

Example 1:
Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.

Example 2:
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10

hints:
1 Run DFS from each non-zero cell.
2 Each time you pick a cell to start from, add up the number of fish contained in the cells you visit.

Analysis:
BFS
TC:O(M*N)
"""
from collections import deque
from typing import List


class MaximumNumberOfFishInaGrid:
    def findMaxFish(self, grid: List[List[int]]) -> int:
        res = 0
        rows, cols = len(grid), len(grid[0])
        dirs = [0, 1, 0, -1, 0]

        def bfs(r, c):
            q = deque([(r, c)])
            res = grid[r][c]
            grid[r][c] = 0
            while q:
                r, c = q.popleft()
                for i in range(len(dirs) - 1):
                    nb_r = r + dirs[i]
                    nb_c = c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols and grid[nb_r][nb_c] > 0:
                        q.append((nb_r, nb_c))
                        res += grid[nb_r][nb_c]
                        grid[nb_r][nb_c] = 0
            return res

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] > 0:
                    res = max(res, bfs(r, c))
        return res
