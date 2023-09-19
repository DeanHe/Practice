"""
Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 10^9 + 7.

Example 1:
Input: n = 3, goal = 3, k = 1
Output: 6
Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].

Example 2:
Input: n = 2, goal = 3, k = 0
Output: 6
Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].

Example 3:
Input: n = 2, goal = 3, k = 1
Output: 2
Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].

Constraints:
0 <= k < n <= goal <= 100

analysis:
DP
TC: O(goal⋅n).
"""
from functools import cache


class NumberOfMusicPlaylists:
    def numMusicPlaylists(self, n: int, goal: int, k: int) -> int:
        MOD = 10 ** 9 + 7

        @cache
        def dfs(uniques, pick):
            if uniques == 0 and pick == 0:
                return 1
            if uniques == 0 or pick == 0:
                return 0
            res = 0
            # add a new song
            res += (dfs(uniques - 1, pick - 1) * (n - uniques + 1)) % MOD
            # add a played song
            if uniques > k:
                res += (dfs(uniques, pick - 1) * (uniques - k)) % MOD
                res %= MOD
            return res

        return dfs(n, goal)
