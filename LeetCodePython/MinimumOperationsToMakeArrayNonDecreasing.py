"""
You are given an integer array nums of length n.

In one operation, you may choose any subarray nums[l..r] and increase each element in that subarray by x, where x is any positive integer.

Return the minimum possible sum of the values of x across all operations required to make the array non-decreasing.

An array is non-decreasing if nums[i] <= nums[i + 1] for all 0 <= i < n - 1.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [3,3,2,1]
Output: 2

Explanation:
One optimal set of operations:

Choose subarray [2..3] and add x = 1 resulting in [3, 3, 3, 2]
Choose subarray [3..3] and add x = 1 resulting in [3, 3, 3, 3]
The array becomes non-decreasing, and the total sum of chosen x values is 1 + 1 = 2.

Example 2:
Input: nums = [5,1,2,3]
Output: 4

Explanation:
One optimal set of operations:

Choose subarray [1..3] and add x = 4 resulting in [5, 5, 6, 7]
The array becomes non-decreasing, and the total sum of chosen x values is 4.

Constraints:
1 <= n == nums.length <= 10^5
1 <= nums[i] <= 10^9

hints:
1 Focus only on positions where the array decreases (nums[i] > nums[i + 1]).
2 Each such drop must be fixed by increasing a subarray starting at i + 1.
3 Observe that you only need to pay for the difference at each drop.

analysis:
TC:O(N)
"""

class Solution:
    def minOperations(self, nums: list[int]) -> int:
        n = len(nums)
        res = 0
        for i in range(n - 1):
            if nums[i] > nums[i + 1]:
                res += nums[i] - nums[i + 1]
        return res