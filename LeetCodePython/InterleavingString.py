"""
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
"""


class InterleavingString:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        rows, cols, l = len(s1), len(s2), len(s3)
        if rows + cols != l:
            return False
        dp = [[False for _ in range(cols + 1)] for _ in range(rows + 1)]
        dp[0][0] = True
        for r in range(rows):
            if s1[r] == s3[r] and dp[r][0]:
                dp[r + 1][0] = True
        for c in range(cols):
            if s2[c] == s3[c] and dp[0][c]:
                dp[0][c + 1] = True
        for r in range(rows):
            for c in range(cols):
                if s1[r] == s3[r + c + 1] and dp[r][c + 1]:
                    dp[r + 1][c + 1] = True
                if s2[c] == s3[r + c + 1] and dp[r + 1][c]:
                    dp[r + 1][c + 1] = True
        return dp[rows][cols]
