"""
Given a matrix and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:
Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:
Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Example 3:
Input: matrix = [[904]], target = 0
Output: 0

Constraints:
1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8

Using a 2D prefix sum, we can query the sum of any submatrix in O(1) time. Now for each (r1, r2), we can find the largest sum of a submatrix that uses every row in [r1, r2] in linear time using a sliding window.
"""
from typing import List


class NumberOfSubmatricesThatSumToTarget:
    def numSubmatrixSumTarget(self, matrix: List[List[int]], target: int) -> int:
        rows, cols, res = len(matrix), len(matrix[0]), 0
        pre_sum = [[0] * cols for _ in range(rows)]
        for r in range(rows):
            for c in range(cols):
                if c == 0:
                    pre_sum[r][c] = matrix[r][c]
                else:
                    pre_sum[r][c] = pre_sum[r][c - 1] + matrix[r][c]

        for sc in range(cols):
            for ec in range(sc, cols):
                row_sum_cnt = {0: 1}
                total = 0
                for r in range(rows):
                    if sc == 0:
                        total += pre_sum[r][ec]
                    else:
                        total += pre_sum[r][ec] - pre_sum[r][sc - 1]
                    if total - target in row_sum_cnt:
                        res += row_sum_cnt[total - target]
                    if total in row_sum_cnt:
                        row_sum_cnt[total] += 1
                    else:
                        row_sum_cnt[total] = 1
        return res
