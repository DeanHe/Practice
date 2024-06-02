"""
You are given an integer array nums and two integers cost1 and cost2. You are allowed to perform either of the following operations any number of times:

Choose an index i from nums and increase nums[i] by 1 for a cost of cost1.
Choose two different indices i, j, from nums and increase nums[i] and nums[j] by 1 for a cost of cost2.
Return the minimum cost required to make all elements in the array equal.

Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [4,1], cost1 = 5, cost2 = 2
Output: 15
Explanation:
The following operations can be performed to make the values equal:
Increase nums[1] by 1 for a cost of 5. nums becomes [4,2].
Increase nums[1] by 1 for a cost of 5. nums becomes [4,3].
Increase nums[1] by 1 for a cost of 5. nums becomes [4,4].
The total cost is 15.

Example 2:
Input: nums = [2,3,3,3,5], cost1 = 2, cost2 = 1
Output: 6
Explanation:
The following operations can be performed to make the values equal:
Increase nums[0] and nums[1] by 1 for a cost of 1. nums becomes [3,4,3,3,5].
Increase nums[0] and nums[2] by 1 for a cost of 1. nums becomes [4,4,4,3,5].
Increase nums[0] and nums[3] by 1 for a cost of 1. nums becomes [5,4,4,4,5].
Increase nums[1] and nums[2] by 1 for a cost of 1. nums becomes [5,5,5,4,5].
Increase nums[3] by 1 for a cost of 2. nums becomes [5,5,5,5,5].
The total cost is 6.

Example 3:
Input: nums = [3,5,3], cost1 = 1, cost2 = 3
Output: 4
Explanation:
The following operations can be performed to make the values equal:
Increase nums[0] by 1 for a cost of 1. nums becomes [4,5,3].
Increase nums[0] by 1 for a cost of 1. nums becomes [5,5,3].
Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,4].
Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,5].
The total cost is 4.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
1 <= cost1 <= 10^6
1 <= cost2 <= 10^6

hints:
1 How can you determine the minimum cost if you know the maximum value in the array once all values are made equal?
2 If cost2 > cost1 * 2, we should just use cost1 to change all the values to the maximum one.
3 Otherwise, it's optimal to choose the smallest two values and use cost2 to increase both of them.
4 If all the values are equal, we don't need cost1, and if all but one value are equal, we need to change it using cost1.
5 Since the maximum value is known, calculate the required increases to equalize all values, instead of naively simulating the operations.
6 There are not a lot of candidates for the maximum; we can try all of them and choose which uses the minimum number of operations.
"""
from typing import List


class MinimumCostToEqualizeArray:
    def minCostToEqualizeArray(self, nums: List[int], cost1: int, cost2: int) -> int:
        sz = len(nums)
        most = max(nums)
        MOD = 10 ** 9 + 7
        max_gap = most - min(nums)
        total_gap = most * sz - sum(nums)
        if not total_gap:
            return 0
        if cost1 * 2 <= cost2:
            return total_gap * cost1 % MOD
        if max_gap * 2 <= total_gap:
        # The max_gap is less than a half of total_gap, we can always find pairs for Op 2
            res = total_gap // 2 * cost2
            if total_gap % 2 == 1:
                # Corner case with the last one spot left
                if sz % 2 == 1:
                    res += min(cost1, cost2 * (sz + 1) // 2)
                else:
                    res += cost1
            return res % MOD
