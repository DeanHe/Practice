"""
You are given three integers n, l, and r.

A ZigZag array of length n is defined as follows:

Each element lies in the range [l, r].
No two adjacent elements are equal.
No three consecutive elements form a strictly increasing or strictly decreasing sequence.
Return the total number of valid ZigZag arrays.

Since the answer may be large, return it modulo 109 + 7.

A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).

A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).

Example 1:
Input: n = 3, l = 4, r = 5
Output: 2
Explanation:
There are only 2 valid ZigZag arrays of length n = 3 using values in the range [4, 5]:
[4, 5, 4]
[5, 4, 5]

Example 2:
Input: n = 3, l = 1, r = 3
Output: 10
Explanation:
There are 10 valid ZigZag arrays of length n = 3 using values in the range [1, 3]:
[1, 2, 1], [1, 3, 1], [1, 3, 2]
[2, 1, 2], [2, 1, 3], [2, 3, 1], [2, 3, 2]
[3, 1, 2], [3, 1, 3], [3, 2, 3]
All arrays meet the ZigZag conditions.

Constraints:
3 <= n <= 2000
1 <= l < r <= 2000

hints:
1 Use dynamic programming: let dp[i][dir][x] be the count of length-i sequences ending at value x where dir is the required next comparison (0 = down, 1 = up).
2 If the required move is up (dir=1) do dp[i+1][0][y] += sum(dp[i][1][x]) for x < y; if the required move is down (dir=0) do dp[i+1][1][y] += sum(dp[i][0][x]) for x > y.
3 Speed up with prefix/suffix sums so each layer updates in O(m) instead of O(m^2); take values mod 109+7.

analysis:
With rolling array + prefix sum:
dp[v] = number of zigzag arrays ending at value v.
res = 2 * sum(dp) % MOD (factor 2 accounts for up/down symmetry).

TC: O(N)
"""

class NumberOfZigZagArraysI:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        MOD = 10 ** 9 + 7
        r -= l
        dp = [1] * (r + 1)
        for i in range(1, n):
            pre_sum = 0
            if i & 1:
                for v in range(r + 1):
                    tmp = dp[v]
                    dp[v] = pre_sum
                    pre_sum = (pre_sum + tmp) % MOD
            else:
                for v in range(r, -1, -1):
                    tmp = dp[v]
                    dp[v] = pre_sum
                    pre_sum = (pre_sum + tmp) % MOD
        return sum(dp) * 2 % MOD