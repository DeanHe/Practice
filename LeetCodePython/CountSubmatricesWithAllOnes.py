"""
Given an m x n binary matrix mat, return the number of submatrices that have all ones.

Example 1:
Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2.
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

Example 2:
Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3.
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2.
There are 2 rectangles of side 3x1.
There is 1 rectangle of side 3x2.
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.

Constraints:
1 <= m, n <= 150
mat[i][j] is either 0 or 1.

hint:
1 For each row i, create an array nums where: if mat[i][j] == 0 then nums[j] = 0 else nums[j] = nums[j-1] +1.
2 In the row i, number of rectangles between column j and k(inclusive) and ends in row i, is equal to SUM(min(nums[j, .. idx])) where idx go from j to k.
Expected solution is O(n^3).
"""
from typing import List


class CountSubmatricesWithAllOnes:
    def numSubmat(self, mat: List[List[int]]) -> int:
        rows, cols, res = len(mat), len(mat[0]), 0
        heights = [0] * cols

        def helper():
            total = [0] * cols
            stack = []
            for c in range(cols):
                while stack:
                    if heights[c] <= heights[stack[-1]]:
                        stack.pop()
                    else:
                        break
                if stack:
                    pre = stack[-1]
                    total[c] = total[pre]
                    total[c] += heights[c] * (c - pre)
                else:
                    total[c] = heights[c] * (c + 1)
                stack.append(c)
            return sum(total)

        for r in range(rows):
            for c in range(cols):
                if mat[r][c] == 1:
                    heights[c] += 1
                else:
                    heights[c] = 0
            res += helper()
        return res