"""
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example 1:
Input: n = 13
Output: 6

Example 2:
Input: n = 0
Output: 0

Constraints:
0 <= n <= 10^9

hints:
1 Beware of overflow.

analysis:
Digit Dynamic Program
"""
from functools import cache


class NumberOfDigitOne:
    def countDigitOne(self, n: int) -> int:
        num_str = str(n)
        sz = len(num_str)

        @cache
        def dfs(i, limited, ones_digits_in_num):
            if i == sz:
                return ones_digits_in_num
            res = 0
            max_digit = int(num_str[i]) if limited else 9
            for d in range(max_digit + 1):
                next_limited = limited and d == int(num_str[i])
                res += dfs(i + 1, next_limited, ones_digits_in_num + (1 if d == 1 else 0))
            return res

        return dfs(0, True, 0)



