"""
You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.

A V-shaped diagonal segment is defined as:

The segment starts with 1.
The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
The segment:
Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).
Continues the sequence in the same diagonal direction.
Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.


Return the length of the longest V-shaped diagonal segment. If no valid segment exists, return 0.



Example 1:

Input: grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]

Output: 5

Explanation:



The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,2) → (1,3) → (2,4), takes a 90-degree clockwise turn at (2,4), and continues as (3,3) → (4,2).
Example 2:
Input: grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
Output: 4

Explanation:
The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: (2,3) → (3,2), takes a 90-degree clockwise turn at (3,2), and continues as (2,1) → (1,0).

Example 3:
Input: grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]
Output: 5

Explanation:
The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,0) → (1,1) → (2,2) → (3,3) → (4,4).

Example 4:
Input: grid = [[1]]
Output: 1
Explanation:
The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: (0,0).

Constraints:
n == grid.length
m == grid[i].length
1 <= n, m <= 500
grid[i][j] is either 0, 1 or 2.

hints:
1 Use dynamic programming to determine the best point to make a 90-degree rotation in the diagonal path while maintaining the required sequence.
2 Represent dynamic programming states as (row, col, currentDirection, hasMadeTurnYet). Track the current position, direction of traversal, and whether a turn has already been made, and take transitions accordingly to find the longest V-shaped diagonal segment.

Analysis:
DP
TC: O(M*N)
"""
from functools import cache
from typing import List


class LengthOfLongestVShapedDiagonalSegment:
    def lenOfVDiagonal(self, grid: List[List[int]]) -> int:
        dirs = [(1, 1), (1, -1), (-1, -1), (-1, 1)]
        rows, cols = len(grid), len(grid[0])
        res = 0

        @cache
        def dfs(r, c, d, turned, nxt):
            step =0
            nb_r, nb_c = r + dirs[d][0], c + dirs[d][1]
            if 0 <= nb_r < rows and 0 <= nb_c < cols and grid[nb_r][nb_c] == nxt:
                step = max(step, dfs(nb_r, nb_c, d, turned, 2 - nxt) + 1)
                if not turned:
                    # make a turn
                    step = max(step, dfs(nb_r, nb_c, (d + 1) % 4, not turned, 2 - nxt) + 1)
            return step

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    for d in range(4):
                        res = max(res, dfs(r, c, d, False, 2) + 1)
        return res

