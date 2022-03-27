"""
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.



Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
"""
import collections
from typing import List


class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit:
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        res, left = 0, 0
        max_deq = collections.deque()
        min_deq = collections.deque()
        for right, n in enumerate(nums):
            while max_deq and max_deq[-1] < n:
                max_deq.pop()
            max_deq.append(n)
            while min_deq and min_deq[-1] > n:
                min_deq.pop()
            min_deq.append(n)
            while max_deq[0] - min_deq[0] > limit:
                if nums[left] == max_deq[0]:
                    max_deq.popleft()
                if nums[left] == min_deq[0]:
                    min_deq.popleft()
                left += 1
            res = max(res, right - left + 1)
        return res
