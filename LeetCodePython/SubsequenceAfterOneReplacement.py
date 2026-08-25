"""
You are given two strings s and t consisting of lowercase English letters.

You may choose at most one index in s and replace the character at that index with any lowercase English letter.

Return true if it is possible to make s a subsequence of t; otherwise, return false.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

Example 1:
Input: s = "cat", t = "chat"
Output: true

Explanation:
Replace s[1] from 'a' to 'h'. The resulting string is "cht".
"cht" is a subsequence of "chat" because we can match 'c', 'h', and 't' in order.

Example 2:
Input: s = "plane", t = "apple"
Output: false

Explanation:
The characters 'p', 'l', and 'e' can be matched in t, but the remaining characters cannot be matched while preserving the required order.
Even after replacing any one character in s, it is impossible to make s a subsequence of t.

Constraints:
1 <= s.length, t.length <= 10^5
s and t consist only of lowercase English letters.

analysis:
DP, two pointers
i tracks with possible one replacement, longest subsequence of t to match s
j tracks without replacement, longest subsequence of t to match s
TC:O(N)
"""

class SubsequenceAfterOneReplacement:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        i = j = 0
        for c in t:
            if s[i] == c:
                i += 1
            i = max(i, j + 1)
            if s[j] == c:
                j += 1
            if i == len(s) or j == len(s):
                return True
        return False