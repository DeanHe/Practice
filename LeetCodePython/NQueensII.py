"""
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Example 2:
Input: n = 1
Output: 1


Constraints:
1 <= n <= 9
"""

class NQueensII:
    def totalNQueens(self, n: int) -> int:
        board = [0] * n

        def dfs(r):

            def is_valid(row, col):
                for r in range(row):
                    if board[r] == col:
                        return False
                    if abs(r - row) == abs(board[r] - col):
                        return False
                return True

            if r == n:
                return 1
            res = 0
            for c in range(n):
                if is_valid(r, c):
                    board[r] = c
                    res += dfs(r + 1)
                    board[r] = 0
            return res

        return dfs(0)