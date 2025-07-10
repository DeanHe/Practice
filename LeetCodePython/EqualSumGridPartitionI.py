"""
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:

Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.

Example 1:
Input: grid = [[1,4],[2,3]]
Output: true
Explanation:
A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.

Example 2:
Input: grid = [[1,3],[2,4]]
Output: false
Explanation:
No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.


Constraints:
1 <= m == grid.length <= 10^5
1 <= n == grid[i].length <= 10^5
2 <= m * n <= 10^5
1 <= grid[i][j] <= 10^5
"""
from typing import List


class EqualSumGridPartitionI:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        rows = len(grid)
        cols = len(grid[0])
        row_sum = [0] * rows
        col_sum = [0] * cols
        for r in range(rows):
            r_sum = 0
            for c in range(cols):
                r_sum += grid[r][c]
                col_sum[c] += grid[r][c]
            row_sum[r] = r_sum
        for r in range(1, rows):
            row_sum[r] += row_sum[r - 1]
        for r in range(rows):
            if row_sum[r] * 2 == row_sum[-1]:
                return True
        for c in range(1, cols):
            col_sum[c] += col_sum[c - 1]
        for c in range(cols):
            if col_sum[c] * 2 == col_sum[-1]:
                return True
        return False
