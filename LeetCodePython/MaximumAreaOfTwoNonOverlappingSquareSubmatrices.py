"""
You are given a 2D integer matrix mat of size m × n, where:

mat[r][c] == 1 means the cell at row r and column c is usable.
mat[r][c] == 0 means it is not usable.
Your task is to find two submatrices that satisfy the following conditions:

Both submatrices must be squares of the same side length k.
The two submatrices must not share any cell.
Each submatrix can only cover cells where mat[r][c] == 1.
Return the maximum possible area of each of the two squares. If it is not possible to choose two such squares, return 0.

Example 1:
Input: mat = [[1,1,1,0],[1,1,1,1],[0,0,1,1]]
Output: 4

Explanation:

The largest equal non-overlapping squares have side length k = 2 with area 4.

First square starts at top-left (0, 0) and covers cells (0, 0), (0, 1), (1, 0), and (1, 1).
Second square starts at top-left (1, 2) and covers cells (1, 2), (1, 3), (2, 2), and (2, 3).
Thus, the answer is 4.

Example 2:
Input: mat = [[0,1],[1,0]]
Output: 1

Explanation:
The largest equal non-overlapping squares have side length k = 1 with area 1.
First square starts at top-left (0, 1) and covers cell (0, 1).
Second square starts at top-left (1, 0) and covers cell (1, 0).
Thus, the answer is 1.

Example 3:
Input: mat = [[0,0],[0,1]]
Output: 0

Explanation:
There is only one usable cell, so it is impossible to choose two non-overlapping squares. Thus, the answer is 0.

Constraints:
mat.length == m
mat[i].length == n
1 <= m, n <= 500
mat[i][j] is either 0 or 1.

hints:
1 Binary search for the maximum side length k. If two valid squares of side length k exist, two valid squares of every smaller side length also exist.
2 Build a 2D prefix sum so that you can determine in constant time whether every cell in a given square is usable.
3 For a fixed k, record the minimum and maximum row and column among the top-left corners of all valid squares. Two of them can be disjoint if the difference between the maximum and minimum row is at least k, or the corresponding column difference is at least k.

analysis:
Binary search
TC:O(rows * cols * log(min(rows, cols))
"""
from typing import List


class MaximumAreaOfTwoNonOverlappingSquareSubmatrices:
    def maxArea(self, mat: List[List[int]]) -> int:
        rows = len(mat)
        cols = len(mat[0])
        for r in range(rows):
            for c in range(cols):
                if r > 0 and c > 0 and mat[r][c] == 1 and mat[r - 1][c] > 0 and mat[r][c - 1] > 0 and mat[r - 1][c - 1] > 0:
                    mat[r][c] = min(mat[r - 1][c], mat[r][c - 1], mat[r - 1][c - 1]) + 1

        def feasible(side):
            r1, c1 = rows, cols
            r2, c2 = -1, -1
            for r in range(side - 1, rows):
                for c in range(side - 1, cols):
                    if mat[r][c] >= side:
                        r1 = min(r1, r)
                        c1 = min(c1, c)
                        r2 = max(r2, r)
                        c2 = max(c2, c)
                    if r2 - r1 >= side or c2 - c1 >= side:
                        return True
            return False

        s, e = 1, min(rows, cols)
        while s + 1 < e:
            mid = (s + e) // 2
            if feasible(mid):
                s = mid
            else:
                e = mid
        if feasible(e):
            return e * e
        if feasible(s):
            return s * s
        return 0

