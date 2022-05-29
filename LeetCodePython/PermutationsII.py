"""
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Example 1:
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

Example 2:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:
1 <= nums.length <= 8
-10 <= nums[i] <= 10
"""
from typing import List


class PermutationsII:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()
        visited = [False] * len(nums)

        def dfs(ls):
            if len(ls) == len(nums):
                res.append(ls.copy())
                return
            for i, n in enumerate(nums):
                if not visited[i]:
                    if i > 0 and nums[i] == nums[i - 1] and not visited[i - 1]:
                        continue

                    visited[i] = True
                    ls.append(n)
                    dfs(ls)
                    ls.pop()
                    visited[i] = False

        dfs([])
        return res
