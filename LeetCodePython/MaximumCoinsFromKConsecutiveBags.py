"""
There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.

You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci coins.

The segments that coins contain are non-overlapping.

You are also given an integer k.

Return the maximum amount of coins you can obtain by collecting k consecutive bags.

Example 1:
Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4
Output: 10
Explanation:
Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.

Example 2:
Input: coins = [[1,10,3]], k = 2
Output: 6
Explanation:
Selecting bags at positions [1, 2] gives the maximum number of coins: 3 + 3 = 6.

Constraints:
1 <= coins.length <= 10^5
1 <= k <= 10^9
coins[i] == [li, ri, ci]
1 <= li <= ri <= 10^9
1 <= ci <= 1000
The given segments are non-overlapping.

hints:
1 An optimal starting position for k consecutive bags will be either li or ri - k + 1.

Analysis:
Sliding window
"""
from typing import List


class MaximumCoinsFromKConsecutiveBags:
    def maximumCoins(self, coins: List[List[int]], k: int) -> int:

        def slide(arr):
            arr.sort()
            res = cur = j = 0
            # considering interval I = [l..l + k - 1]
            for i, (l, r, val) in enumerate(arr):
                # segment arr[j] is fully in I
                while j < len(arr) and arr[j][1] - l + 1 <= k:
                    cur += (arr[j][1] - arr[j][0] + 1) * arr[j][2]
                    j += 1
                # segment arr[j] is partial in I
                partial = 0
                if j < len(arr) and arr[j][0] - l + 1 <= k:
                    rightmost = min(l + k - 1, arr[j][1])
                    partial = (rightmost - arr[j][0] + 1) * arr[j][2]
                res = max(res, cur + partial)
                cur -= (r - l + 1) * val
            return res

        reverse = []
        for l, r, val in coins:
            reverse.append([-r, -l, val])
        return max(slide(coins), slide(reverse))
