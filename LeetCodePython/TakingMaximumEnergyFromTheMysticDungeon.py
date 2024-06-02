"""
In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.

You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to magician (i + k). This process will be repeated until you reach the magician where (i + k) does not exist.

In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the magicians' sequence, absorbing all the energy during the journey.

You are given an array energy and an integer k. Return the maximum possible energy you can gain.

Example 1:
Input:  energy = [5,2,-10,-5,1], k = 3
Output: 3
Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.

Example 2:
Input: energy = [-2,-3,-1], k = 2
Output: -1
Explanation: We can gain a total energy of -1 by starting from magician 2.

Constraints:
1 <= energy.length <= 10^5
-1000 <= energy[i] <= 1000
1 <= k <= energy.length - 1

hints:
1 Let dp[i] denote the energy we gain starting from index i.
2 We can notice, that  dp[i] = dp[i + k] + energy[i].

analysis:
DP reverse loop
TC: O(N)
"""
import math
from typing import List


class TakingMaximumEnergyFromTheMysticDungeon:
    def maximumEnergy(self, energy: List[int], k: int) -> int:
        res = -math.inf
        dp = energy.copy()
        sz = len(energy)
        for i in range(sz - 1, -1 , -1):
            if i + k < sz:
                dp[i] += dp[i + k]
            res = max(res, dp[i])
        return res