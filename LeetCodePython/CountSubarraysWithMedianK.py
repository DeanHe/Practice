"""
You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
Return the number of non-empty subarrays in nums that have a median equal to k.

Note:
The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [3,2,1,4,5], k = 4
Output: 3
Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].

Example 2:
Input: nums = [2,3,1], k = 3
Output: 1
Explanation: [3] is the only subarray that has a median equal to 3.


Constraints:

n == nums.length
1 <= n <= 10^5
1 <= nums[i], k <= n
The integers in nums are distinct.

hint:
1 Consider changing the numbers that are strictly greater than k in the array to 1, the numbers that are strictly smaller than k to -1, and k to 0.
2 After the change, what property does a subarray with median k have in the new array?
3 An array with median k should have a sum equal to either 0 or 1 in the new array and should contain the element k. How do you count such subarrays?

analysis:
prefix sum
TC: O(N)
SC: O(1)
"""
import collections
from typing import List


class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        res = bal = 0
        pre_sum_cnt = collections.defaultdict(int)
        pre_sum_cnt[0] = 1
        found = False
        for n in nums:
            if n < k:
                bal -= 1
            elif n == k:
                found = True
            else:
                bal += 1
            if found:
                res += pre_sum_cnt[bal] + pre_sum_cnt[bal - 1]
            else:
                pre_sum_cnt[bal] += 1
        return res
