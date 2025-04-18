"""
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper

Example 1:
Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).

Example 2:
Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).


Constraints:
1 <= nums.length <= 10^5
nums.length == n
-10^9 <= nums[i] <= 10^9
-10^9 <= lower <= upper <= 10^9

hints:
1 Sort the array in ascending order.
2 For each number in the array, keep track of the smallest and largest numbers in the array that can form a fair pair with this number.
3 As you move to larger number, both boundaries move down.

Analysis:
Binary Search
TC: O(NlogN)
"""
from bisect import bisect_left, bisect_right
from typing import List


class CountTheNumberOfFairPairs:
    def countFairPairs(self, nums: List[int], lower: int, upper: int) -> int:
        nums.sort()
        res = 0
        for i, n in enumerate(nums):
            s = bisect_left(nums, lower - n)
            e = bisect_right(nums, upper - n)
            cnt = e - s
            if cnt > 0 and s <= i < e:
                cnt -= 1
            res += cnt
        return res // 2

    def countFairPairs2(self, nums: List[int], lower: int, upper: int) -> int:
        nums.sort()

        def lower_bound(x):
            res = 0
            s, e = 0, len(nums) - 1
            while s < e:
                total = nums[s] + nums[e]
                if total < x:
                    res += e - s
                    s += 1
                else:
                    e -= 1
            return res

        return lower_bound(upper + 1) - lower_bound(lower)