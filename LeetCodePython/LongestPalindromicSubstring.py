"""
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.

hints:
1 How can we reuse a previously computed palindrome to compute a larger palindrome?
2 If “aba” is a palindrome, is “xabax” a palindrome? Similarly is “xabay” a palindrome?
3 Complexity based hint: If we use brute-force and check whether for every start and end position a substring is a palindrome we have O(n^2) start - end pairs and O(n) palindromic checks.
Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.

analysis:
TC: O(N^2)
"""
class LongestPalindromicSubstring:
    def longestPalindrome(self, s: str) -> str:
        self.max_len, self.start = 0, 0
        if len(s) < 2:
            return s
        for i in range(len(s)):
            self.expand(s, i, i)
            self.expand(s, i, i + 1)
        return s[self.start: self.start + self.max_len]

    def expand(self, s, l, r):
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1
            r += 1
        if r - l - 1 >= self.max_len:
            self.max_len = r - l - 1
            self.start = l + 1