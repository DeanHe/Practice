"""
You are given two strings s and t.

You are allowed to remove any number of characters from the string t.

The score string is 0 if no characters are removed from the string t, otherwise:

Let left be the minimum index among all removed characters.
Let right be the maximum index among all removed characters.
Then the score of the string is right - left + 1.

Return the minimum possible score to make t a subsequence of s.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abacaba", t = "bzaa"
Output: 1
Explanation: In this example, we remove the character "z" at index 1 (0-indexed).
The string t becomes "baa" which is a subsequence of the string "abacaba" and the score is 1 - 1 + 1 = 1.
It can be proven that 1 is the minimum score that we can achieve.

Example 2:
Input: s = "cde", t = "xyz"
Output: 3
Explanation: In this example, we remove characters "x", "y" and "z" at indices 0, 1, and 2 (0-indexed).
The string t becomes "" which is a subsequence of the string "cde" and the score is 2 - 0 + 1 = 3.
It can be proven that 3 is the minimum score that we can achieve.

Constraints:
1 <= s.length, t.length <= 10^5
s and t consist of only lowercase English letters.

hints:
1 Maintain two pointers: i and j. We need to perform a similar operation: while t[0:i] + t[j:n] is not a subsequence of the string s, increase j.
2 We can check the condition greedily. Create the array leftmost[i] which denotes minimum index k, such that in prefix s[0:k] exists subsequence t[0:i]. Similarly, we define rightmost[i].
3 If leftmost[i] < rightmost[j] then t[0:i] + t[j:n] is the subsequence of s.

analysis:
DP prefix and suffix LCS length track
TC: O(N)
"""

class SubsequenceWithTheMinimumScore:
    def minimumScore(self, s: str, t: str) -> int:
        suffix = [-1] * len(s)
        r = len(t) - 1
        for i in range(len(s) - 1, -1, -1):
            if 0 <= r and s[i] == t[r]:
                r -= 1
            suffix[i] = r
        res = r + 1
        l = 0
        for i, c in enumerate(s):
            res = min(res, max(0, suffix[i] - l + 1))
            # prefix calculation
            if l < len(t) and s[i] == t[l]:
                l += 1
        return min(res, len(t) - l)