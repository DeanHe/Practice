"""
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length
"""
class SubarraysWithKDifferentIntegers(object):
    def subarraysWithKDistinct(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        return self.subarraysWithAtMostKDistinct(nums, k) - self.subarraysWithAtMostKDistinct(nums, k - 1)

    def subarraysWithAtMostKDistinct(self, nums, k):
        freq = {}
        res, s, e = 0, 0, 0
        while e < len(nums):
            freq[nums[e]] = freq.get(nums[e], 0) + 1
            while len(freq) > k:
                freq[nums[s]] -= 1
                if freq[nums[s]] == 0:
                    freq.pop(nums[s])
                s += 1
            res += e - s + 1
            e += 1
        return res