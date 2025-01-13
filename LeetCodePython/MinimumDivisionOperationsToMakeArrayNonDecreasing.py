"""
You are given an integer array nums.

Any positive divisor of a natural number x that is strictly less than x is called a proper divisor of x. For example, 2 is a proper divisor of 4, while 6 is not a proper divisor of 6.

You are allowed to perform an operation any number of times on nums, where in each operation you select any one element from nums and divide it by its greatest proper divisor.

Return the minimum number of operations required to make the array non-decreasing.

If it is not possible to make the array non-decreasing using any number of operations, return -1.

Example 1:
Input: nums = [25,7]
Output: 1
Explanation:
Using a single operation, 25 gets divided by 5 and nums becomes [5, 7].

Example 2:
Input: nums = [7,7,6]
Output: -1

Example 3:
Input: nums = [1,1,1,1]
Output: 0

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6

hints:
1 Iterate backward from the last index.
2 Each number can be divided by its largest proper divisor to yield its smallest prime divisor.

analysis:
greedy
"""
from typing import List


class MinimumDivisionOperationsToMakeArrayNonDecreasing:
    def minOperations(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        def reduce_num(x, bound):
            for i in range(bound, 1, -1):
                if x % i == 0:
                    return i
            return -1

        for i in range(sz - 1, 0, -1):
            if nums[i] < nums[i - 1]:
                nums[i - 1] = reduce_num(nums[i - 1], nums[i])
                if nums[i - 1] == -1:
                    return -1
                res += 1
        return res
