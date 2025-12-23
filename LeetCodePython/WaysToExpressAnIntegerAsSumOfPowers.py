"""
Given two positive integers n and x.

Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.

Since the result can be very large, return it modulo 109 + 7.

For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

Example 1:
Input: n = 10, x = 2
Output: 1
Explanation: We can express n as the following: n = 32 + 12 = 10.
It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

Example 2:
Input: n = 4, x = 1
Output: 2
Explanation: We can express n in the following ways:
- n = 41 = 4.
- n = 31 + 11 = 4.

Constraints:
1 <= n <= 300
1 <= x <= 5

hints:
1 You can use dynamic programming, where dp[k][j] represents the number of ways to express k as the sum of the x-th power of unique positive integers such that the biggest possible number we use is j.
2 To calculate dp[k][j], you can iterate over the numbers smaller than j and try to use each one as a power of x to make our sum k.

analysis:
DP
TC: O(N * sqrt(N))
"""

class Solution:
    def numberOfWays(self, n: int, x: int) -> int:
        MOD = 10 ** 9 + 7
        powers = []
        i = 1
        power = pow(i, x)
        while power <= n:
            powers.append(power)
            i += 1
            power = pow(i, x)
        dp = [0] * (n + 1)
        dp[0] = 1
        for power in powers:
            for i in range(n, power - 1, -1):
                dp[i] = (dp[i] + dp[i - power]) % MOD
        return dp[n]