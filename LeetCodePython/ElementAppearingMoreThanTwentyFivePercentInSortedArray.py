"""
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

Example 1:
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6

Example 2:
Input: arr = [1,1]
Output: 1

Constraints:
1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5

analysis:
binary search
TC: O(logN)
"""
import bisect
from typing import List


class ElementAppearingMoreThanTwentyFivePercentInSortedArray:
    def findSpecialInteger(self, arr: List[int]) -> int:
        sz = len(arr)
        target = sz / 4
        candidates = [arr[sz // 4], arr[sz // 2], arr[sz * 3 // 4]]
        for candidate in candidates:
            left = bisect.bisect_left(arr, candidate)
            right = bisect.bisect_right(arr, candidate) - 1
            if right - left + 1 > target:
                return candidate
        return -1
