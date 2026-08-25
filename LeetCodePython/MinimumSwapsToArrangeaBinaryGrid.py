"""
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

A grid is said to be valid if all the cells above the main diagonal are zeros.

Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

Example 1:
Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3

Example 2:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0

Constraints:
n == grid.length == grid[i].length
1 <= n <= 200
grid[i][j] is either 0 or 1

analysis:
Greedy
TC:O(N^2)
"""
from typing import List


class MinimumSwapsToArrangeaBinaryGrid:
    def minSwaps(self, grid: List[List[int]]) -> int:
        n = len(grid)
        zeros = [0] * n
        for r in range(n):
            for c in range(n - 1, -1, -1):
                if grid[r][c] == 0:
                    zeros[r] += 1
                else:
                    break
        res = 0
        for r in range(n):
            if zeros[r] < n - 1 - r:
                i = r
                while i < n and zeros[i] < n - 1 - r:
                    i += 1
                if i == n:
                    # Did not find any number greater than or equal to len-r-1
                    return -1
                while r < i:
                    # bubble up swap
                    zeros[i], zeros[i - 1] = zeros[i - 1], zeros[i]
                    res += 1
                    i -= 1
        return res
