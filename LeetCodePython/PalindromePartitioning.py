"""
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.
"""
from typing import List


class PalindromePartitioning:
    def partition(self, s: str) -> List[List[str]]:
        res = []

        def dfs(candidates, pos):
            if pos == len(s):
                res.append(candidates.copy())
                return
            for i in range(pos + 1, len(s) + 1):
                sub = s[pos:i]
                if sub == sub[::-1]:
                    candidates.append(sub)
                    dfs(candidates, i)
                    candidates.pop()

        dfs([], 0)
        return res