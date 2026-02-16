"""
You are given two integer arrays nums1 and nums2 of lengths n and m respectively, and an integer k.

You must choose exactly k pairs of indices (i1, j1), (i2, j2), ..., (ik, jk) such that:

0 <= i1 < i2 < ... < ik < n
0 <= j1 < j2 < ... < jk < m
For each chosen pair (i, j), you gain a score of nums1[i] * nums2[j].

The total score is the sum of the products of all selected pairs.

Return an integer representing the maximum achievable total score.

Example 1:
Input: nums1 = [1,3,2], nums2 = [4,5,1], k = 2
Output: 22

Explanation:
One optimal choice of index pairs is:
(i1, j1) = (1, 0) which scores 3 * 4 = 12
(i2, j2) = (2, 1) which scores 2 * 5 = 10
This gives a total score of 12 + 10 = 22.

Example 2:
Input: nums1 = [-2,0,5], nums2 = [-3,4,-1,2], k = 2
Output: 26

Explanation:
One optimal choice of index pairs is:
(i1, j1) = (0, 0) which scores -2 * -3 = 6
(i2, j2) = (2, 1) which scores 5 * 4 = 20
The total score is 6 + 20 = 26.

Example 3:
Input: nums1 = [-3,-2], nums2 = [1,2], k = 2
Output: -7

Explanation:
The optimal choice of index pairs is:
(i1, j1) = (0, 0) which scores -3 * 1 = -3
(i2, j2) = (1, 1) which scores -2 * 2 = -4
The total score is -3 + (-4) = -7.

Constraints:
1 <= n == nums1.length <= 100
1 <= m == nums2.length <= 100
-10^6 <= nums1[i], nums2[i] <= 10^6
1 <= k <= min(n, m)

hints:
1 Use dynamic programming
2 Let dynamic programming states dp(i, j, k), denote the maximum k-th pair sum you can get from the first nums1[0..i] and nums2[0..j]
3 Transition: dp(i, j, t) = max(dp(i - 1, j, t), dp(i, j - 1, t), dp(i - 1, j - 1, t - 1) + nums1[i] * nums2[j])

analysis:
TC:O(len(nums1) * len(nums2) * k)
SC:O(len(nums1) * len(nums2) * k)
"""
import math
from functools import cache
from typing import List


class MaximumScoreUsingExactlyKPairs:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:

        @cache
        def dfs(i, j, kk):
            if kk == k:
                return 0
            if i == len(nums1) or j == len(nums2):
                return -math.inf
            take = nums1[i] * nums2[j] + dfs(i + 1, j + 1, kk+ 1)
            skip1 = dfs(i + 1, j, kk)
            skip2 = dfs(i, j + 1, kk)
            return max(take, skip1, skip2)

        return dfs(0, 0, 0)

    def maxScoreTopDownDP(self, nums1: List[int], nums2: List[int], k: int) -> int:
        NEG = -10**18
        dp = [[[NEG] * (k + 1) for _ in range(len(nums2) + 1)] for _ in range(len(nums1) + 1)]
        dp[0][0][0] = 0
        for i in range(len(nums1) + 1):
            for j in range(len(nums2) + 1):
                for kk in range(k + 1):
                    if i > 0:
                        dp[i][j][kk] = max(dp[i][j][kk], dp[i - 1][j][kk])
                    if j > 0:
                        dp[i][j][kk] = max(dp[i][j][kk], dp[i][j - 1][kk])
                    if i > 0 and j > 0 and kk > 0 and dp[i - 1][j - 1][kk - 1] != NEG:
                        dp[i][j][kk] = max(dp[i][j][kk], dp[i - 1][j - 1][kk - 1] + nums1[i - 1] * nums2[j - 1])
        return dp[len(nums1)][len(nums2)][k]

