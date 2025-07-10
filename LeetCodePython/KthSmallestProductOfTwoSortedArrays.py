"""
Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.

Example 1:
Input: nums1 = [2,5], nums2 = [3,4], k = 2
Output: 8
Explanation: The 2 smallest products are:
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
The 2nd smallest product is 8.

Example 2:
Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
Output: 0
Explanation: The 6 smallest products are:
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
The 6th smallest product is 0.

Example 3:
Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
Output: -6
Explanation: The 3 smallest products are:
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
The 3rd smallest product is -6.


Constraints:
1 <= nums1.length, nums2.length <= 5 * 10^4
-10^5 <= nums1[i], nums2[j] <= 10^5
1 <= k <= nums1.length * nums2.length
nums1 and nums2 are sorted.

hints:
1 Can we split this problem into four cases depending on the sign of the numbers?
2 Can we binary search the value?
"""
from typing import List


class KthSmallestProductOfTwoSortedArrays:
    def kthSmallestProduct(self, nums1: List[int], nums2: List[int], k: int) -> int:
        neg1 = [-x for x in nums1 if x < 0][::-1]
        pos1 = [x for x in nums1 if x >= 0]
        neg2 = [-x for x in nums2 if x < 0][::-1]
        pos2 = [x for x in nums2 if x >= 0]
        neg_pairs_cnt = len(neg1) * len(pos2) + len(neg2) * len(pos1)
        if k > neg_pairs_cnt:
            k -= neg_pairs_cnt
            sign = 1
        else:
            k = neg_pairs_cnt - k + 1
            neg2, pos2 = pos2, neg2
            sign = -1

        def count(A, B, target):
            res = 0
            j = len(B) - 1
            for i in range(len(A)):
                while j >= 0 and A[i] * B[j] > target:
                    j -= 1
                res += j + 1
            return res

        s, e = 0, 10 ** 10
        while s < e:
            mid = (s + e) // 2
            if count(neg1, neg2, mid) + count(pos1, pos2, mid) >= k:
                e = mid
            else:
                s = mid + 1
        return s * sign