"""
There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

Example 1:
Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".

Example 2:
Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.

Constraints:
1 <= s.length <= 100
s consists of lowercase English letters.

analysis:
DP
TC:O(N^3)
"""

class StrangePrinter:
    def strangePrinter(self, content: str) -> int:
        n = len(content)
        dp = [[0] * n for _ in range(n)]
        for i in range(0, n):
            dp[i][i] = 1
            if i + 1 < n:
                if content[i] == content[i + 1]:
                    dp[i][i + 1] = 1
                else:
                    dp[i][i + 1] = 2
        for l in range(2, n + 1):
            for s in range(n + 1 - l):
                e = s + l - 1
                dp[s][e] = l
                for i in range(s, e):
                    tmp = dp[s][i] + dp[i + 1][e]
                    if content[i] == content[e]:
                        dp[s][e] = min(dp[s][e], tmp - 1)
                    else:
                        dp[s][e] = min(dp[s][e], tmp)
        return dp[0][n - 1]