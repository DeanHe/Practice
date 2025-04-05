"""
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".

Example 3:
Input: s = "abc"
Output: 1

Constraints:
3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.

hints:
1 For each position we simply need to find the first occurrence of a/b/c on or after this position.
2 So we can pre-compute three link-list of indices of each a, b, and c.

Analysis:
Sliding Window
TC: O(N)
"""

class NumberOfSubstringsContainingAllThreeCharacters:
    def numberOfSubstrings(self, s: str) -> int:
        res = 0
        l = 0
        cnt = {}
        for r in range(len(s)):
            if s[r] in cnt:
                cnt[s[r]] += 1
            else:
                cnt[s[r]] = 1
            while len(cnt) == 3:
                # count of sub-arrays contains [l:r]
                res += len(s) - r
                cnt[s[l]] -= 1
                if cnt[s[l]] == 0:
                    del cnt[s[l]]
                l += 1
        return res
