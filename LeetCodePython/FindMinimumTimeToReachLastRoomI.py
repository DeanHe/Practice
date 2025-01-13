"""
There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

Example 1:
Input: moveTime = [[0,4],[4,4]]
Output: 6
Explanation:
The minimum time required is 6 seconds.
At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.

Example 2:
Input: moveTime = [[0,0,0],[0,0,0]]
Output: 3
Explanation:
The minimum time required is 3 seconds.
At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.

Example 3:
Input: moveTime = [[0,1],[1,2]]
Output: 3

Constraints:
2 <= n == moveTime.length <= 50
2 <= m == moveTime[i].length <= 50
0 <= moveTime[i][j] <= 10^9
"""
import heapq
from typing import List


class FindMinimumTimeToReachLastRoomI:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        rows = len(moveTime)
        cols = len(moveTime[0])
        dirs = [0, 1, 0, -1, 0]
        pq = [(0, 0, 0)]
        dist = {}
        while pq:
            d, r, c = heapq.heappop(pq)
            if (r, c) not in dist:
                dist[(r, c)] = d
                if r == rows - 1 and c == cols - 1:
                    return d
                for i in range(len(dirs) - 1):
                    nb_r = r + dirs[i]
                    nb_c = c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols:
                        if (nb_r, nb_c) not in dist:
                            heapq.heappush(pq, (max(d, moveTime[nb_r][nb_c]) + 1, nb_r, nb_c))
        return -1





