"""
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

Example 1:
Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:
Input: word1 = "leetcode", word2 = "etco"
Output: 4

Constraints:
1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
"""

class DeleteOperationForTwoStrings:
    def minDistance(self, word1: str, word2: str) -> int:

        def lcs(a, b):
            a_len, b_len = len(a), len(b)
            dp = [[0] * (b_len + 1) for _ in range(a_len + 1)]
            for i in range(a_len):
                for j in range(b_len):
                    if a[i] == b[j]:
                        dp[i + 1][j + 1] = dp[i][j] + 1
                    else:
                        dp[i + 1][j + 1] = max(dp[i + 1][j], dp[i][j + 1])
            return dp[a_len][b_len]

        return len(word1) + len(word2) - 2 * lcs(word1, word2)