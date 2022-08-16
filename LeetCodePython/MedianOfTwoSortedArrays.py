"""
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
"""
from typing import List


class MedianOfTwoSortedArrays:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        n1 = len(nums1)
        n2 = len(nums2)
        n = n1 + n2

        def findKth(i, j, k):
            if i >= n1:
                return nums2[j + k - 1]
            if j >= n2:
                return nums1[i + k - 1]
            if k == 1:
                return min(nums1[i], nums2[j])
            if i + k // 2 - 1 < n1 and j + k // 2 - 1 < n2:
                a = nums1[i + k // 2 - 1]
                b = nums2[j + k // 2 - 1]
                if a < b:
                    return findKth(i + k // 2, j, k - k // 2)
                else:
                    return findKth(i, j + k // 2, k - k // 2)
            elif i + k // 2 - 1 < n1 and j + k // 2 - 1 >= n2:
                return findKth(i + k // 2, j, k - k // 2)
            else:
                return findKth(i, j + k // 2, k - k // 2)

        if n % 2 == 0:
            return (findKth(0, 0, n // 2) + findKth(0, 0, n // 2 + 1)) / 2
        else:
            return findKth(0, 0, n // 2 + 1)
