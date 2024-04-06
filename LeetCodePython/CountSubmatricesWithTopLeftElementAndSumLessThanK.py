"""
You are given a 0-indexed integer matrix grid and an integer k.

Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.

Example 1:
Input: grid = [[7,6,3],[6,6,1]], k = 18
Output: 4
Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.

Example 2:
Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
Output: 6
Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 20.


Constraints:
m == grid.length
n == grid[i].length
1 <= n, m <= 1000
0 <= grid[i][j] <= 1000
1 <= k <= 10^9
"""
from typing import List

class CountSubmatricesWithTopLeftElementAndSumLessThanK:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        res = 0
        if grid[0][0] <= k:
            res += 1
        rows, cols = len(grid), len(grid[0])
        for r in range(1, rows):
            grid[r][0] += grid[r - 1][0]
            if grid[r][0] <= k:
                res += 1
        for c in range(1, cols):
            grid[0][c] += grid[0][c - 1]
            if grid[0][c] <= k:
                res += 1
        for r in range(1, rows):
            for c in range(1, cols):
                grid[r][c] += grid[r - 1][c] + grid[r][c - 1] - grid[r - 1][c - 1]
                if grid[r][c] <= k:
                    res += 1
        return res