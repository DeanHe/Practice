"""
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Example 2:
Input: matrix = [["0","1"],["1","0"]]
Output: 1

Example 3:
Input: matrix = [["0"]]
Output: 0


Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
"""
from typing import List


class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        rows, cols, max_edge = len(matrix), len(matrix[0]), 0
        dp = [[0] * cols for _ in range(rows)]
        for r in range(rows):
            if matrix[r][0] == '1':
                max_edge = 1
            dp[r][0] = int(matrix[r][0])
        for c in range(cols):
            if matrix[0][c] == '1':
                max_edge = 1
            dp[0][c] = int(matrix[0][c])
        for r in range(1, rows):
            for c in range(1, cols):
                if matrix[r][c] == '1':
                    dp[r][c] = min(dp[r - 1][c - 1], dp[r - 1][c], dp[r][c - 1]) + 1
                    max_edge = max(max_edge, dp[r][c])
        return max_edge * max_edge

