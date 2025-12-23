"""
A no-zero integer is a positive integer that does not contain the digit 0 in its decimal representation.

Given an integer n, count the number of pairs (a, b) where:

a and b are no-zero integers.
a + b = n
Return an integer denoting the number of such pairs.

Example 1:
Input: n = 2

Output: 1
Explanation:
The only pair is (1, 1).

Example 2:
Input: n = 3

Output: 2
Explanation:
The pairs are (1, 2) and (2, 1).

Example 3:
Input: n = 11
Output: 8

Explanation:
The pairs are (2, 9), (3, 8), (4, 7), (5, 6), (6, 5), (7, 4), (8, 3), and (9, 2). Note that (1, 10) and (10, 1) do not satisfy the conditions because 10 contains 0 in its decimal representation.

Constraints:
2 <= n <= 10^15

hints:
1 Use digit DP over the decimal representation of n.
2 At each digit, track whether a carry is present and whether a or b has used a zero so far.
3 Transition by choosing digits for a and b that add up (with carry) to the current digit of n.
4 Subtract the cases where either number ends up being zero-containing when n itself is no-zero.


Analysis:
TC:O(N^2) where N is the number of digits in n
"""
from functools import cache


class CountNoZeroPairsThatSumToN:
    def countNoZeroPairs(self, n: int) -> int:
        res = 0
        n_str = str(n)
        sz = len(n_str)
        digits = []
        for c in n_str[::-1]:
            digits.append(int(c))

        @cache
        def dfs(i, carry, len_A, len_B):
            if i == sz:
                if carry == 0:
                    return 1
                else:
                    return 0
            start_num_A = 1 if i < len_A else 0
            end_num_A = 9 if i < len_A else 0
            start_num_B = 1 if i < len_B else 0
            end_num_B = 9 if i < len_B else 0
            ways = 0
            for a in range(start_num_A, end_num_A + 1):
                for b in range(start_num_B, end_num_B + 1):
                    cur = a + b + carry
                    if cur % 10 == digits[i]:
                        ways += dfs(i + 1, cur // 10, len_A, len_B)
            return ways

        for len_A in range(1, sz + 1):
            for len_B in range(1, sz + 1):
                res += dfs(0, 0, len_A, len_B)
        return res

