"""
You are given an integer array nums of length n.

A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices l < p < q < r such that:

nums[l...p] is strictly increasing,
nums[p...q] is strictly decreasing,
nums[q...r] is strictly increasing.
Return the maximum sum of any trionic subarray in nums.

Example 1:
Input: nums = [0,-2,-1,-3,0,2,-1]
Output: -4

Explanation:
Pick l = 1, p = 2, q = 3, r = 5:
nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1).
nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3)
nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2).
Sum = (-2) + (-1) + (-3) + 0 + 2 = -4.

Example 2:
Input: nums = [1,4,2,7]
Output: 14

Explanation:
Pick l = 0, p = 1, q = 2, r = 3:
nums[l...p] = nums[0...1] = [1, 4] is strictly increasing (1 < 4).
nums[p...q] = nums[1...2] = [4, 2] is strictly decreasing (4 > 2).
nums[q...r] = nums[2...3] = [2, 7] is strictly increasing (2 < 7).
Sum = 1 + 4 + 2 + 7 = 14.

Constraints:
4 <= n = nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
It is guaranteed that at least one trionic subarray exists.

analysis:
TC:O(N)
"""
import math
from typing import List


class TrionicArrayII:

    def isTrionic(self, nums: List[int]) -> bool:
        p = 0
        q = len(nums) - 1
        while p + 1 < len(nums):
            if nums[p] < nums[p + 1]:
                p += 1
            else:
                break
        while q - 1 >= 0:
            if nums[q - 1] < nums[q]:
                q -= 1
            else:
                break
        if p >= q or p == 0 or q == len(nums) - 1:
            return False
        for i in range(p, q):
            if nums[i] > nums[i + 1]:
                i += 1
            else:
                return False
        return True

    def maxSumTrionic(self, nums: List[int]) -> int:
        res = -math.inf
        l = 0
        while l < len(nums):
            p = l
            total = 0
            # first increasing segment:
            while p + 1 < len(nums):
                if nums[p] < nums[p + 1]:
                    p += 1
                else:
                    break
            if l == p:
                # no valid first segment:
                #print("no valid first segment", l, p)
                l += 1
                continue
            # second decreasing segment:
            q = p
            while q + 1 < len(nums):
                if nums[q] > nums[q + 1]:
                    total += nums[q]
                    q += 1
                else:
                    break
            if q == p or q == len(nums) - 1 or nums[q] == nums[q + 1]:
                # no valid second segment
                #print("no valid second segment", l, p, q)
                l = q + 1
                continue
            total += nums[q]
            # third increasing segment
            # find the max sum of third segment
            max_sum = nums[q + 1]
            cur = 0
            r = q + 1
            while r < len(nums) and nums[r - 1] < nums[r]:
                cur += nums[r]
                max_sum = max(max_sum, cur)
                r += 1
            total += max_sum
            #print(max_sum)
            # find the max sum of first segment
            max_sum = nums[p - 1]
            cur = 0
            for i in range(p - 1, l - 1, -1):
                cur += nums[i]
                max_sum = max(max_sum, cur)
            total += max_sum
            #print(l, p, q, r, max_sum)
            res = max(res, total)
            l = q
        return res



