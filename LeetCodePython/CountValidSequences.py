"""
You are given two positive integers n and k.

A valid sequence is a sequence of k positive integers such that:

The sum of all integers in the sequence is equal to n.
The product of all integers in the sequence is even.

Return the number of valid sequences. Since the answer may be very large, return it modulo 10^9 + 7.

Two sequences are considered different if they differ at any index. For example, [1, 1, 2] and [1, 2, 1] are considered different sequences.

Example 1:
Input: n = 5, k = 3
Output: 3

Explanation:
The sequences of length k = 3 whose sum is 5 are:

Sequence	Product	Parity
[1, 1, 3]	1 * 1 * 3 = 3	Odd
[1, 2, 2]	1 * 2 * 2 = 4	Even
[2, 1, 2]	2 * 1 * 2 = 4	Even
[2, 2, 1]	2 * 2 * 1 = 4	Even
[1, 3, 1]	1 * 3 * 1 = 3	Odd
[3, 1, 1]	3 * 1 * 1 = 3	Odd
There are 3 sequences with an even product, thus the answer is 3.

Example 2:
Input: n = 3, k = 2
Output: 2

Explanation:
The sequences of length k = 2 whose sum is 3 are:

Sequence	Product	Parity
[1, 2]	1 * 2 = 2	Even
[2, 1]	2 * 1 = 2	Even
There are 2 sequences with an even product, thus the answer is 2.

Example 3:
Input: n = 5, k = 5
Output: 0

Explanation:
The only possible sequence of length k = 5 whose sum is 5 is [1, 1, 1, 1, 1], which has an odd product. Thus, the answer is 0.

Constraints:
1 <= n <= 5 * 10^5
1 <= k <= n

analysis:
Math, Stars and Bars
https://leetcode.com/problems/count-valid-sequences/solutions/8420704/brute-force-tle-dp-tle-o-1-math-optimised-beggar-s-method/
TC:O(1)
"""
import math
from functools import cache


class CountValidSequences:
    def countValidSequences(self, n: int, k: int) -> int:
        MOD = 10 ** 9 + 7

        @cache
        def dfs(i, groups, even):
            if i == 0 and groups == k and even:
                return 1
            if groups > k:
                return 0
            if i < 0:
                return 0
            if i < k - groups:
                return 0
            res = 0
            for j in range(i):
                next_even = even or ((i - j) % 2) == 0
                res = (res + dfs(j, groups + 1, next_even)) % MOD
            return res

        return dfs(n, 0, False)

    def countValidSequencesByMath(self, n: int, k: int) -> int:
        MOD = 10 ** 9 + 7
        total = math.comb(n - 1, k - 1) % MOD
        odd = 0
        if (n - k) % 2 == 0:
            odd = math.comb((n - k) // 2 + k - 1, k - 1) % MOD
        return (total - odd) % MOD