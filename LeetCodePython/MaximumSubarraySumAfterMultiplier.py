"""
You are given an integer array nums and a positive integer k.

You must choose exactly one subarray of nums and perform exactly one of the following operations:

Multiply each number in the chosen subarray by k.
Divide each number in the chosen subarray by k.
When dividing a positive number by k, use the floor value of the division result.
When dividing a negative number by k, use the ceiling value of the division result.
Return the maximum possible sum of a non-empty subarray in the resulting array.

Note that the subarray chosen for the operation and the subarray chosen for the sum may be different.

Example 1:
Input: nums = [1,-2,3,4,-5], k = 2
Output: 14

Explanation:
Multiply each number in the subarray [3, 4] by 2.
This results in nums = [1, -2, 6, 8, -5].
The subarray with the largest sum is [6, 8], so the output is 6 + 8 = 14.

Example 2:
Input: nums = [-5,-4,-3], k = 2
Output: -1

Explanation:
Divide each number in the subarray [-3] by 2.
This results in nums = [-5, -4, -1].
The subarray with the largest sum is [-1], so the output is -1.

Constraints:
1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
1 <= k <= 10^5

hints:
1 Use a Kadane-style dynamic programming where the maximum-sum subarray is built from left to right.
2 Keep states for four cases: no operation used yet, currently multiplying, currently dividing, and operation already finished.
3 The divide value should be computed as truncation toward zero: floor for positive numbers and ceiling for negative numbers.
4 The answer is the maximum value over all states and all ending positions.

analysis:
DP
TC:O(N)
"""
import math
from typing import List


class MaximumSubarraySumAfterMultiplier:
    def maxSubarraySum(self, nums: List[int], k: int) -> int:
        res = dp_no_ops = dp_multiply = dp_divide = dp_after_ops = -math.inf
        for num in nums:
            multi, div = num * k, int(num / k)
            dp_after_ops = max(dp_after_ops + num, dp_multiply + num, dp_divide + num)
            dp_multiply = max(dp_no_ops + multi, dp_multiply + multi, multi)
            dp_divide = max(dp_no_ops + div, dp_divide + div, div)
            dp_no_ops = max(dp_no_ops + num, num)
            res = max(res, dp_no_ops, dp_multiply, dp_divide, dp_after_ops)
        return res