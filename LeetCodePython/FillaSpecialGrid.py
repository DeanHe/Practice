"""
You are given a non-negative integer N representing a 2N x 2N grid. You must fill the grid with integers from 0 to 22N - 1 to make it special. A grid is special if it satisfies all the following conditions:

All numbers in the top-right quadrant are smaller than those in the bottom-right quadrant.
All numbers in the bottom-right quadrant are smaller than those in the bottom-left quadrant.
All numbers in the bottom-left quadrant are smaller than those in the top-left quadrant.
Each of its quadrants is also a special grid.
Return the special 2N x 2N grid.

Note: Any 1x1 grid is special.

Example 1:
Input: N = 0
Output: [[0]]
Explanation:
The only number that can be placed is 0, and there is only one possible position in the grid.

Example 2:
Input: N = 1
Output: [[3,0],[2,1]]
Explanation:
The numbers in each quadrant are:
Top-right: 0
Bottom-right: 1
Bottom-left: 2
Top-left: 3
Since 0 < 1 < 2 < 3, this satisfies the given constraints.

Example 3:
Input: N = 2
Output: [[15,12,3,0],[14,13,2,1],[11,8,7,4],[10,9,6,5]]

Explanation:
The numbers in each quadrant are:
Top-right: 3, 0, 2, 1
Bottom-right: 7, 4, 6, 5
Bottom-left: 11, 8, 10, 9
Top-left: 15, 12, 14, 13
max(3, 0, 2, 1) < min(7, 4, 6, 5)
max(7, 4, 6, 5) < min(11, 8, 10, 9)
max(11, 8, 10, 9) < min(15, 12, 14, 13)
This satisfies the first three requirements. Additionally, each quadrant is also a special grid. Thus, this is a special grid.

Constraints:
0 <= N <= 10
"""
import math
from typing import List


class Solution:
    def specialGrid(self, N: int) -> List[List[int]]:

        def dfs(s, e):
            if s == e:
                return [[s]]
            k = (e - s + 1) // 4
            top_right = dfs(s, s + k - 1)
            bot_right = dfs(s + k, s + 2 * k - 1)
            bot_left = dfs(s + 2 * k, s + 3 * k - 1)
            top_left = dfs(s + 3 * k, e)
            k = int(math.sqrt(k))
            res = []
            for r in range(k):
                row = []
                for c in range(k):
                    row.append(top_left[r][c])
                for c in range(k):
                    row.append(top_right[r][c])
                res.append(row)
            for r in range(k):
                row = []
                for c in range(k):
                    row.append(bot_left[r][c])
                for c in range(k):
                    row.append(bot_right[r][c])
                res.append(row)
            return res

        return dfs(0, (1 << 2 * N) - 1)

