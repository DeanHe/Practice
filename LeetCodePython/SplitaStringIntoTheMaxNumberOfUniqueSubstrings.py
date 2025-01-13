"""
Given a string s, return the maximum number of unique substrings that the given string can be split into.

You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.

Example 2:
Input: s = "aba"
Output: 2
Explanation: One way to split maximally is ['a', 'ba'].

Example 3:
Input: s = "aa"
Output: 1
Explanation: It is impossible to split the string any further.


Constraints:
1 <= s.length <= 16
s contains only lower case English letters.

hints:
1 Use a set to keep track of which substrings have been used already
2 Try each possible substring at every position and backtrack if a complete split is not possible

analysis:
TC: O(N * 2^N)
"""

class SplitaStringIntoTheMaxNumberOfUniqueSubstrings:
    def maxUniqueSplit(self, s: str) -> int:
        seen = set()
        sz = len(s)

        def dfs(i):
            if i == sz:
                return 0
            res = 0
            for j in range(i + 1, sz + 1):
                substr = s[i:j]
                if substr not in seen:
                    seen.add(substr)
                    res = max(res, dfs(j) + 1)
                    seen.remove(substr)
            return res

        return dfs(0)
