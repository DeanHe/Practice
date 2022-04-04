"""
Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input:  [[5]],2
Output: false

Explanation:
false if not included.

Example 2:
Input:  [
[1, 3, 5, 7],
[10, 11, 16, 20],
[23, 30, 34, 50]
],3
Output: true

Explanation:
return true if included.

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-10^4 <= matrix[i][j], target <= 10^4
"""
from typing import List


class Searcha2DMatrix:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        rows, cols = len(matrix), len(matrix[0])
        s, e = 0, rows * cols - 1
        while s + 1 < e:
            mid = s + (e - s) // 2
            r, c = mid // cols, mid % cols
            if matrix[r][c] == target:
                return True
            elif matrix[r][c] < target:
                s = mid
            else:
                e = mid
        r, c = s // cols, s % cols
        if matrix[r][c] == target:
            return True
        r, c = e // cols, e % cols
        if matrix[r][c] == target:
            return True
        return False
