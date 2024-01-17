"""
You are given a string s that consists of lowercase English letters.

A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.

Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.

A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: s = "aaaa"
Output: 2
Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
It can be shown that the maximum length achievable is 2.

Example 2:
Input: s = "abcdef"
Output: -1
Explanation: There exists no special substring which occurs at least thrice. Hence return -1.

Example 3:
Input: s = "abcaba"
Output: 1
Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
It can be shown that the maximum length achievable is 1.


Constraints:
3 <= s.length <= 5 * 10^5
s consists of only lowercase English letters.

hints:
1 Let len[i] be the length of the longest special string ending with s[i].
2 If i > 0 and s[i] == s[i - 1], len[i] = len[i - 1] + 1. Otherwise len[i] = 1.
3 Group all the len[i] by s[i]. We have at most 26 groups.
4 The maximum value of the third largest len[i] in each group is the answer.
5 We only need to maintain the top three values for each group. You can use sorting, heap, or brute-force comparison to find the third largest value in each group.

analysis:
TC: O(N)
dp[i] is the length of longest special substring ended in s[i]
"""
import heapq
from collections import defaultdict


class Solution:
    def maximumLength(self, s: str) -> int:
        res = -1
        letter_cnt_set = defaultdict(list)
        dp = [0] * len(s)
        for i, c in enumerate(s):
            if i > 0:
                if s[i] == s[i - 1]:
                    dp[i] = dp[i - 1] + 1
                else:
                    dp[i] = 1
            else:
                dp[i] = 1
            heapq.heappush(letter_cnt_set[c], dp[i])
            if len(letter_cnt_set[c]) > 3:
                heapq.heappop(letter_cnt_set[c])
        for _, pq in letter_cnt_set.items():
            if len(pq) == 3:
                res = max(res, pq[0])
        return res
