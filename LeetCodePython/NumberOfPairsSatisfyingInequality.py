"""
You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
Return the number of pairs that satisfy the conditions.

Example 1:
Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
Output: 3
Explanation:
There are 3 pairs that satisfy the conditions:
1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
Therefore, we return 3.

Example 2:
Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
Output: 0
Explanation:
Since there does not exist any pair that satisfies the conditions, we return 0.


Constraints:
n == nums1.length == nums2.length
2 <= n <= 10^5
-10^4 <= nums1[i], nums2[i] <= 10^4
-10^4 <= diff <= 10^4

hints:
1 Try rearranging the equation.
2 Once the equation is rearranged properly, think how a segment tree or a Fenwick tree can be used to solve the rearranged equation.
3 Iterate through the array backwards.

analysis:
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff: equals to
nums1[i] - nums2[i] <= nums1[j]- nums2[j] + diff: equals to
c[i] <= c[j] + diff

solutions: merge sort or BIT

TC: O(N log N)
"""
from typing import List


class NumberOfPairsSatisfyingInequality:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], diff: int) -> int:
        n = len(nums1)
        nums = [nums1[i] - nums2[i] for i in range(n)]
        return self.dfs(nums, 0, n - 1, [0] * n, diff)

    def dfs(self, nums, start, end, tmp, diff):
        if start >= end:
            return 0
        mid = start + (end - start) // 2
        res = self.dfs(nums, start, mid, tmp, diff) + self.dfs(nums, mid + 1, end, tmp, diff)
        res += self.merge(nums, start, mid, end, tmp, diff)
        return res

    def merge(self, nums, start, mid, end, tmp, diff):
        res = 0
        # merge
        j = mid + 1
        for i in range(start, mid + 1):
            while j <= end and nums[i] > nums[j] + diff:
                j += 1
            res += end - j + 1
        # sort
        i, j, k = start, mid + 1, start
        while i <= mid and j <= end:
            if nums[i] < nums[j]:
                tmp[k] = nums[i]
                i += 1
            else:
                tmp[k] = nums[j]
                j += 1
            k += 1
        while j <= end:
            tmp[k] = nums[j]
            j += 1
            k += 1
        while i <= mid:
            tmp[k] = nums[i]
            i += 1
            k += 1
        for i in range(start, end + 1):
            nums[i] = tmp[i]
        return res
