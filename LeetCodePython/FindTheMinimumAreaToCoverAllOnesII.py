"""
You are given a 2D binary array grid. You need to find 3 non-overlapping rectangles having non-zero areas with horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.

Return the minimum possible sum of the area of these rectangles.

Note that the rectangles are allowed to touch.

Example 1:
Input: grid = [[1,0,1],[1,1,1]]
Output: 5
Explanation:
The 1's at (0, 0) and (1, 0) are covered by a rectangle of area 2.
The 1's at (0, 2) and (1, 2) are covered by a rectangle of area 2.
The 1 at (1, 1) is covered by a rectangle of area 1.

Example 2:
Input: grid = [[1,0,1,0],[0,1,0,1]]
Output: 5
Explanation:
The 1's at (0, 0) and (0, 2) are covered by a rectangle of area 3.
The 1 at (1, 1) is covered by a rectangle of area 1.
The 1 at (1, 3) is covered by a rectangle of area 1.

Constraints:
1 <= grid.length, grid[i].length <= 30
grid[i][j] is either 0 or 1.
The input is generated such that there are at least three 1's in grid.

analysis:
6 arrangement cases
TC: O(rows^2 * cols^2)
"""
import math
from typing import List


class FindTheMinimumAreaToCoverAllOnesII:

    def minimumSum(self, grid: List[List[int]]) -> int:
        def minimumArea(start_r, start_c, end_r, end_c):
            min_row = end_r
            max_row = start_r
            min_col = end_c
            max_col = start_c
            found = False
            for r in range(start_r, end_r + 1):
                for c in range(start_c, end_c + 1):
                    if grid[r][c] == 1:
                        min_row = min(min_row, r)
                        max_row = max(max_row, r)
                        min_col = min(min_col, c)
                        max_col = max(max_col, c)
                        found = True
            if found:
                return (max_row - min_row + 1) * (max_col - min_col + 1)
            else:
                return 0

        rows, cols = len(grid), len(grid[0])
        res = math.inf
        one = two = three = 0

        """
        -------------
        |    (1)    |
        |           |
        -------------
        | (2) | (3) |
        |     |     |
        -------------
        """
        for r in range(rows - 1):
            one = minimumArea(0, 0, r, cols - 1)
            for c in range(cols - 1):
                two = minimumArea(r + 1, 0, rows - 1, c)
                three = minimumArea(r + 1, c + 1, rows - 1, cols - 1)
                res = min(res, one + two + three)
        """
        -------------
        |     | (2) |
        |     |     |
          (1) -------
        |     |     |
        |     | (3) |
        -------------
        """
        for c in range(cols - 1):
            one = minimumArea(0, 0, rows - 1, c)
            for r in range(rows - 1):
                two = minimumArea(0, c + 1, r, cols - 1)
                three = minimumArea(r + 1, c + 1, rows - 1, cols - 1)
                res = min(res, one + two + three)
        """
        -------------
        |     |     |
        | (2) |     |
        ------- (1) |
        |     |     |
        | (3) |     |
        -------------
        """
        for c in range(cols - 1, 0, -1):
            one = minimumArea(0, c, rows - 1, cols - 1)
            for r in range(rows - 1):
                two = minimumArea(0, 0, r, c - 1)
                three = minimumArea(r + 1, 0, rows - 1, c - 1)
                res = min(res, one + two + three)
        """
        -------------
        | (2) | (3) |
        |     |     |
        ------------
        |           |
        |    (1)    |
        -------------
        """
        for r in range(rows - 1, 0, -1):
            one = minimumArea(r, 0, rows - 1, cols - 1)
            for c in range(cols - 1):
                two = minimumArea(0, 0, r - 1, c)
                three = minimumArea(0, c + 1, r - 1, cols - 1)
                res = min(res, one + two + three)
        """
        -------------
        |    (1)    |
        -------------
        |    (2)    |
        -------------
        |    (3)    |
        -------------
        """
        for r in range(rows - 1):
            for r2 in range(r + 1, rows):
                one = minimumArea(0, 0, r, cols - 1)
                two = minimumArea(r + 1, 0, r2 - 1, cols - 1)
                three = minimumArea(r2, 0, rows - 1, cols - 1)
                res = min(res, one + two + three)
        """
        -------------
        |   |   |   |
        |   |   |   |
        |(1)|(2)|(3)|
        |   |   |   |
        |   |   |   |
        -------------
        """
        for c in range(cols - 1):
            for c2 in range(c + 1, cols):
                one = minimumArea(0, 0, rows - 1, c)
                two = minimumArea(0, c + 1, rows - 1, c2 - 1)
                three = minimumArea(0, c2, rows - 1, cols - 1)
                res = min(res, one + two + three)
        return res



