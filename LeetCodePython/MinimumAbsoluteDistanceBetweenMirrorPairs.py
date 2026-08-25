"""
You are given an integer array nums.

A mirror pair is a pair of indices (i, j) such that:

0 <= i < j < nums.length, and
reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading zeros are omitted after reversing, for example reverse(120) = 21.
Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices i and j is abs(i - j).

If no mirror pair exists, return -1.

Example 1:
Input: nums = [12,21,45,33,54]
Output: 1

Explanation:
The mirror pairs are:
(0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
(2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
The minimum absolute distance among all pairs is 1.

Example 2:
Input: nums = [120,21]
Output: 1

Explanation:
There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].
The minimum absolute distance is 1.

Example 3:
Input: nums = [21,120]
Output: -1

Explanation:
There are no mirror pairs in the array.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9

hints:
1 Scan left to right with a hash map: for each nums[i], if the map contains key nums[i] then set ans = min(ans, i - map[nums[i]]).
2 Store/update the current index under key reverse(nums[i]), so future matches use the most recent index.

analysis:
TC: O(nlogC).
We traverse the array once, and each hash table operation takes O(1) on average. Reversing a number takes O(logC) time.
"""
import math
from typing import List


class MinimumAbsoluteDistanceBetweenMirrorPairs:
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        res = math.inf
        pre = {}
        for i, num in enumerate(nums):
            if num in pre:
                res = min(res, i - pre[num])
            reverse = int(str(num)[::-1])
            pre[reverse] = i
        return res if res != math.inf else -1