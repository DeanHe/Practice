"""
You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.



Example 1:
Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.

Example 2:
Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.


Constraints:
1 <= m, n <= 10^5
2 <= m * n <= 10^5
1 <= guards.length, walls.length <= 5 * 10^4
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.

hint:
1 Create a 2D array to represent the grid. Can you mark the tiles that can be seen by a guard?
2 Iterate over the guards, and for each of the 4 directions, advance the current tile and mark the tile. When should you stop advancing?
"""
from typing import List

class CountUnguardedCellsInTheGrid:
    def countUnguarded(self, m: int, n: int, guards: List[List[int]], walls: List[List[int]]) -> int:
        dirs = [0, 1, 0, -1, 0]
        dp = [[0] * n for _ in range(m)]
        res = m * n - len(guards) - len(walls)
        for r, c in guards + walls:
            dp[r][c] = 1
        for r, c in guards:
            for i in range(len(dirs) - 1):
                dr, dc = dirs[i], dirs[i + 1]
                cur_r, cur_c = r, c
                while 0 <= cur_r + dr < m and 0 <= cur_c + dc < n and dp[cur_r + dr][cur_c + dc] != 1:
                    cur_r += dr
                    cur_c += dc
                    if dp[cur_r][cur_c] == 0:
                        res -= 1
                    dp[cur_r][cur_c] = 2
        return res
