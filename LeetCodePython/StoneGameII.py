"""
Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

Example 1:
Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.

Example 2:
Input: piles = [1,2,3,4,5,100]
Output: 104

Constraints:
1 <= piles.length <= 100
1 <= piles[i] <= 10^4
"""
from functools import cache
from typing import List


class StoneGameII:
    def stoneGameII(self, piles: List[int]) -> int:
        max_val = 10 ** 7
        n = len(piles)
        suffix_sum = piles.copy()
        for i in range(n - 2, -1, -1):
            suffix_sum[i] += suffix_sum[i + 1]

        @cache
        def dfs(i, M):
            if i == n:
                return 0
            if i + 2 * M >= n:
                return suffix_sum[i]
            nxt_player_min = max_val
            for x in range(1, 2 * M + 1):
                nxt_player_min = min(nxt_player_min, dfs(i + x, max(M, x)))
            return suffix_sum[i] - nxt_player_min

        return dfs(0, 1)