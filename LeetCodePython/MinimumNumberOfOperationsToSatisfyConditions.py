"""
You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any cell to any non-negative number. You need to perform some operations such that each cell grid[i][j] is:

Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
Return the minimum number of operations needed.

Example 1:
Input: grid = [[1,0,2],[1,0,2]]
Output: 0

Explanation:
All the cells in the matrix already satisfy the properties.

Example 2:
Input: grid = [[1,1,1],[0,0,0]]
Output: 3

Explanation:
The matrix becomes [[1,0,1],[1,0,1]] which satisfies the properties, by doing these 3 operations:

Change grid[1][0] to 1.
Change grid[0][1] to 0.
Change grid[1][2] to 1.

Example 3:
Input: grid = [[1],[2],[3]]
Output: 2

Explanation:
There is a single column. We can change the value to 1 in each cell using 2 operations.

Constraints:
1 <= n, m <= 1000
0 <= grid[i][j] <= 9

analysis:
DP precompute
TC: O(rows * cols)
"""
import math
from functools import cache
from typing import List


class MinimumNumberOfOperationsToSatisfyConditions:
    def minimumOperations(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        # count the number 0 - 9 in each column
        cnt = [[0] * 10 for _ in range(cols)]
        for r in range(rows):
            for c in range(cols):
                val = grid[r][c]
                cnt[c][val] += 1
        # cost[c][val] is total cost of changing all column c to val;
        cost = [[0] * 10 for _ in range(cols)]
        for val in range(10):
            for c in range(cols):
                cost[c][val] = rows - cnt[c][val]

        @cache
        def dfs(c, pre_col_val):
            if c == cols:
                return 0
            res = math.inf
            for val in range(10):
                if val != pre_col_val:
                    res = min(res, dfs(c + 1, val) + cost[c][val])
            return res

        return dfs(0, -1)




