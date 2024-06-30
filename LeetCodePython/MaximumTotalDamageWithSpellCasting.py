"""
A magician has various spells.

You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.

It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.

Each spell can be cast only once.

Return the maximum possible total damage that a magician can cast.

Example 1:
Input: power = [1,1,3,4]
Output: 6
Explanation:
The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

Example 2:
Input: power = [7,1,6,6]
Output: 13
Explanation:
The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

Constraints:
1 <= power.length <= 10^5
1 <= power[i] <= 10^9

hints:
1 If we ever decide to use some spell with power x, then we will use all spells with power x.
2 Think of dynamic programming.
3 dp[i][j] represents the maximum damage considering up to the i-th unique spell and j represents the number of spells skipped (up to 3 as per constraints).

analysis:
dp[i + 1] represents the best result if power[i] was used.
keep track of the previous most dp value
"""
from typing import List


class MaximumTotalDamageWithSpellCasting:
    def maximumTotalDamage(self, power: List[int]) -> int:
        sz = len(power)
        power.sort()
        dp = [0] * (sz + 1)
        pre = 0
        pre_sum_max = 0
        for i in range(sz + 1):
            if i == 0 or power[i] == power[i + 1]:
                dp[i + 1] = dp[i] + power[i]
            else:
                while power[pre] + 2 < power[i]:
                    pre += 1
                    pre_sum_max = max(pre_sum_max, dp[pre])
                dp[i + 1] = pre_sum_max + power[i]
        return max(dp)
