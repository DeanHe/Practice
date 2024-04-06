"""
Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.

Example 1:
Input: s = "bcbbbcba"

Output: 4
Explanation:
The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".

Example 2:
Input: s = "aaaa"

Output: 2
Explanation:
The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".


Constraints:
2 <= s.length <= 100
s consists only of lowercase English letters.

analysis:
sliding window
TC: O(N)
"""

class MaximumLengthSubstringWithTwoOccurrences:
    def maximumLengthSubstring(self, s: str) -> int:
        res = 0
        cnt = [0] * 26
        l = 0
        for i, c in enumerate(s):
            cnt[ord(c) - ord('a')] += 1
            if cnt[ord(c) - ord('a')] > 2:
                while cnt[ord(s[l]) - ord('a')] > 2:
                    cnt[ord(s[l]) - ord('a')] -= 1
                    l += 1
            res = max(res, i - l + 1)
        return res