"""
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100

hint:
1 Use dynamic programming. dp[i][j] will be the answer for inputs A[i:], B[j:].
"""
from typing import List


class MaximumLengthOfRepeatedSubarray:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        n1, n2, res = len(nums1), len(nums2), 0
        dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]
        for i in range(n1):
            for j in range(n2):
                if nums1[i] == nums2[j]:
                    dp[i + 1][j + 1] = dp[i][j] + 1
                res = max(res, dp[i + 1][j + 1])
        return res