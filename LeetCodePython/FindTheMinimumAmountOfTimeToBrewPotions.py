"""
You are given two integer arrays, skill and mana, of length n and m, respectively.

In a laboratory, n wizards must brew m potions in order. Each potion has a mana capacity mana[j] and must pass through all the wizards sequentially to be brewed properly. The time taken by the ith wizard on the jth potion is timeij = skill[i] * mana[j].

Since the brewing process is delicate, a potion must be passed to the next wizard immediately after the current wizard completes their work. This means the timing must be synchronized so that each wizard begins working on a potion exactly when it arrives. ​

Return the minimum amount of time required for the potions to be brewed properly.

Example 1:
Input: skill = [1,5,2,4], mana = [5,1,4,2]
Output: 110
Explanation:
Potion Number	Start time	Wizard 0 done by	Wizard 1 done by	Wizard 2 done by	Wizard 3 done by
0	0	5	30	40	60
1	52	53	58	60	64
2	54	58	78	86	102
3	86	88	98	102	110
As an example for why wizard 0 cannot start working on the 1st potion before time t = 52, consider the case where the wizards started preparing the 1st potion at time t = 50. At time t = 58, wizard 2 is done with the 1st potion, but wizard 3 will still be working on the 0th potion till time t = 60.

Example 2:
Input: skill = [1,1,1], mana = [1,1,1]
Output: 5
Explanation:
Preparation of the 0th potion begins at time t = 0, and is completed by time t = 3.
Preparation of the 1st potion begins at time t = 1, and is completed by time t = 4.
Preparation of the 2nd potion begins at time t = 2, and is completed by time t = 5.

Example 3:
Input: skill = [1,2,3,4], mana = [1,2]
Output: 21

Constraints:
n == skill.length
m == mana.length
1 <= n, m <= 5000
1 <= mana[i], skill[i] <= 5000

hints:
1 Maintain each wizard's earliest free time (for the last potion) as f[i].
2 Let x be the current mana value. Starting from now = f[0], update now = max(now + skill[i - 1] * x, f[i]) for i in [1..n]. Then, the final f[n - 1] = now + skill[n - 1] * x for this potion.
3 Update all other f values by f[i] = f[i + 1] - skill[i + 1] * x for i in [0..n - 2] (in reverse order).

analysis:
TC: O(N*M) where N is len of wizards, M is len of potions
"""
from typing import List


class FindTheMinimumAmountOfTimeToBrewPotions:
    def minTime(self, skill: List[int], mana: List[int]) -> int:
        finish_time = [0] * (len(skill) + 1)
        for i in range(len(mana)):
            for j in range(len(skill)):
                # wizard j + 1 is done with previous potion, and wizard j is done with current potion
                finish_time[j + 1] = max(finish_time[j + 1], finish_time[j]) + mana[i] * skill[j]
            # remove any wait gap between consecutive wizards
            for j in range(len(skill) - 1, -1, -1):
                finish_time[j] = finish_time[j + 1] - mana[i] * skill[j]
        return finish_time[-1]
