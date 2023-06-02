"""
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

Example 1:
Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.

Example 2:
Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:
Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Constraints:
1 <= s.length <= 500
s consists of lowercase English letters.
"""
from functools import cache


class MinimumInsertionStepsToMakeaStringPalindrome:
    def minInsertions(self, s: str) -> int:

        @cache
        def longestPalindromeSubseq(l, r):
            if l > r:
                return 0
            if l == r:
                return 1
            if s[l] == s[r]:
                return 2 + longestPalindromeSubseq(l + 1, r - 1)
            else:
                return max(longestPalindromeSubseq(l + 1, r), longestPalindromeSubseq(l, r - 1))

        return len(s) - longestPalindromeSubseq(0, len(s) - 1)