"""
Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

Example 1:
Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 2:
Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 3:
Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.

Constraints:
m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 10^5.
All elements in the matrix are distinct.

hints:
1 Find out and save the minimum of each row and maximum of each column in two lists.
2 Then scan through the whole matrix to identify the elements that satisfy the criteria.

Analysis:
there can exist at most one lucky number
"""
import math
from typing import List


class LuckyNumbersInaMatrix:
    def luckyNumbers (self, matrix: List[List[int]]) -> List[int]:
        res = []
        rows, cols = len(matrix), len(matrix[0])
        row_mins = [math.inf] * rows
        col_maxs = [-math.inf] * cols
        for r in range(rows):
            for c in range(cols):
                if matrix[r][c] < row_mins[r]:
                    row_mins[r] = matrix[r][c]
                if col_maxs[c] < matrix[r][c]:
                    col_maxs[c] = matrix[r][c]
        if max(row_mins) == min(col_maxs):
            res.append(max(row_mins))
        return res