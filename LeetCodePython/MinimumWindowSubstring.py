"""
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
"""
import collections


class MinimumWindowSubstring:
    def minWindow(self, s: str, t: str) -> str:
        cnt = collections.Counter(t)
        l, r, s_list, cnt_size, least, res = 0, 0, list(s), len(cnt), len(s) + 1, ''
        while r < len(s):
            sr = s_list[r]
            if sr in cnt:
                cnt[sr] -= 1
                if cnt[sr] == 0:
                    cnt_size -= 1
            r += 1
            while cnt_size == 0:
                if r - l < least:
                    least = r - l
                    res = ''.join(s_list[l:r])
                sl = s_list[l]
                if sl in cnt:
                    cnt[sl] += 1
                    if cnt[sl] == 1:
                        cnt_size += 1
                l += 1
        return res
