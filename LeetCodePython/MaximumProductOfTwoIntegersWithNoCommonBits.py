"""
You are given an integer array nums.

Your task is to find two distinct indices i and j such that the product nums[i] * nums[j] is maximized, and the binary representations of nums[i] and nums[j] do not share any common set bits.

Return the maximum possible product of such a pair. If no such pair exists, return 0.

Example 1:
Input: nums = [1,2,3,4,5,6,7]
Output: 12
Explanation:
The best pair is 3 (011) and 4 (100). They share no set bits and 3 * 4 = 12.

Example 2:
Input: nums = [5,6,4]
Output: 0
Explanation:
Every pair of numbers has at least one common set bit. Hence, the answer is 0.

Example 3:
Input: nums = [64,8,32]
Output: 2048
Explanation:
No pair of numbers share a common bit, so the answer is the product of the two maximum elements, 64 and 32 (64 * 32 = 2048).

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^6

hints:
1 Think of each number as a mask: treat nums[i] as a bitmask.
2 Create an array dp of size B, where B is your bit‑width.
3 Initialize dp[mask] to the maximum nums[i] exactly equal to that mask, or 0 if none.
4 For each m, propagate to all its super‑masks M: dp[m] = max(dp[m], dp[M])
5 For a number x with mask m_x, compute its "complement mask" as c_m = m_x ^ ((1 << most) - 1)
6 The best disjoint partner is then dp[c_m].

Analysis:
DP
TC: O(N)
"""
from typing import List


class MaximumProductOfTwoIntegersWithNoCommonBits:
    def maxProduct(self, nums: List[int]) -> int:
        res = 0
        most = max(nums)
        n = most.bit_length()
        dp = [0] * (1 << n)
        # dp[x] means the max value for all numbers belong to sub masks of x
        for num in nums:
            dp[num] = num
        for state in range((1 << n)):
            if dp[state] == 0:
                for bit in range(n):
                    if state & (1 << bit):
                        # if state is less than its sub mask of state, update!
                        if dp[state] < dp[state ^ (1 << bit)]:
                            dp[state] = dp[state ^ (1 << bit)]
        for num in nums:
            complement = ((1 << n) - 1) ^ num
            res = max(res, num * dp[complement])
        return res
