"""
You are given a 1-indexed array of integers nums of length n.

We define a function greaterCount such that greaterCount(arr, val) returns the number of elements in arr that are strictly greater than val.

You need to distribute all the elements of nums between two arrays arr1 and arr2 using n operations. In the first operation, append nums[1] to arr1. In the second operation, append nums[2] to arr2. Afterwards, in the ith operation:

If greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]), append nums[i] to arr1.
If greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]), append nums[i] to arr2.
If greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]), append nums[i] to the array with a lesser number of elements.
If there is still a tie, append nums[i] to arr1.
The array result is formed by concatenating the arrays arr1 and arr2. For example, if arr1 == [1,2,3] and arr2 == [4,5,6], then result = [1,2,3,4,5,6].

Return the integer array result.

Example 1:
Input: nums = [2,1,3,3]
Output: [2,3,1,3]
Explanation: After the first 2 operations, arr1 = [2] and arr2 = [1].
In the 3rd operation, the number of elements greater than 3 is zero in both arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
In the 4th operation, the number of elements greater than 3 is zero in both arrays. As the length of arr2 is lesser, hence, append nums[4] to arr2.
After 4 operations, arr1 = [2,3] and arr2 = [1,3].
Hence, the array result formed by concatenation is [2,3,1,3].

Example 2:
Input: nums = [5,14,3,1,2]
Output: [5,3,1,2,14]
Explanation: After the first 2 operations, arr1 = [5] and arr2 = [14].
In the 3rd operation, the number of elements greater than 3 is one in both arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
In the 4th operation, the number of elements greater than 1 is greater in arr1 than arr2 (2 > 1). Hence, append nums[4] to arr1.
In the 5th operation, the number of elements greater than 2 is greater in arr1 than arr2 (2 > 1). Hence, append nums[5] to arr1.
After 5 operations, arr1 = [5,3,1,2] and arr2 = [14].
Hence, the array result formed by concatenation is [5,3,1,2,14].

Example 3:
Input: nums = [3,3,3,3]
Output: [3,3,3,3]
Explanation: At the end of 4 operations, arr1 = [3,3] and arr2 = [3,3].
Hence, the array result formed by concatenation is [3,3,3,3].

Constraints:
3 <= n <= 10^5
1 <= nums[i] <= 10^9

hints:
1 We need a data structure that counts the number of integers greater than a given value x and supports insertion.
2 Use Segment Tree or Binary Indexed Tree by compressing the numbers to the range [1,n].

analysis:
Binary Index Tree
TC:O(NlogN)
"""
import bisect
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


class DistributeElementsIntoTwoArraysII:
    def resultArray(self, nums: List[int]) -> List[int]:
        sz = len(nums)
        bit1 = BinaryIndexTree(sz + 1)
        bit2 = BinaryIndexTree(sz + 1)

        def compress(arr):
            sorted_arr = sorted(arr)
            res = [0] * len(arr)
            for i, num in enumerate(arr):
                idx = bisect.bisect_left(sorted_arr, num)
                res[i] = idx
            return res

        compressed_nums = compress(nums)
        l1 = [nums[0]]
        bit1.update(compressed_nums[0])
        l2 = [nums[1]]
        bit2.update(compressed_nums[1])
        for i in range(2, len(nums)):
            cnt_greater_than1 = bit1.getPreSum(sz - 1) - bit1.getPreSum(compressed_nums[i])
            cnt_greater_than2 = bit2.getPreSum(sz - 1) - bit2.getPreSum(compressed_nums[i])
            if cnt_greater_than1 < cnt_greater_than2:
                l2.append(nums[i])
                bit2.update(compressed_nums[i])
            elif cnt_greater_than2 < cnt_greater_than1:
                l1.append(nums[i])
                bit1.update(compressed_nums[i])
            elif len(l2) < len(l1):
                l2.append(nums[i])
                bit2.update(compressed_nums[i])
            else:
                l1.append(nums[i])
                bit1.update(compressed_nums[i])
        return l1 + l2
