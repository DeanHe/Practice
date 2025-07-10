"""
You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].

A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.

Return the total number of good triplets.

Example 1:
Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
Output: 1
Explanation:
There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.

Example 2:
Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
Output: 4
Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).

Constraints:
n == nums1.length == nums2.length
3 <= n <= 10^5
0 <= nums1[i], nums2[i] <= n - 1
nums1 and nums2 are permutations of [0, 1, ..., n - 1].

hints:
1 For every value y, how can you find the number of values x (0 ≤ x, y ≤ n - 1) such that x appears before y in both of the arrays?
2 Similarly, for every value y, try finding the number of values z (0 ≤ y, z ≤ n - 1) such that z appears after y in both of the arrays.
3 Now, for every value y, count the number of good triplets that can be formed if y is considered as the middle element.

Analysis:
TC: O(NlogN)
binary indexed tree
"""
from typing import List

class BinaryIndexTree:
    def __init__(self, n):
        self.bit = [0] * n
        self.n = n

    def update(self, idx):
        i = idx + 1
        while i < self.n:
            self.bit[i] += 1
            i += self.lowbit(i)

    def getPreSum(self, idx):
        res = 0
        i = idx + 1
        while i > 0:
            res += self.bit[i]
            i -= self.lowbit(i)
        return res

    def lowbit(self, x):
        return x & (-x)

class CountGoodTripletsInAnArray:
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        res = 0
        n = len(nums1)
        pos = [0] * n
        left = [0] * n
        right = [0] * n
        for i in range(n):
            pos[nums2[i]] = i
        bit1 = BinaryIndexTree(n + 1)
        for i in range(n):
            idx = pos[nums1[i]]
            left[i] = bit1.getPreSum(idx - 1)
            bit1.update(idx)
        bit2 = BinaryIndexTree(n + 1)
        for i in range(n - 1, -1, -1):
            idx = pos[nums1[i]]
            right[i] = bit2.getPreSum(n - 1) - bit2.getPreSum(idx - 1)
            bit2.update(idx)
        for i in range(n):
            res += left[i] * right[i]
        return res

