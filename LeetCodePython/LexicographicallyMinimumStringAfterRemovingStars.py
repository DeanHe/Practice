"""
You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.

While there is a '*', do the following operation:

Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.

Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.

Constraints:
1 <= s.length <= 10^5
s consists only of lowercase English letters and '*'.
The input is generated such that it is possible to delete all '*' characters.
"""
import heapq


class LexicographicallyMinimumStringAfterRemovingStars:
    def clearStars(self, s: str) -> str:
        res = []
        deleted_idx = set()
        letters = []
        for i, c in enumerate(s):
            if c == '*':
                deleted_idx.add(i)
                c, j = heapq.heappop(letters)
                j = -j
                deleted_idx.add(j)
            else:
                heapq.heappush(letters, (c, -i))

        for i, c in enumerate(s):
            if i not in deleted_idx:
                res.append(c)
        return ''.join(res)