"""
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= sum(nums[i].length) <= 10^5
1 <= nums[i][j] <= 10^5

hints:
1 Notice that numbers with equal sums of row and column indexes belong to the same diagonal.
2 Store them in tuples (sum, row, val), sort them, and then regroup the answer.
"""
from collections import defaultdict, deque
from typing import List


class Solution:
    def findDiagonalOrder(self, nums: List[List[int]]) -> List[int]:
        diag = defaultdict(list)
        res = []
        for r in range(len(nums) - 1, -1, -1):
            for c in range(len(nums[r])):
                diag[r + c].append(nums[r][c])
        key = 0
        while key in diag:
            res.extend(diag[key])
        return res

    def findDiagonalOrderBFS(self, nums: List[List[int]]) -> List[int]:
        q = deque([(0, 0)])
        res = []
        while q:
            r, c = q.popleft()
            res.append(nums[r][c])
            if c == 0 and r + 1 < len(nums):
                q.append((r + 1, c))
            if c + 1 < len(nums[r]):
                q.append((r, c + 1))
        return res