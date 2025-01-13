"""
Alice and Bob are playing a fantasy battle game consisting of n rounds where they summon one of three magical creatures each round: a Fire Dragon, a Water Serpent, or an Earth Golem. In each round, players simultaneously summon their creature and are awarded points as follows:

If one player summons a Fire Dragon and the other summons an Earth Golem, the player who summoned the Fire Dragon is awarded a point.
If one player summons a Water Serpent and the other summons a Fire Dragon, the player who summoned the Water Serpent is awarded a point.
If one player summons an Earth Golem and the other summons a Water Serpent, the player who summoned the Earth Golem is awarded a point.
If both players summon the same creature, no player is awarded a point.
You are given a string s consisting of n characters 'F', 'W', and 'E', representing the sequence of creatures Alice will summon in each round:

If s[i] == 'F', Alice summons a Fire Dragon.
If s[i] == 'W', Alice summons a Water Serpent.
If s[i] == 'E', Alice summons an Earth Golem.
Bob’s sequence of moves is unknown, but it is guaranteed that Bob will never summon the same creature in two consecutive rounds. Bob beats Alice if the total number of points awarded to Bob after n rounds is strictly greater than the points awarded to Alice.
Return the number of distinct sequences Bob can use to beat Alice.
Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: s = "FFF"
Output: 3
Explanation:
Bob can beat Alice by making one of the following sequences of moves: "WFW", "FWF", or "WEW". Note that other winning sequences like "WWE" or "EWW" are invalid since Bob cannot make the same move twice in a row.

Example 2:
Input: s = "FWEFW"
Output: 18
Explanation:
Bob can beat Alice by making one of the following sequences of moves: "FWFWF", "FWFWE", "FWEFE", "FWEWE", "FEFWF", "FEFWE", "FEFEW", "FEWFE", "WFEFE", "WFEWE", "WEFWF", "WEFWE", "WEFEF", "WEFEW", "WEWFW", "WEWFE", "EWFWE", or "EWEWE".

Constraints:
1 <= s.length <= 1000
s[i] is one of 'F', 'W', or 'E'.

hints:
1 Use dynamic programming.
2 For 0 < i < n - 1, -n < j < n, and k in {’F’, ‘W’, ‘E’}, let dp[i][j][k] be the number of sequences consisting of the first i moves such that the difference between bob’s points and alice’s point is equal to j and the ith move that Bob played is k.
3 The answer is the sum of dp[n - 1][j][k]over all j > 0 and over all k.
"""
from functools import cache


class CountTheNumberOfWinningSequences:
    def countWinningSequences(self, s: str) -> int:
        MOD = 10 ** 9 + 7
        sz = len(s)

        @cache
        def dfs(last, i, total):
            if i == sz:
                if total > 0:
                    return 1
                return 0
            if total < -sz or total > sz:
                return 0
            res = 0
            # 1:F, 2:W 3:E
            if last != 1:
                points = total
                if s[i] == 'E':
                    points = total + 1
                elif s[i] == 'W':
                    points = total - 1
                res = (res + dfs(1, i + 1, points)) % MOD
            if last != 2:
                points = total
                if s[i] == 'F':
                    points = total + 1
                elif s[i] == 'E':
                    points = total - 1
                res = (res + dfs(2, i + 1, points)) % MOD
            if last != 3:
                points = total
                if s[i] == 'W':
                    points = total + 1
                elif s[i] == 'F':
                    points = total - 1
                res = (res + dfs(3, i + 1, points)) % MOD
            return res

        return dfs(0, 0, 0)


