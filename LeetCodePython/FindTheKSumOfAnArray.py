"""
You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and sum all of its elements together.

We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).

Return the K-Sum of the array.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Note that the empty subsequence is considered to have a sum of 0.

Example 1:
Input: nums = [2,4,-2], k = 5
Output: 2
Explanation: All the possible subsequence sums that we can obtain are the following sorted in decreasing order:
- 6, 4, 4, 2, 2, 0, 0, -2.
The 5-Sum of the array is 2.

Example 2:
Input: nums = [1,-2,3,4,-10,12], k = 16
Output: 10
Explanation: The 16-Sum of the array is 10.

Constraints:
n == nums.length
1 <= n <= 105
-109 <= nums[i] <= 109
1 <= k <= min(2000, 2n)

hint:
1 Start from the largest sum possible, and keep finding the next largest sum until you reach the kth sum.
2 Starting from a sum, what are the two next largest sums that you can obtain from it?

Analysis:
Time complexity O(NlogN + KlogK)
Space complexity O(N + K)
"""
import heapq
from typing import List


class FindTheKSumOfAnArray:
    def kSum(self, nums: List[int], k: int) -> int:
        max_sum = sum(n for n in nums if n > 0)
        pq = [(-max_sum, 0)]
        vals = sorted(abs(n) for n in nums)
        for _ in range(k):
            total, i = heapq.heappop(pq)
            if i < len(nums):
                heapq.heappush(pq, (total + vals[i], i + 1))
                if i:
                    heapq.heappush(pq, (total - vals[i - 1] + vals[i], i + 1))
        return -total