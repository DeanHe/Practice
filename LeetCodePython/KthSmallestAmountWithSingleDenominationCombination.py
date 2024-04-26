"""
You are given an integer array coins representing coins of different denominations and an integer k.

You have an infinite number of coins of each denomination. However, you are not allowed to combine coins of different denominations.

Return the kth smallest amount that can be made using these coins.

Example 1:
Input: coins = [3,6,9], k = 3
Output:  9
Explanation: The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6, 9, 12, 15, etc.

Example 2:
Input: coins = [5,2], k = 7
Output: 12
Explanation: The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12, 14, 15, etc.

Constraints:
1 <= coins.length <= 15
1 <= coins[i] <= 25
1 <= k <= 2 * 10^9
coins contains pairwise distinct integers.
"""
import itertools
import math
from collections import defaultdict
from typing import List


class KthSmallestAmountWithSingleDenominationCombination:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        sz = len(coins)
        map = defaultdict(list)
        for i in range(1, sz + 1):
            for comb in itertools.combinations(coins, i):
                # the least common multiple (LCM) of the selected elements
                map[len(comb)].append(math.lcm(*comb))

        def count(target):
            res = 0
            for i in range(1, sz + 1):
                for lcm in map[i]:
                    res += target // lcm * pow(-1, i + 1)
            return res

        s = min(coins)
        e = s * k
        while s + 1 < e:
            mid = (s + e) // 2
            if count(mid) >= k:
                e = mid
            else:
                s = mid
        if count(s) >= k:
            return s
        return e