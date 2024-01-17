"""
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")


Constraints:
3 <= s.length <= 10^5
s consists of only lowercase English letters.

hints:
1 What is the maximum number of length-3 palindromic strings?
2 How can we keep track of the characters that appeared to the left of a given position?

analysis:
TC: O(N)
"""

class UniqueLength3PalindromicSubsequences:
    def countPalindromicSubsequence(self, s: str) -> int:
        res = 0
        first = [-1] * 26
        last = [-1] * 26
        for i, c in enumerate(s):
            cur = ord(c) - ord('a')
            if first[cur] == -1:
                first[cur] = i
            last[cur] = i

        for i in range(26):
            if first[i] != -1:
                between = set()
                for j in range(first[i] + 1, last[i]):
                    between.add(s[j])
                res += len(between)

        return res
