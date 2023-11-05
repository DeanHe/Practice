"""
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

Example 1:
Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].

Constraints:
1 <= k <= nums.length <= 10^5
-104 <= nums[i] <= 10^4

hints:
1 Use dynamic programming.
2 Let dp[i] be the solution for the prefix of the array that ends at index i, if the element at index i is in the subsequence.
3 dp[i] = nums[i] + max(0, dp[i-k], dp[i-k+1], ..., dp[i-1])
4 Use a heap with the sliding window technique to optimize the dp.

analysis:
Monotonic Deque + dp + sliding window
deque maintains monotonic decreasing of dp value
dp[i] means the max SubSet Sum with end in nums[i]
TC: O(N)
"""
import collections
from typing import List


class Solution:
    def constrainedSubsetSum(self, nums: List[int], k: int) -> int:
        res, sz = float('-inf'), len(nums)
        dp = [0] * sz
        deque = collections.deque([])
        for i in range(sz):
            dp[i] = nums[i]
            while deque and i - deque[0] > k:
                deque.popleft()
            if deque:
                dp[i] += dp[deque[0]]
            while deque and dp[deque[-1]] < dp[i]:
                deque.pop()
            if dp[i] > 0:
                deque.append(i)
            res = max(res, dp[i])
        return res

