"""
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0

Example 3:
Input: matrix = [["1"]]
Output: 1

Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

analysis:
monotonic stack DP
TC: O(N^2)
"""
from typing import List


class MaximalRectangle:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        rows, cols, res = len(matrix), len(matrix[0]), 0
        heights = [0] * cols
        for r in range(rows):
            for c in range(cols):
                if matrix[r][c] == '1':
                    heights[c] += 1
                else:
                    heights[c] = 0
            res = max(res, self.calMaxArea(heights))
        return res

    def calMaxArea(self, heights):
        res = 0
        stack = []
        h = w = 0
        for i in range(len(heights)):
            while stack and heights[i] <= heights[stack[-1]]:
                h = heights[stack.pop()]
                if not stack:
                    w = i
                else:
                    w = i - stack[-1] - 1
                res = max(res, h * w)
            stack.append(i)
        while stack:
            h = heights[stack.pop()]
            if not stack:
                w = len(heights)
            else:
                w = len(heights) - stack[-1] - 1
            res = max(res, h * w)
        return res

    def maximalRectangleDP(self, matrix: List[List[str]]) -> int:
        rows = len(matrix)
        cols = len(matrix[0])
        left = [0] * cols
        right = [cols] * cols
        height = [0] * cols
        res = 0
        for r in range(rows):
            cur_left = 0
            cur_right = cols
            for c in range(cols):
                if matrix[r][c] == '1':
                    height[c] += 1
                else:
                    height[c] = 0
            for c in range(cols):
                if matrix[r][c] == '1':
                    left[c] = max(left[c], cur_left)
                else:
                    left[c] = 0
                    cur_left = c + 1
            for c in range(cols - 1, -1, -1):
                if matrix[r][c] == '1':
                    right[c] = min(right[c], cur_right)
                else:
                    right[c] = 0
                    cur_right = c - 1
            for c in range(cols):
                res = max(res, (right[c] - left[c]) * height[c])
        return res
