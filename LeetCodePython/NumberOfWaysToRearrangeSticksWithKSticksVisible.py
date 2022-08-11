"""
There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that exactly k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.

For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 109 + 7.

Example 1:
Input: n = 3, k = 2
Output: 3
Explanation: [1,3,2], [2,3,1], and [2,1,3] are the only arrangements such that exactly 2 sticks are visible.
The visible sticks are underlined.

Example 2:
Input: n = 5, k = 5
Output: 1
Explanation: [1,2,3,4,5] is the only arrangement such that all 5 sticks are visible.
The visible sticks are underlined.

Example 3:
Input: n = 20, k = 11
Output: 647427950
Explanation: There are 647427950 (mod 109 + 7) ways to rearrange the sticks such that exactly 11 sticks are visible.

Constraints:
1 <= n <= 1000
1 <= k <= n

hint:
1 Is there a way to build the solution from a base case?
2 How many ways are there if we fix the position of one stick?
"""
from functools import lru_cache


class NumberOfWaysToRearrangeSticksWithKSticksVisible:
    def rearrangeSticks(self, n: int, k: int) -> int:
        MOD = 10 ** 9 + 7

        @lru_cache(None)
        def dfs(i, j):
            if i == 0 or j == 0:
                return 0

            if i <= 2 or i == j:
                return 1

            res = 0
            res = (res + dfs(i - 1, j - 1)) % MOD
            res = (res + (i - 1) * dfs(i - 1, j)) % MOD
            return res

        return dfs(n, k)