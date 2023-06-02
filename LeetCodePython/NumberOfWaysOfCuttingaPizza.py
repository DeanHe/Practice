"""
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.

Example 1:
Input: pizza = ["A..","AAA","..."], k = 3
Output: 3
Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.

Example 2:
Input: pizza = ["A..","AA.","..."], k = 3
Output: 1

Example 3:
Input: pizza = ["A..","A..","..."], k = 1
Output: 1

Constraints:
1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza consists of characters 'A' and '.' only.

hints:
1 Note that after each cut the remaining piece of pizza always has the lower right coordinate at (rows-1,cols-1).
2 Use dynamic programming approach with states (row1, col1, c) which computes the number of ways of cutting the pizza using "c" cuts where the current piece of pizza has upper left coordinate at (row1,col1) and lower right coordinate at (rows-1,cols-1).
3 For the transitions try all vertical and horizontal cuts such that the piece of pizza you have to give a person must contain at least one apple. The base case is when c=k-1.
4 Additionally use a 2D dynamic programming to respond in O(1) if a piece of pizza contains at least one apple.
"""
from functools import cache
from typing import List


class NumberOfWaysOfCuttingaPizza:
    def ways(self, pizza: List[str], k: int) -> int:
        MOD = 10 ** 9 + 7
        rows, cols = len(pizza), len(pizza[0])
        # preSum[r][c] means pizza[r:][c:] has how many apples
        prefix_sum = [[0] * (cols + 1) for _ in range(rows + 1)]
        for r in range(rows - 1, -1, -1):
            for c in range(cols - 1, -1, -1):
                prefix_sum[r][c] = (1 if pizza[r][c] == 'A' else 0) \
                                   + prefix_sum[r + 1][c] + prefix_sum[r][c + 1] - prefix_sum[r + 1][c + 1]

        @cache
        def dfs(r, c, cut):
            if prefix_sum[r][c] == 0:
                return 0
            if cut == 0:
                return 1
            res = 0
            # horizontal cut
            for nr in range(r + 1, rows):
                if prefix_sum[r][c] > prefix_sum[nr][c]:
                    res = (res + dfs(nr, c, cut - 1)) % MOD
            # vertical cut
            for nc in range(c + 1, cols):
                if prefix_sum[r][c] > prefix_sum[r][nc]:
                    res = (res + dfs(r, nc, cut - 1)) % MOD
            return res

        return dfs(0, 0, k - 1)
