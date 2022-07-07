"""
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

Example 1:
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

Example 2:
Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.

Constraints:
1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1

hint:
1 Create an additive table that counts the sum of elements of submatrix with the superior corner at (0,0).
2 Loop over all subsquares in O(n^3) and check if the sum make the whole array to be ones, if it checks then add 1 to the answer.
"""
from typing import List


class CountSquareSubmatricesWithAllOnes:
    def countSquares(self, matrix: List[List[int]]) -> int:
        res, rows, cols = 0, len(matrix), len(matrix[0])
        dp = [[0] * cols for _ in range(rows)]
        for r in range(rows):
            dp[r][0] = matrix[r][0]
            res += dp[r][0]

        for c in range(cols):
            dp[0][c] = matrix[0][c]
            res += dp[0][c]

        for r in range(1, rows):
            for c in range(1, cols):
                if matrix[r][c] == 1:
                    dp[r][c] = min(dp[r - 1][c - 1], dp[r - 1][c], dp[r][c - 1]) + 1
                    res += dp[r][c]
        return res
