"""
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]


Constraints:
1 <= nums.length <= 5 * 10^4
-109 <= nums[i] <= 10^9

Follow up: Could you solve the problem in linear time and in O(1) space?
"""
from typing import List


class MajorityElementII:
    def majorityElement(self, nums: List[int]) -> List[int]:
        if not nums:
            return []
        cnt1, cnt2, cand1, cand2 = 0, 0, 0, 1
        for n in nums:
            if n == cand1:
                cnt1 += 1
            elif n == cand2:
                cnt2 += 1
            elif cnt1 == 0:
                cand1, cnt1 = n, 1
            elif cnt2 == 0:
                cand2, cnt2 = n, 1
            else:
                cnt1 -= 1
                cnt2 -= 1
        return[n for n in (cand1, cand2) if nums.count(n) > len(nums) // 3]