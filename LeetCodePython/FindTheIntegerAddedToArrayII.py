"""
You are given two integer arrays nums1 and nums2.

From nums1 two elements have been removed, and all other elements have been increased (or decreased in the case of negative) by an integer, represented by the variable x.

As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.

Return the minimum possible integer x that achieves this equivalence.

Example 1:
Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
Output: -2
Explanation:
After removing elements at indices [0,4] and adding -2, nums1 becomes [18,14,10].

Example 2:
Input: nums1 = [3,5,5,3], nums2 = [7,7]
Output: 2
Explanation:
After removing elements at indices [0,3] and adding 2, nums1 becomes [7,7].

Constraints:
3 <= nums1.length <= 200
nums2.length == nums1.length - 2
0 <= nums1[i], nums2[i] <= 1000
The test cases are generated in a way that there is an integer x such that nums1 can become equal to nums2 by removing two elements and adding x to each element of nums1.

hints:
1 Try all possibilities to remove 2 elements from nums1.
2 x should be equal to min(nums2) - min(nums1), check it naively.

TC: O(NlogN)
"""
import math
from typing import List


class FindTheIntegerAddedToArrayII:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        res = 2000
        nums1.sort()
        nums2.sort()

        def check(a, b, x):
            diff_cnt = i = 0
            for n in a:
                if i < len(b):
                    if n + x != b[i]:
                        diff_cnt += 1
                    else:
                        i += 1
            return diff_cnt <= 2


        for n in nums1:
            x = nums2[0] - n
            if check(nums1, nums2, x):
                res = min(res, x)
        return res
