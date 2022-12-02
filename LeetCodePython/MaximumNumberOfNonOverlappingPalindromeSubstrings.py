"""
You are given a string s and a positive integer k.

Select a set of non-overlapping substrings from the string s that satisfy the following conditions:

The length of each substring is at least k.
Each substring is a palindrome.
Return the maximum number of substrings in an optimal selection.

A substring is a contiguous sequence of characters within a string.



Example 1:
Input: s = "abaccdbbd", k = 3
Output: 2
Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.

Example 2:
Input: s = "adbcda", k = 2
Output: 0
Explanation: There is no palindrome substring of length at least 2 in the string.

Constraints:
1 <= k <= s.length <= 2000
s consists of lowercase English letters.

hints:
1 Try to use dynamic programming to solve the problem.
2 let dp[i] be the answer for the prefix s[0…i].
3 The final answer to the problem will be dp[n-1]. How do you compute this dp?

analysis:
Main catch here was to avoid picking a palindromic substring with size greater than k+1
because if there exist a palindromic substring with size greater than k+1 then it would definitely contain a palindromic substring with size at least k ( It was just basic observation )
And we are trying to minimize the length of the palindromic string ( with size >= k ) to maximize our answer.
"""
from functools import lru_cache


class MaximumNumberOfNonOverlappingPalindromeSubstrings:
    def maxPalindromes(self, s: str, k: int) -> int:

        @lru_cache(None)
        def is_pal(l, r):
            if l < 0:
                return False
            return s[l:r] == s[l:r][::-1]

        dp = [0] * (len(s) + 1)
        for i in range(k, len(s) + 1):
            dp[i] = dp[i - 1]
            if is_pal(i - k, i):
                dp[i] = max(dp[i], dp[i - k] + 1)
            if is_pal(i - k - 1, i):
                dp[i] = max(dp[i], dp[i - k - 1] + 1)
        return dp[-1]