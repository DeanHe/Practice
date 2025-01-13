"""
You are given an integer array nums and two integers indexDiff and valueDiff.

Find a pair of indices (i, j) such that:

i != j,
abs(i - j) <= indexDiff.
abs(nums[i] - nums[j]) <= valueDiff, and
Return true if such pair exists or false otherwise.

Example 1:
Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
Output: true
Explanation: We can choose (i, j) = (0, 3).
We satisfy the three conditions:
i != j --> 0 != 3
abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0

Example 2:
Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
Output: false
Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.

Constraints:
2 <= nums.length <= 10^5
-109 <= nums[i] <= 10^9
1 <= indexDiff <= nums.length
0 <= valueDiff <= 10^9

hints:
1 Time complexity O(n log k) - This will give an indication that sorting is involved for k elements.
2 Use already existing state to evaluate next state - Like, a set of k sorted numbers are only needed to be tracked. When we are processing the next number in array, then we can utilize the existing sorted state and it is not necessary to sort next overlapping set of k numbers again.

Analysis:
Buckets
"""
from typing import List


class ContainsDuplicateIII:
    def containsNearbyAlmostDuplicate(self, nums: List[int], indexDiff: int, valueDiff: int) -> bool:
        def getID(x, width):
            return x // width

        buckets = {}
        width = valueDiff + 1
        for i in range(len(nums)):
            bucket = getID(nums[i], width)
            if bucket in buckets:
                return True
            if bucket - 1 in buckets and abs(nums[i] - buckets[bucket - 1]) < width:
                return True
            if bucket + 1 in buckets and abs(buckets[bucket + 1] - nums[i]) < width:
                return True
            buckets[bucket] = nums[i]
            if i >= indexDiff:
                del buckets[getID(nums[i - indexDiff], width)]
        return False
