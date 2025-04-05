"""
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]

Constraints:
1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30

Analysis:
DFS backtrack:
TC: O(2^N)
SC: O(N)
"""
from typing import List


class CombinationSumII:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        res = []
        ls = []
        candidates.sort()

        def dfs(start, total):
            if total < 0:
                return
            if total == 0:
                res.append(ls[:])
                return
            for i in range(start, len(candidates)):
                if i == start and candidates[i] != candidates[i - 1]:
                    continue
                ls.append(candidates[i])
                dfs(i + 1, total - candidates[i])
                ls.pop()

        dfs(0, target)
        return res
