"""
Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

Example 1:
Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

Example 2:
Input: nums = [4,4,3,2,1]
Output: [[4,4]]

Constraints:
1 <= nums.length <= 15
-100 <= nums[i] <= 100

analysis:
backtracking
TC: O(2^n * n)
SC: O(2^n * n)
"""
from typing import List


class NonDecreasingSubsequences:
    def findSubsequences(self, nums: List[int]) -> List[List[int]]:
        res = set()
        arr = []

        def dfs(idx):
            if idx == len(nums):
                if len(arr) >= 2:
                    res.add(tuple(arr))
                return
            if not arr or arr[-1] <= nums[idx]:
                arr.append(nums[idx])
                dfs(idx + 1)
                arr.pop()
            dfs(idx + 1)

        dfs(0)
        return res
