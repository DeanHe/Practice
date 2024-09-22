"""
You are given two positive integer arrays nums and target, of the same length.

In a single operation, you can select any
subarray of nums and increment or decrement each element within that subarray by 1.

Return the minimum number of operations required to make nums equal to the array target.

Example 1:
Input: nums = [3,5,1,2], target = [4,6,2,4]
Output: 2
Explanation:
We will perform the following operations to make nums equal to target:
- Increment nums[0..3] by 1, nums = [4,6,2,3].
- Increment nums[3..3] by 1, nums = [4,6,2,4].

Example 2:
Input: nums = [1,3,2], target = [2,1,4]
Output: 5
Explanation:
We will perform the following operations to make nums equal to target:
- Increment nums[0..0] by 1, nums = [2,3,2].
- Decrement nums[1..1] by 1, nums = [2,2,2].
- Decrement nums[1..1] by 1, nums = [2,1,2].
- Increment nums[2..2] by 1, nums = [2,1,3].
- Increment nums[2..2] by 1, nums = [2,1,4].

Constraints:
1 <= nums.length == target.length <= 10^5
1 <= nums[i], target[i] <= 10^8

hints:
1 Change nums'[i] = nums[i] - target[i], so our goal is to make nums' into all 0s.
2 Divide and conquer.

analysis:
TC: O(N)
"""
from typing import List


class MinimumOperationsToMakeArrayEqualToTarget:
    def minimumOperations(self, nums: List[int], target: List[int]) -> int:
        res, inc, dec = 0, 0, 0
        sz = len(nums)
        for i in range(sz):
            diff = target[i] - nums[i]
            if diff == 0:
                inc = dec = 0
            elif diff > 0:
                if inc < diff:
                    res += diff - inc
                inc = diff
                dec = 0
            else:
                if diff < dec:
                    res += dec - diff
                dec = diff
                inc = 0
        return res
