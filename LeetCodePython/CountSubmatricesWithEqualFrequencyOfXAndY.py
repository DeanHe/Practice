"""
Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:

grid[0][0]
an equal frequency of 'X' and 'Y'.
at least one 'X'.

Example 1:
Input: grid = [["X","Y","."],["Y",".","."]]
Output: 3

Explanation:

Example 2:
Input: grid = [["X","X"],["X","Y"]]
Output: 0

Explanation:
No submatrix has an equal frequency of 'X' and 'Y'.

Example 3:
Input: grid = [[".","."],[".","."]]
Output: 0

Explanation:
No submatrix has at least one 'X'.

Constraints:
1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'.

hints:
1 Replace ’X’ with 1, ’Y’ with -1 and ’.’ with 0.
2 You need to find how many submatrices grid[0..x][0..y] have a sum of 0 and at least one ’X’.
3 Use prefix sum to calculate submatrices sum.
"""
from typing import List


class CountSubmatricesWithEqualFrequencyOfXAndY:
    def numberOfSubmatrices(self, grid: List[List[str]]) -> int:
        res = 0
        rows = len(grid)
        cols = len(grid[0])
        pre_sum_X = [[0] * (cols + 1) for _ in range(rows + 1)]
        pre_sum_Y = [[0] * (cols + 1) for _ in range(rows + 1)]
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 'X':
                    pre_sum_X[r + 1][c + 1] += 1
                elif grid[r][c] == 'Y':
                    pre_sum_Y[r + 1][c + 1] += 1
                pre_sum_X[r + 1][c + 1] += pre_sum_X[r][c + 1] + pre_sum_X[r + 1][c] - pre_sum_X[r][c]
                pre_sum_Y[r + 1][c + 1] += pre_sum_Y[r][c + 1] + pre_sum_Y[r + 1][c] - pre_sum_Y[r][c]
                if pre_sum_X[r + 1][c + 1] == pre_sum_Y[r + 1][c + 1] and pre_sum_X[r + 1][c + 1] > 0:
                    res += 1
        return res
