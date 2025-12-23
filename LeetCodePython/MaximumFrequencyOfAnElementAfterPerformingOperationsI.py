"""
You are given an integer array nums and two integers k and numOperations.

You must perform an operation numOperations times on nums, where in each operation you:

Select an index i that was not selected in any previous operations.
Add an integer in the range [-k, k] to nums[i].
Return the maximum possible frequency of any element in nums after performing the operations.

Example 1:
Input: nums = [1,4,5], k = 1, numOperations = 2
Output: 2

Explanation:
We can achieve a maximum frequency of two by:
Adding 0 to nums[1]. nums becomes [1, 4, 5].
Adding -1 to nums[2]. nums becomes [1, 4, 4].

Example 2:
Input: nums = [5,11,20,20], k = 5, numOperations = 1
Output: 2

Explanation:
We can achieve a maximum frequency of two by:
Adding 0 to nums[1].


Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
0 <= k <= 10^5
0 <= numOperations <= nums.length

hints:
1 Sort the array and try each value in range as a candidate.

analysis:
Sliding window: two cases
TC:O(NlogN)
"""
import collections
from typing import List


class MaximumFrequencyOfAnElementAfterPerformingOperationsI:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        sz = len(nums)
        res = 0
        nums.sort()
        cnt = collections.Counter(nums)
        # First pass: choose existing numbers as reference points
        l = r = 0
        for i in range(sz):
            while nums[l] + k < nums[i]:
                l += 1
            while r + 1 < sz and nums[r + 1] <= nums[i] + k:
                r += 1
            total = r - l + 1
            res = max(res, min(total - cnt[nums[i]], numOperations) + cnt[nums[i]])
        # Second pass: choose a non-existent midpoint as reference
        l = 0
        for r in range(sz):
            mid = (nums[l] + nums[r]) // 2
            while nums[l] + k < mid or nums[r] > mid + k:
                l += 1
                mid = (nums[l] + nums[r]) // 2
            res = max(res, min(r - l + 1, numOperations))
        return res
