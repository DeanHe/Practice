"""
You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.

Example 1:
Input: steps = 3, arrLen = 2
Output: 4
Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay

Example 2:
Input: steps = 2, arrLen = 4
Output: 2
Explanation: There are 2 differents ways to stay at index 0 after 2 steps
Right, Left
Stay, Stay

Example 3:
Input: steps = 4, arrLen = 2
Output: 8

Constraints:
1 <= steps <= 500
1 <= arrLen <= 10^6

hints:
1 Try with Dynamic programming, dp(pos,steps): number of ways to back pos = 0 using exactly "steps" moves.
2 Notice that the computational complexity does not depend on "arrlen".

analysis:
TC: O(len(steps) * min(len(steps), len(arrLen)))
SC: O(len(steps) * min(len(steps), len(arrLen)))
"""
from functools import cache


class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps:
    def numWays(self, steps: int, arrLen: int) -> int:
        MOD = 10 ** 9 + 7

        @cache
        def dfs(cur, remains):
            if remains == 0:
                if cur == 0:
                    return 1
                return 0
            res = dfs(cur, remains - 1)
            if cur > 0:
                res = (res + dfs(cur - 1, remains - 1)) % MOD
            if cur < arrLen - 1:
                res = (res + dfs(cur + 1, remains - 1)) % MOD
            return res

        return dfs(0, steps)