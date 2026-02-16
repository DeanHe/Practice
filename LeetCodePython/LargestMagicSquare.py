"""
A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.


Example 1:
Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12

Example 2:
Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 10^6

hints:
1 Check all squares in the matrix and find the largest one.

Analysis:
prefix sum

TC: O(N^3)
SC: O(N^2)
"""
from typing import List


class LargestMagicSquare:
    def largestMagicSquare(self, grid: List[List[int]]) -> int:
        rows = len(grid)
        cols = len(grid[0])
        row_pre_sum = [[0] * cols for _ in range(rows)]
        col_pre_sum = [[0] * cols for _ in range(rows)]
        for r in range(rows):
            row_pre_sum[r][0] = grid[r][0]
            for c in range(1, cols):
                row_pre_sum[r][c] = grid[r][c] + row_pre_sum[r][c - 1]
        for c in range(cols):
            col_pre_sum[0][c] = grid[0][c]
            for r in range(1, rows):
                col_pre_sum[r][c] = grid[r][c] + col_pre_sum[r - 1][c]
        for k in range(min(rows, cols), 1, -1):
            for r in range(rows - k + 1):
                for c in range(cols - k + 1):
                    if c == 0:
                        target = row_pre_sum[r][c + k - 1]
                    else:
                        target = row_pre_sum[r][c + k - 1] - row_pre_sum[r][c - 1]
                    check = True
                    for i in range(r, r + k):
                        if c == 0:
                            r_sum = row_pre_sum[i][c + k - 1]
                        else:
                            r_sum = row_pre_sum[i][c + k - 1] - row_pre_sum[i][c - 1]
                        if r_sum != target:
                            check = False
                            break
                    if not check:
                        continue
                    for i in range(c, c + k):
                        if r == 0:
                            c_sum = col_pre_sum[r + k - 1][i]
                        else:
                            c_sum = col_pre_sum[r + k - 1][i] - col_pre_sum[r - 1][i]
                        if c_sum != target:
                            check = False
                            break
                    if not check:
                        continue
                    d1 = d2 = 0
                    for i in range(k):
                        d1 += grid[r + i][c + i]
                        d2 += grid[r + i][c + k - 1 - i]
                    if d1 == target and d2 == target:
                        return k
        return 1

