"""
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
"""
from typing import List


class SpiralMatrix:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        if len(matrix) == 0:
            return res
        r1, r2, c1, c2 = 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        while r1 <= r2 and c1 <= c2:
            if r1 == r2:
                for c in range(c1, c2 + 1):
                    res.append(matrix[r1][c])
            elif c1 == c2:
                for r in range(r1, r2 + 1):
                    res.append(matrix[r][c2])
            else:
                for c in range(c1, c2):
                    res.append(matrix[r1][c])
                for r in range(r1, r2):
                    res.append(matrix[r][c2])
                for c in range(c2, c1, -1):
                    res.append(matrix[r2][c])
                for r in range(r2, r1, -1):
                    res.append(matrix[r][c1])
            r1 += 1
            r2 -= 1
            c1 += 1
            c2 -= 1
        return res
