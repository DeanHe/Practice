"""
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
"""
import collections
from typing import List


class LongestConsecutiveSequence:
    def longestConsecutive(self, nums: List[int]) -> int:
        res = 0
        num_set = set(nums)
        for n in nums:
            if n - 1 not in num_set:
                length = 0
                while n in num_set:
                    length += 1
                    n += 1
                res = max(res, length)
        return res
