"""
You are given an integer array nums. The uniqueness array of nums is the sorted array that contains the number of distinct elements of all the
subarrays of nums. In other words, it is a sorted array consisting of distinct(nums[i..j]), for all 0 <= i <= j < nums.length.

Here, distinct(nums[i..j]) denotes the number of distinct elements in the subarray that starts at index i and ends at index j.

Return the median of the uniqueness array of nums.

Note that the median of an array is defined as the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the smaller of the two values is taken.


Example 1:
Input: nums = [1,2,3]
Output: 1
Explanation:
The uniqueness array of nums is [distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])] which is equal to [1, 1, 1, 2, 2, 3]. The uniqueness array has a median of 1. Therefore, the answer is 1.

Example 2:
Input: nums = [3,4,3,4,5]
Output: 2
Explanation:
The uniqueness array of nums is [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]. The uniqueness array has a median of 2. Therefore, the answer is 2.

Example 3:
Input: nums = [4,3,5,4]
Output: 2
Explanation:
The uniqueness array of nums is [1, 1, 1, 1, 2, 2, 2, 3, 3, 3]. The uniqueness array has a median of 2. Therefore, the answer is 2.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

hints:
1 Binary search over the answer.
2 For a given x, you need to check if x is the median, to the left of the median, or to the right of the media. You can do that by counting the number of sub-arrays nums[i…j] such that distinct(num[i…j]) <= x.
3 Use the sliding window to solve the counting problem in the hint above.

analysis:
TC: O(NlogN)
"""
from collections import Counter
from typing import List


class FindTheMedianOfTheUniquenessArray:
    def medianOfUniquenessArray(self, nums: List[int]) -> int:
        n = len(nums)
        total_subarrays = n * (n + 1) // 2

        def at_most(k):
            # number of subarrays with at most k distinct elements
            res = 0
            cnt = Counter()
            j = 0
            for i, num in enumerate(nums):
                cnt[num] += 1
                while len(cnt) > k:
                    cnt[nums[j]] -= 1
                    if cnt[nums[j]] == 0:
                        del cnt[nums[j]]
                    j += 1
                res += i - j + 1
            return res



        s, e = 1, len(set(nums))
        while s + 1 < e:
            mid = (s + e) // 2
            if at_most(mid) * 2 >= total_subarrays:
                e = mid
            else:
                s = mid
        if at_most(s) * 2 <= total_subarrays:
            return s
        return e

