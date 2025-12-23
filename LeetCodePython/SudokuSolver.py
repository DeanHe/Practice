"""
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example 1:
Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:


Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
"""
from typing import List


class SudokuSolver:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        rows = len(board)
        cols = len(board[0])

        def dfs(r, c):
            def is_valid(r, c):
                for i in range(rows):
                    if board[r][c] == board[i][c] and i != r:
                        return False
                for i in range(cols):
                    if board[r][c] == board[r][i] and i != c:
                        return False
                sr = r - r % 3
                sc = c - c % 3
                for i in range(3):
                    for j in range(3):
                        if board[sr + i][sc + j] == board[r][c] and (sr + i != r or sc + j != c):
                            return False
                return True

            if r == rows:
                return True
            if c == cols:
                return dfs(r + 1, 0)
            if board[r][c] == '.':
                for k in range(1, 10):
                    board[r][c] = chr(ord('0') + k)
                    if is_valid(r, c):
                        if dfs(r, c + 1):
                            return True
                    board[r][c] = '.'
            else:
                return dfs(r, c + 1)
            return False

        dfs(0, 0)
