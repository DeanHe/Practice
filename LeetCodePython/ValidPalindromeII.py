"""
Given a string s, return true if the s can be palindrome after deleting at most one character from it.



Example 1:
Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false


Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
"""


class ValidPalindromeII:
    def validPalindrome(self, s: str) -> bool:
        l, r = 0, len(s) - 1

        def is_palindrome(start, end):
            while start < end:
                if s[start] != s[end]:
                    return False
                start += 1
                end -= 1
            return True

        while l <= r:
            if s[l] == s[r]:
                l += 1
                r -= 1
            else:
                return is_palindrome(l + 1, r) or is_palindrome(l, r - 1)
