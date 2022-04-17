"""
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]

Constraints:
1 <= n <= 20
"""
from typing import List

class SpiralMatrixII:
    def generateMatrix(self, n: int) -> List[List[int]]:
        res = [[0] * n for _ in range(n)]
        r, c, dr, dc = 0, 0, 0, 1
        for val in range(n * n):
            res[r][c] = val + 1
            if res[(r + dr) % n][(c + dc) % n]:
                dr, dc = dc, -dr
            r += dr
            c += dc
        return res
