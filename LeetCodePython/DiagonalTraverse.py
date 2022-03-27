"""
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 10^4
1 <= m * n <= 10^4
-10^5 <= mat[i][j] <= 10^5
"""
from typing import List


class DiagonalTraverse:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        rows, cols, r, c, res = len(mat), len(mat[0]), 0, 0, []
        for i in range(rows * cols):
            res.append(mat[r][c])
            if (r + c) % 2 == 0:
                if c == cols - 1:
                    r += 1
                elif r == 0:
                    c += 1
                else:
                    r -= 1
                    c += 1
            else:
                if r == rows - 1:
                    c += 1
                elif c == 0:
                    r += 1
                else:
                    r += 1
                    c -= 1
        return res
