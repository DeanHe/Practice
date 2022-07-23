"""
Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
"""
import collections
from typing import List


class MaximumGap:
    def maximumGap(self, nums: List[int]) -> int:
        least, most, n = min(nums), max(nums), len(nums)
        if n <= 2 or least == most:
            return most - least
        bucket = collections.defaultdict(list)
        for num in nums:
            i = n - 2 if num == most else (num - least) * (n - 1) // (most - least)
            bucket[i].append(num)

        candidates = [[min(bucket[i]), max(bucket[i])] for i in range(n - 1) if bucket[i]]
        return max(y[0] - x[1] for x, y in zip(candidates, candidates[1:]))