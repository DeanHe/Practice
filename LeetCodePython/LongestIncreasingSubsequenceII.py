"""
You are given an integer array nums and an integer k.

Find the longest subsequence of nums that meets the following requirements:

The subsequence is strictly increasing and
The difference between adjacent elements in the subsequence is at most k.
Return the length of the longest subsequence that meets the requirements.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
Output: 5
Explanation:
The longest subsequence that meets the requirements is [1,3,4,5,8].
The subsequence has a length of 5, so we return 5.
Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.

Example 2:
Input: nums = [7,4,5,1,8,12,4,7], k = 5
Output: 4
Explanation:
The longest subsequence that meets the requirements is [4,5,8,12].
The subsequence has a length of 4, so we return 4.

Example 3:
Input: nums = [1,5], k = 1
Output: 1
Explanation:
The longest subsequence that meets the requirements is [1].
The subsequence has a length of 1, so we return 1.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i], k <= 10^5

hint:
1 We can use dynamic programming. Let dp[i][val] be the answer using only the first i + 1 elements, and the last element in the subsequence is equal to val.
2 The only value that might change between dp[i - 1] and dp[i] are dp[i - 1][val] and dp[i][val].
3 Try using dp[i - 1] and the fact that the second last element in the subsequence has to fall within a range to calculate dp[i][val]
4 We can use a segment tree to find the maximum value in dp[i - 1] within a certain range.
"""
from typing import List

class SegmentTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * 4 * self.n

    def query(self, l, r):
        l += self.n
        r += self.n
        res = 0
        while l < r:
            if l & 1:
                res = max(res, self.tree[l])
                l += 1
            if r & 1:
                r -= 1
                res = max(res, self.tree[r])
            l >>= 1
            r >>= 1
        return res

    def update(self, i, val):
        i += self.n
        self.tree[i] = val
        while i > 1:
            i >>= 1
            self.tree[i] = max(self.tree[2 * i], self.tree[2 * i + 1])

class LongestIncreasingSubsequenceII:
    def lengthOfLIS(self, nums: List[int], k: int) -> int:
        n, res = max(nums), 1
        seg = SegmentTree(n)
        for a in nums:
            a -= 1
            pre_max = seg.query(max(0, a - k), a)
            res = max(res, pre_max + 1)
            seg.update(a, pre_max + 1)
        return res