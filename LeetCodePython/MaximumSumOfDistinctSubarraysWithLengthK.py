"""
You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

Example 2:
Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.


Constraints:
1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^5

hints:
1 Which elements change when moving from the subarray of size k that ends at index i to the subarray of size k that ends at index i + 1?
2 Only two elements change, the element at i + 1 is added into the subarray, and the element at i - k + 1 gets removed from the subarray.
3 Iterate through each subarray of size k and keep track of the sum of the subarray and the frequency of each element.
"""
from typing import List


class MaximumSumOfDistinctSubarraysWithLengthK:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        res = total = 0
        freq = {}
        for i, n in enumerate(nums):
            if i - k >= 0:
                start = nums[i - k]
                total -= start
                if freq[start] > 1:
                    freq[start] -= 1
                else:
                    freq.pop(start)
            total += n
            if n not in freq:
                freq[n] = 1
            else:
                freq[n] += 1
            if len(freq) == k:
                res = max(res, total)
        return res