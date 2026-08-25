"""
You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.

Example 1:
Input: nums = [1,3,1,1,2]
Output: [5,0,3,4,0]
Explanation:
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
When i = 4, arr[4] = 0 because there is no other index with value 2.

Example 2:
Input: nums = [0,5,3]
Output: [0,0,0]
Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9

hints:
1 Can we use the prefix sum here?
2 For each number x, collect all the indices where x occurs, and calculate the prefix sum of the array.
3 For each occurrence of x, the indices to the right will be regular subtraction while the indices to the left will be reversed subtraction.

analysis:
two sweep: left to right, then right to left
Grouping + Prefix Sum
TC: O(N)
"""
from collections import defaultdict
from typing import List


class SumOfDistances:
    def distance(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [0] * n
        groups = defaultdict(list)
        for i, num in enumerate(nums):
            groups[num].append(i)
        for indexes in groups.values():
            total = sum(indexes)
            left_sum = 0
            idx_cnt = len(indexes)
            for i in range(idx_cnt):
                right_sum = total - indexes[i] - left_sum
                left_contribution = indexes[i] * i - left_sum
                right_contribution = right_sum - indexes[i] * (idx_cnt - i - 1)
                res[indexes[i]] = left_contribution + right_contribution
                left_sum += indexes[i]
        return res

