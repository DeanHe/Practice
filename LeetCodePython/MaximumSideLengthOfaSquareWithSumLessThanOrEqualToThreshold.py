"""
Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

Example 1:
Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
Explanation: The maximum side length of square with sum less than 4 is 2 as shown.

Example 2:
Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
Output: 0

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 300
0 <= mat[i][j] <= 10^4
0 <= threshold <= 10^5

hints:
1 Store prefix sum of all grids in another 2D array.
2 Try all possible solutions and if you cannot find one return -1.
3 If x is a valid answer then any y < x is also valid answer. Use binary search to find answer.

analysis:
TC:O(M*N + min(M,N))
"""
from typing import List


class MaximumSideLengthOfaSquareWithSumLessThanOrEqualToThreshold:

    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        res = 0
        rows = len(mat)
        cols = len(mat[0])
        pre_sum = [[0] * (cols + 1) for _ in range(rows + 1)]
        for r in range(rows):
            for c in range(cols):
                pre_sum[r + 1][c + 1] = pre_sum[r][c + 1] + pre_sum[r + 1][c] - pre_sum[r][c] + mat[r][c]

        def get_area(r1, c1, r2, c2):
            return pre_sum[r2 + 1][c2 + 1] - pre_sum[r2 + 1][c1] - pre_sum[r1][c2 + 1] + pre_sum[r1][c1]

        for r in range(rows):
            for c in range(cols):
                for edge in range(res + 1, min(rows, cols) + 1):
                    if r + edge - 1 < rows and c + edge - 1 < cols and get_area(r, c, r + edge - 1, c + edge - 1) <= threshold:
                        res = edge
                    else:
                        break
        return res
