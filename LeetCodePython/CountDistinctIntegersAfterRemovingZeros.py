"""
You are given a positive integer n.

For every integer x from 1 to n, we write down the integer obtained by removing all zeros from the decimal representation of x.

Return an integer denoting the number of distinct integers written down.

Example 1:
Input: n = 10
Output: 9
Explanation:
The integers we wrote down are 1, 2, 3, 4, 5, 6, 7, 8, 9, 1. There are 9 distinct integers (1, 2, 3, 4, 5, 6, 7, 8, 9).

Example 2:
Input: n = 3
Output: 3
Explanation:
The integers we wrote down are 1, 2, 3. There are 3 distinct integers (1, 2, 3).

Constraints:
1 <= n <= 10^15

Build integers less than or equal to n using only digits from 1 to 9
Count such integers using math or digit DP

analysis:
Digit DP
TC: O(len(str(n)))
"""
from functools import cache


class CountDistinctIntegersAfterRemovingZeros:
    def countDistinct(self, n: int) -> int:
        s = str(n)
        sz = len(s)

        @cache
        def dfs(i, bounded, begin):
            if i == sz:
                return 1
            res = 0
            max_digit = int(s[i]) if bounded else 9
            for d in range(max_digit + 1):
                if d == 0:
                    if not begin:
                        res += dfs(i + 1, bounded and d == max_digit, False)
                else:
                    res += dfs(i + 1, bounded and d == max_digit, True)
            return res

        # We subtract 1 because the DP will count '0'
        return dfs(0, True, False) - 1

