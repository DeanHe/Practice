"""
You are given a binary array nums.

You can do the following operation on the array any number of times (possibly zero):

Choose any 3 consecutive elements from the array and flip all of them.
Flipping an element means changing its value from 0 to 1, and from 1 to 0.

Return the minimum number of operations required to make all elements in nums equal to 1. If it is impossible, return -1.

Example 1:
Input: nums = [0,1,1,1,0,0]
Output: 3
Explanation:
We can do the following operations:
Choose the elements at indices 0, 1 and 2. The resulting array is nums = [1,0,0,1,0,0].
Choose the elements at indices 1, 2 and 3. The resulting array is nums = [1,1,1,0,0,0].
Choose the elements at indices 3, 4 and 5. The resulting array is nums = [1,1,1,1,1,1].

Example 2:
Input: nums = [0,1,1,1]
Output: -1
Explanation:
It is impossible to make all elements equal to 1.

Constraints:
3 <= nums.length <= 10^5
0 <= nums[i] <= 1

hints:
1 If nums[0] is 0, then the only way to change it to 1 is by doing an operation on the first 3 elements of the array.
2 After Changing nums[0] to 1, use the same logic on the remaining array.

Analysis:
Greedy
TC: O(N)
"""
from typing import List


class MinimumOperationsToMakeBinaryArrayElementsEqualToOneI:
    def minOperations(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        for i in range(sz - 2):
            if nums[i] == 0:
                res += 1
                nums[i + 1] = 1 if nums[i + 1] == 0 else 0
                nums[i + 2] = 1 if nums[i + 2] == 0 else 0
        if nums[sz - 2] == 0 or nums[sz - 1] == 0:
            return -1
        return res
