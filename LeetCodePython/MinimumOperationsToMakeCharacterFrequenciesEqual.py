"""
You are given a string s.

A string t is called good if all characters of t occur the same number of times.

You can perform the following operations any number of times:

Delete a character from s.
Insert a character in s.
Change a character in s to its next letter in the alphabet.
Note that you cannot change 'z' to 'a' using the third operation.

Return the minimum number of operations required to make s good.

Example 1:
Input: s = "acab"
Output: 1

Explanation:
We can make s good by deleting one occurrence of character 'a'.

Example 2:
Input: s = "wddw"
Output: 0

Explanation:
We do not need to perform any operations since s is initially good.

Example 3:
Input: s = "aaabc"
Output: 2

Explanation:
We can make s good by applying these operations:
Change one occurrence of 'a' to 'b'
Insert one occurrence of 'c' into s

Constraints:
3 <= s.length <= 2 * 10^4
s contains only lowercase English letters.

hints:
1 The order of the letters in the string is irrelevant.
2 Compute an occurrence array occ where occ[x] is the number of occurrences of the x character of the alphabet. How do the described operations change occ?
3 We have three types of operations: increase any occ[x] by 1, decrease any occ[x] by 1, or decrease any occ[x] by 1 and simultaneously increase occ[x + 1] by 1 at the same time. To make s good, we need to make occ good. occ is good if and only if every occ[x] equals either 0 or some constant c.
4 If you know the value of c, how can you calculate the minimum operations required to make occ good?
5 Observation 1: It is never optimal to apply the third type of operation (simultaneous decrease and increase) on two continuous elements occ[x] and occ[x + 1]. Instead, we can decrease occ[x] by 1 then increase occ[x + 2] by 1 to achieve the same effect.
6 Observation 2: It is never optimal to increase an element of occ then decrease it, or vice versa.
7 Use dynamic programming where dp[i] is the minimum number of operations required to make occ[0..i] good. You will need to use the above observations to come up with the transitions.

Analysis:
DP
"""
from functools import cache
from math import inf


class MinimumOperationsToMakeCharacterFrequenciesEqual:
    def makeStringGood(self, s: str) -> int:
        cnt = [0] * 26
        for c in s:
            cnt[ord(c) - ord('a')] += 1

        @cache
        def dfs(i, pre_char_deleted, target):
            if i == 26:
                return 0

            cur = cnt[i]
            if cur <= target:
                # remove cur
                res = dfs(i + 1, cur, target) + cur
                # insert or using change to next
                change = min(pre_char_deleted, target - cur)
                candidate = dfs(i + 1, 0, target) + target - cur - change
                res = min(res, candidate)
            else:
                # delete extra cur - target
                res = dfs(i + 1, cur - target, target) + cur - target
            return res

        res = inf
        for ops in range(max(cnt) + 1):
            res = min(res, dfs(0, 0, ops))
        return res

