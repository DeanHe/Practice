"""
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:
1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of any valid ascii character.

analysis:
TC: O(N)
"""
from collections import defaultdict


class IsomorphicStrings:
    def isIsomorphic(self, s: str, t: str) -> bool:
        sz = len(s)
        s_map = defaultdict(int)
        t_map = defaultdict(int)
        for i in range(sz):
            if s_map[s[i]] != t_map[t[i]]:
                return False
            s_map[s[i]] = i + 1
            t_map[t[i]] = i + 1
        return True