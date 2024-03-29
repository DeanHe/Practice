"""
For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
Example 1:
Input: n = 3, k = 0
Output: 1
Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.

Example 2:
Input: n = 3, k = 1
Output: 2
Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.

Constraints:
1 <= n <= 1000
0 <= k <= 1000

analysis:
dp[i][j] means # of permutation of (1...i) with j reverse pairs
"""

class KInversePairsArray:
    def kInversePairs(self, n: int, k: int) -> int:
        MOD = 10 ** 9 + 7
        dp = [0] + [1] * (k + 1)
        for i in range(2, n + 1):
            tmp = [0]
            for j in range(k + 1):
                v = dp[j + 1]
                v -= dp[j - i + 1] if j >= i else 0
                tmp.append((tmp[-1] + v) % MOD)
            dp = tmp
        return (dp[k + 1] - dp[k]) % MOD