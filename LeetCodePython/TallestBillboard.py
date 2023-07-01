"""
You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.

You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.

Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.



Example 1:

Input: rods = [1,2,3,6]
Output: 6
Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
Example 2:

Input: rods = [1,2,3,4,5,6]
Output: 10
Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
Example 3:

Input: rods = [1,2]
Output: 0
Explanation: The billboard cannot be supported, so we return 0.


Constraints:
1 <= rods.length <= 20
1 <= rods[i] <= 1000
sum(rods[i]) <= 5000

analysis:
dynamic programming
TC: O(n * m)
"""
from typing import List


class TallestBillboard:
    def tallestBillboard(self, rods: List[int]) -> int:
        # dp[taller - shorter] = taller
        dp = {0: 0}
        for r in rods:
            # dp.copy() means we don't use r
            new_dp = dp.copy()
            for diff, taller in dp.items():
                shorter = taller - diff
                # add r to the taller stand
                new_dp[diff + r] = max(new_dp.get(diff + r, 0), taller + r)
                # add r to the shorter stand
                new_diff = abs(shorter + r - taller)
                new_taller = max(shorter + r, taller)
                new_dp[new_diff] = max(new_dp.get(new_diff, 0), new_taller)
            dp = new_dp
        return dp.get(0, 0)