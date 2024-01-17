"""
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.

Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m * n <= 10^5
matrix[i][j] is either 0 or 1.

hints:
1 For each column, find the number of consecutive ones ending at each position.
2 For each row, sort the cumulative ones in non-increasing order and "fit" the largest submatrix.

analysis:
prefix sum by column
TC: O(rows * cols * log(cols))
"""
from typing import List


class LargestSubmatrixWithRearrangements:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        rows, cols = len(matrix), len(matrix[0])
        res = 0
        pre_sum = [0] * cols
        for r in range(rows):
            cur_row = matrix[r][:]
            for c in range(cols):
                if cur_row[c] != 0:
                    cur_row[c] += pre_sum[c]
            sorted_row = sorted(cur_row, reverse=True)
            for c in range(cols):
                res = max(res, (c + 1) * sorted_row[c])
            pre_sum = cur_row
        return res