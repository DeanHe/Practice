"""
You are given a 0-indexed 2D integer array grid of size m x n which represents a field. Each cell has one of three values:

0 represents grass,
1 represents fire,
2 represents a wall that you and fire cannot pass through.
You are situated in the top-left cell, (0, 0), and you want to travel to the safehouse at the bottom-right cell, (m - 1, n - 1).
Every minute, you may move to an adjacent grass cell. After your move, every fire cell will spread to all adjacent cells that are not walls.

Return the maximum number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse.
If this is impossible, return -1. If you can always reach the safehouse regardless of the minutes stayed, return 10^9.

Note that even if the fire spreads to the safehouse immediately after you have reached it, it will be counted as safely reaching the safehouse.

A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).



Example 1:
Input: grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
Output: 3
Explanation: The figure above shows the scenario where you stay in the initial position for 3 minutes.
You will still be able to safely reach the safehouse.
Staying for more than 3 minutes will not allow you to safely reach the safehouse.

Example 2:
Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
Output: -1
Explanation: The figure above shows the scenario where you immediately move towards the safehouse.
Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
Thus, -1 is returned.
Example 3:


Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
Output: 1000000000
Explanation: The figure above shows the initial grid.
Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
Thus, 10^9 is returned.


Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 300
4 <= m * n <= 2 * 10^4
grid[i][j] is either 0, 1, or 2.
grid[0][0] == grid[m - 1][n - 1] == 0

hint:
1 For some tile (x, y), how can we determine when, if ever, the fire will reach it?
2 We can use multi-source BFS to find the earliest time the fire will reach each cell.
3 Then, starting with a given t minutes of staying in the initial position,
we can check if there is a safe path to the safehouse using the obtained information about the fire.
4 We can use binary search to efficiently find the maximum t that allows us to reach the safehouse.
"""
import collections
from typing import List


class EscapeTheSpreadingFire:
    def maximumMinutes(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        fire_reach = [[-1] * cols for _ in range(rows)]
        person_reach = [[-1] * cols for _ in range(rows)]
        dirs = [0, 1, 0, -1, 0]

        def bfs(queue, board, is_fire=True):
            while queue:
                r, c, time = queue.popleft()
                board[r][c] = time
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    if 0 <= nb_r < rows and 0 <= nb_c < cols and board[nb_r][nb_c] == -1 and grid[nb_r][nb_c] != 2:
                        if is_fire:
                            queue.append((nb_r, nb_c, time + 1))
                        else:
                            if time + 1 < fire_reach[nb_r][nb_c] \
                                or fire_reach[nb_r][nb_c] == -1 \
                                or (time + 1 == fire_reach[nb_r][nb_c] and nb_r == rows - 1 and nb_c == cols - 1):
                                queue.append((nb_r, nb_c, time + 1))




        fire_queue = collections.deque([(r, c, 0) for r in range(rows) for c in range(cols) if grid[r][c] == 1])
        bfs(fire_queue, fire_reach)
        bfs(collections.deque([(0, 0, 0)]), person_reach, False)

        safe_house_fire_reach, safe_house_person_reach = fire_reach[-1][-1], person_reach[-1][-1]
        if safe_house_person_reach == -1:
            return -1
        if safe_house_fire_reach == -1:
            return 1000000000
        if safe_house_fire_reach == safe_house_person_reach:
            return 0

        diff = fire_reach[-1][-1] - person_reach[-1][-1]
        if diff < fire_reach[-1][-2] - person_reach[-1][-2]:
            return diff
        if diff < fire_reach[-2][-1] - person_reach[-2][-1]:
            return diff
        return diff - 1
