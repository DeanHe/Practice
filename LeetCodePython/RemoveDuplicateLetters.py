"""
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"


Constraints:
1 <= s.length <= 10^4
s consists of lowercase English letters.
"""


class RemoveDuplicateLetters:
    def removeDuplicateLetters(self, s: str) -> str:
        res, last_idx, visited = [], {}, set()
        for i, c in enumerate(s):
            last_idx[c] = i
        for i, c in enumerate(s):
            if c not in visited:
                while res and c < res[-1] and i < last_idx[res[-1]]:
                    end_c = res.pop()
                    visited.discard(end_c)
                res.append(c)
                visited.add(c)
        return ''.join(res)
