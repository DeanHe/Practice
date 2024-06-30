"""
Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:
1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

hints:
1 After replacing each even by zero and every odd by one can we use prefix sum to find answer ?
2 Can we use two pointers to count number of sub-arrays ?
3 Can we store indices of odd numbers and for each k indices count number of sub-arrays contains them ?

analysis:
Prefix Sum
"""
from collections import defaultdict
from typing import List


class Solution:
    def numberOfSubarrays(self, nums: List[int], k: int) -> int:
        res = 0
        pre_sum_cnt = defaultdict(int)
        pre_sum_cnt[0] = 1
        cnt = 0
        for num in nums:
            cnt += 1 if num % 2 == 1 else 0
            res += pre_sum_cnt[cnt - k]
            pre_sum_cnt[cnt] += 1
        return res
