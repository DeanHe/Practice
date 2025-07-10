"""
You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.

Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.

Example 2:
Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.

Example 3:
Input: m = 5, n = 5
Output: 580986

Constraints:
1 <= m <= 5
1 <= n <= 1000

hints:
1 Represent each colored column by a bitmask based on each cell color.
2 Use bitmasks DP with state (currentCell, prevColumn).

Analysis:
TC: O(cols * (3 ** rows))
"""
from functools import cache


class Solution:
    def colorTheGrid(self, m: int, n: int) -> int:
        MOD = 10 ** 9 + 7
        self.rows = m
        self.cols = n

        def get_color(mask, pos):
            return (mask >> (2 * pos)) & 3

        def set_color(mask, pos, color):
            return mask | (color << (2 * pos))

        def dfs(r, cur_col_mask, pre_col_mask, nbs):
            if r == self.rows:
                nbs.append(cur_col_mask)
                return
            for i in [1, 2, 3]:
                if get_color(pre_col_mask, r) != i and (r == 0 or get_color(cur_col_mask, r - 1) != i):
                    dfs(r + 1, set_color(cur_col_mask, r, i), pre_col_mask, nbs)

        @cache
        def neighbors(pre_col_mask):
            nbs = []
            dfs(0, 0, pre_col_mask, nbs)
            return nbs

        @cache
        def dp(c, pre_col_mask):
            if c == self.cols:
                return 1
            res = 0
            for nb in neighbors(pre_col_mask):
                res = (res + dp(c + 1, nb)) % MOD
            return res

        return dp(0, 0)
