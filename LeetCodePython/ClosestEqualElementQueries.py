"""
You are given a circular array nums and an array queries.

For each query i, you have to find the following:

The minimum distance between the element at index queries[i] and any other index j in the circular array, where nums[j] == nums[queries[i]]. If no such index exists, the answer for that query should be -1.
Return an array answer of the same size as queries, where answer[i] represents the result for query i.

Example 1:
Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
Output: [2,-1,3]
Explanation:
Query 0: The element at queries[0] = 0 is nums[0] = 1. The nearest index with the same value is 2, and the distance between them is 2.
Query 1: The element at queries[1] = 3 is nums[3] = 4. No other index contains 4, so the result is -1.
Query 2: The element at queries[2] = 5 is nums[5] = 3. The nearest index with the same value is 1, and the distance between them is 3 (following the circular path: 5 -> 6 -> 0 -> 1).

Example 2:
Input: nums = [1,2,3,4], queries = [0,1,2,3]
Output: [-1,-1,-1,-1]
Explanation:
Each value in nums is unique, so no index shares the same value as the queried element. This results in -1 for all queries.

Constraints:
1 <= queries.length <= nums.length <= 10^5
1 <= nums[i] <= 10^6
0 <= queries[i] < nums.length
"""
from collections import defaultdict
from typing import List


class ClosestEqualElementQueries:
    def solveQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        res = []
        num_idx = defaultdict(list)
        idx_pos = {}
        for i, num in enumerate(nums):
            idx_pos[i] = len(num_idx[num])
            num_idx[num].append(i)
        for q in queries:
            val = nums[q]
            j = idx_pos[q]
            pre = (j - 1 + len(num_idx[val])) % len(num_idx[val])
            nxt = (j + 1) % len(num_idx[val])
            to_pre_dist = abs(q - num_idx[val][pre])
            to_next_dist = abs(q - num_idx[val][nxt])
            min_dist = min(to_pre_dist, len(nums) - to_pre_dist, to_next_dist, len(nums) - to_next_dist)
            if min_dist == 0:
                min_dist = -1
            res.append(min_dist)
        return res
