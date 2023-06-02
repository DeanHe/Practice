"""
A string is beautiful if:

It consists of the first k letters of the English lowercase alphabet.
It does not contain any substring of length 2 or more which is a palindrome.
You are given a beautiful string s of length n and a positive integer k.

Return the lexicographically smallest string of length n, which is larger than s and is beautiful. If there is no such string, return an empty string.

A string a is lexicographically larger than a string b (of the same length) if in the first position where a and b differ, a has a character strictly larger than the corresponding character in b.

For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the fourth character, and d is greater than c.

Example 1:
Input: s = "abcz", k = 26
Output: "abda"
Explanation: The string "abda" is beautiful and lexicographically larger than the string "abcz".
It can be proven that there is no string that is lexicographically larger than the string "abcz", beautiful, and lexicographically smaller than the string "abda".

Example 2:
Input: s = "dc", k = 4
Output: ""
Explanation: It can be proven that there is no string that is lexicographically larger than the string "dc" and is beautiful.

Constraints:
1 <= n == s.length <= 10^5
4 <= k <= 26
s is a beautiful string.

hints:
1 If the string does not contain any palindromic substrings of lengths 2 and 3, then the string does not contain any palindromic substrings at all.
2 Iterate from right to left and if it is possible to increase character at index i without creating any palindromic substrings of lengths 2 and 3, then increase it.
3 After increasing the character at index i, set every character after index i equal to character a.
With this, we will ensure that we have created a lexicographically larger string than s, which does not contain any palindromes before index i and is lexicographically the smallest.
4 Finally, we are just left with a case to fix palindromic substrings, which come after index i. This can be done with a similar method mentioned in the second hint.

analysis:
TC: O(N)
SC: O(N)
"""

class LexicographicallySmallestBeautifulString:
    def smallestBeautifulString(self, s: str, k: int) -> str:
        idx = [ord(c) - ord('a') for c in s]
        i = len(idx) - 1
        while i >= 0:
            idx[i] += 1
            if idx[i] == k:
                i -= 1
            elif idx[i] not in idx[max(i - 2, 0):i]:
                for j in range(i + 1, len(idx)):
                    idx[j] = min({0, 1, 2} - set(idx[max(j - 2, 0):j]))
                return ''.join(chr(ord('a') + x) for x in idx)
        return ''