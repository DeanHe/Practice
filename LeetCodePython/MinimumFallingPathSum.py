"""
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

Example 1:
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

Example 2:
Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.

Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
"""
from typing import List


class MinimumFallingPathSum:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        rows, cols = len(matrix), len(matrix[0])
        for r in range(1, rows):
            for c in range(cols):
                if c == 0:
                    matrix[r][c] = matrix[r][c] + min(matrix[r - 1][c], matrix[r - 1][c + 1])
                elif c == cols - 1:
                    matrix[r][c] = matrix[r][c] + min(matrix[r - 1][c], matrix[r - 1][c - 1])
                else:
                    matrix[r][c] = matrix[r][c] + min(matrix[r - 1][c - 1], matrix[r - 1][c], matrix[r - 1][c + 1])
        return min(matrix[-1])

