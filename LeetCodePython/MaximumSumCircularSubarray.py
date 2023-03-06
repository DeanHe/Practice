"""
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

Example 1:
Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

Example 2:
Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:
Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


Constraints:
n == nums.length
1 <= n <= 3 * 10^4
-3 * 104 <= nums[i] <= 3 * 10^4

analysis:
Instead of thinking about the "special sum" as the sum of a prefix and a suffix, Calculate the "Minimum Subarray"
TC: O(N)
SC: O(1)
"""
from typing import List


class MaximumSumCircularSubarray:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        cur_max = cur_min = total = 0
        sum_max = sum_min = nums[0]
        for n in nums:
            cur_max = max(cur_max, 0) + n
            sum_max = max(sum_max, cur_max)
            cur_min = min(cur_min, 0) + n
            sum_min = min(sum_min, cur_min)
            total += n
        if total == sum_min:
            return sum_max
        else:
            return max(sum_max, total - sum_min)
