"""
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 2^31 - 1
"""
from typing import List


class LongestIncreasingPathInaMatrix:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        if not matrix or not matrix[0]:
            return 0
        rows, cols = len(matrix), len(matrix[0])
        mem = [[0] * cols for _ in range(rows)]

        def dfs(r, c):
            if not mem[r][c]:
                cur = matrix[r][c]
                mem[r][c] = 1 + max(
                    dfs(r - 1, c) if r > 0 and cur > matrix[r - 1][c] else 0,
                    dfs(r + 1, c) if r + 1 < rows and cur > matrix[r + 1][c] else 0,
                    dfs(r, c - 1) if c > 0 and cur > matrix[r][c - 1] else 0,
                    dfs(r, c + 1) if c + 1 < cols and cur > matrix[r][c + 1] else 0,
                )
            return mem[r][c]

        return max(dfs(r, c) for r in range(rows) for c in range(cols))