"""
Given an unsorted integer array, where same integers are grouped together, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

Example 1:
Input: arr = [1,6,6,6,6,2,2,7,10]
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

tag:Facebook
"""
import bisect
from typing import List


class ElementAppearingMoreThanTwentyFivePercentInArray:
    def findSpecialInteger(self, arr: List[int]) -> int:
        sz = len(arr)
        roundup = (sz + 3) // 4
        candidates = [sz // 4, sz // 2, sz * 3 // 4]
        def find_bound(start, end, target, left_bound):
            s, e = start, end
            while s + 1 < e:
                mid = (s + e) // 2
                if arr[mid] == target:
                    if left_bound:
                        e = mid
                    else:
                        s = mid
                else:
                    if left_bound:
                        s = mid
                    else:
                        e = mid
            if left_bound:
                if arr[s] == target:
                    return s
                return e
            else:
                if arr[e] == target:
                    return e
                return s

        for i in candidates:
            left = find_bound(max(0, i - roundup), i, arr[i], True)
            right = find_bound(i, min(sz - 1, i + roundup), arr[i], False)
            if right - left + 1 > sz / 4:
                return arr[i]
        return -1
