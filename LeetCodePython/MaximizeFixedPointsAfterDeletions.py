"""
You are given an integer array nums.

A position i is called a fixed point if nums[i] == i.

You are allowed to delete any number of elements (including zero) from the array. After each deletion, the remaining elements shift left, and indices are reassigned starting from 0.

Return an integer denoting the maximum number of fixed points that can be achieved after performing any number of deletions.

Example 1:
Input: nums = [0,2,1]
Output: 2

Explanation:
Delete nums[1] = 2. The array becomes [0, 1].
Now, nums[0] = 0 and nums[1] = 1, so both indices are fixed points.
Thus, the answer is 2.

Example 2:
Input: nums = [3,1,2]
Output: 2

Explanation:
Do not delete any elements. The array remains [3, 1, 2].
Here, nums[1] = 1 and nums[2] = 2, so these indices are fixed points.
Thus, the answer is 2.

Example 3:
Input: nums = [1,0,1,2]
Output: 3

Explanation:
Delete nums[0] = 1. The array becomes [0, 1, 2].
Now, nums[0] = 0, nums[1] = 1, and nums[2] = 2, so all indices are fixed points.
Thus, the answer is 3.

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5

hints:
1 An element can become a fixed point only if i >= nums[i].
2 Two indices i and j can both be fixed points if they satisfy the first condition and i - nums[i] < j - nums[j].
3 Find the longest possible increasing subsequence from those indices.

analysis:
longest increasing subsequence
TC:O(NlogN)
SC:O(N)
"""
from bisect import bisect_left


class MaximizeFixedPointsAfterDeletions:
    def maxFixedPoints(self, nums: list[int]) -> int:
        transformed = []
        for i, num in enumerate(nums):
            if num <= i:
                transformed.append((i - num, num))
        transformed.sort()
        dp = []
        for _, num in transformed:
            i = bisect_left(dp, num)
            if i == len(dp):
                dp.append(num)
            else:
                dp[i] = num
        return len(dp)