"""
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a ROOM, that room should remain filled with INF

Example1
Input:
[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output:
[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
Example2

Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]
"""
import collections
from typing import List


class WallsAndGates:
    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        empty = 2147483647
        rows, cols = len(rooms), len(rooms[0])
        dirs = [(0, 1), (0, -1), (-1, 0), (1, 0)]
        q = collections.deque()
        for r in range(rows):
            for c in range(cols):
                if rooms[r][c] == 0:
                    q.append((r, c))
        while q:
            sz = len(q)
            for i in range(sz):
                r, c = q.popleft()
                for dr, dc in dirs:
                    nb_r, nb_c = r + dr, c + dc
                    if 0 <= nb_r < rows and 0 <= nb_c < cols and rooms[nb_r][nb_c] == empty:
                        rooms[nb_r][nb_c] = rooms[r][c] + 1
                        q.append((nb_r, nb_c))
