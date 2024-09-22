"""
There is a 50 x 50 chessboard with one knight and some pawns on it. You are given two integers kx and ky where (kx, ky) denotes the position of the knight, and a 2D array positions where positions[i] = [xi, yi] denotes the position of the pawns on the chessboard.

Alice and Bob play a turn-based game, where Alice goes first. In each player's turn:

The player selects a pawn that still exists on the board and captures it with the knight in the fewest possible moves. Note that the player can select any pawn, it might not be one that can be captured in the least number of moves.
In the process of capturing the selected pawn, the knight may pass other pawns without capturing them. Only the selected pawn can be captured in this turn.
Alice is trying to maximize the sum of the number of moves made by both players until there are no more pawns on the board, whereas Bob tries to minimize them.

Return the maximum total number of moves made during the game that Alice can achieve, assuming both players play optimally.

Note that in one move, a chess knight has eight possible positions it can move to, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.

Example 1:
Input: kx = 1, ky = 1, positions = [[0,0]]
Output: 4
Explanation:
The knight takes 4 moves to reach the pawn at (0, 0).

Example 2:
Input: kx = 0, ky = 2, positions = [[1,1],[2,2],[3,3]]
Output: 8
Explanation:
Alice picks the pawn at (2, 2) and captures it in two moves: (0, 2) -> (1, 4) -> (2, 2).
Bob picks the pawn at (3, 3) and captures it in two moves: (2, 2) -> (4, 1) -> (3, 3).
Alice picks the pawn at (1, 1) and captures it in four moves: (3, 3) -> (4, 1) -> (2, 2) -> (0, 3) -> (1, 1).

Example 3:
Input: kx = 0, ky = 0, positions = [[1,2],[2,4]]
Output: 3
Explanation:
Alice picks the pawn at (2, 4) and captures it in two moves: (0, 0) -> (1, 2) -> (2, 4). Note that the pawn at (1, 2) is not captured.
Bob picks the pawn at (1, 2) and captures it in one move: (2, 4) -> (1, 2).


Constraints:
0 <= kx, ky <= 49
1 <= positions.length <= 15
positions[i].length == 2
0 <= positions[i][0], positions[i][1] <= 49
All positions[i] are unique.
The input is generated such that positions[i] != [kx, ky] for all 0 <= i < positions.length.

hints:
1 Use BFS to preprocess the minimum number of moves to reach one pawn from the other pawns.
2 Consider the knight’s original position as another pawn.
3 Use DP with a bitmask to store current pawns that have not been captured.
"""
import math
from collections import deque
from functools import cache
from typing import List


class MaximumNumberOfMovesToKillAllPawns:
    def maxMoves(self, kx: int, ky: int, positions: List[List[int]]) -> int:
        dirs = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]
        dist = {}
        positions.append([kx, ky])
        n = len(positions)

        def hash(a, b, c, d):
            return a * (50 ** 3) + b * (50 ** 2) + c * 50 + d

        def bfs(start_x, start_y):
            q = deque([(start_x, start_y)])
            d = 0
            dist[hash(start_x, start_y, start_x, start_y)] = d
            while q:
                sz = len(q)
                for _ in range(sz):
                    x, y = q.popleft()
                    for dir in dirs:
                        nb_x = x + dir[0]
                        nb_y = y + dir[1]
                        hash_nb = hash(start_x, start_y, nb_x, nb_y)
                        if 0 <= nb_x < 50 and 0 <= nb_y < 50 and hash_nb not in dist:
                            q.append((nb_x, nb_y))
                            dist[hash_nb] = d + 1
                d += 1

        for x, y in positions:
            bfs(x, y)

        @cache
        def dfs(state, cur):
            if state == (1 << n) - 1:
                return 0
            x, y = positions[cur]
            if state.bit_count() % 2 == 1:
                res = -math.inf
                for i in range(n):
                    if (1 << i) & state == 0:
                        nxt_x, nxt_y = positions[i]
                        res = max(res, dist[hash(x, y, nxt_x, nxt_y)] + dfs((1 << i) | state, i))
                return res
            else:
                res = math.inf
                for i in range(n):
                    if (1 << i) & state == 0:
                        nxt_x, nxt_y = positions[i]
                        res = min(res, dist[hash(x, y, nxt_x, nxt_y)] + dfs((1 << i) | state, i))
                return res

        return dfs(1 << (n - 1), n - 1)
