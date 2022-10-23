"""
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example 1:
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.

Example 2:
Input: board = [["X"]]
Output: [["X"]]

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
"""
import collections
from typing import List


class SurroundedRegions:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        rows, cols = len(board), len(board[0])
        dirs = [0, 1, 0, -1, 0]
        q = collections.deque()
        for r in range(rows):
            if board[r][0] == 'O':
                q.append((r, 0))
                board[r][0] = '#'
            if board[r][cols - 1] == 'O':
                q.append((r, cols - 1))
                board[r][cols - 1] = '#'
        for c in range(cols):
            if board[0][c] == 'O':
                q.append((0, c))
                board[0][c] = '#'
            if board[rows - 1][c] == 'O':
                q.append((rows - 1, c))
                board[rows - 1][c] = '#'
        while q:
            r, c = q.popleft()
            for i in range(len(dirs) - 1):
                nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                if 0 <= nb_r < rows and 0 <= nb_c < cols and board[nb_r][nb_c] == 'O':
                    board[nb_r][nb_c] = '#'
                    q.append((nb_r, nb_c))
        for r in range(rows):
            for c in range(cols):
                if board[r][c] == '#':
                    board[r][c] = 'O'
                elif board[r][c] == 'O':
                    board[r][c] = 'X'
