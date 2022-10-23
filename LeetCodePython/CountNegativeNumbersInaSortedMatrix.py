"""
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.



Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100


Follow up: Could you find an O(n + m) solution?
hints:
1 Use binary search for optimization or simply brute force.
"""
from typing import List


class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        rows, cols, res = len(grid), len(grid[0]), 0
        r, c = rows - 1, 0
        while r >= 0 and c < cols:
            if grid[r][c] < 0:
                res += cols - c
                r -= 1
            else:
                c += 1
        return res
