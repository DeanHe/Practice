"""
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]

Constraints:
1 <= s.length <= 104
1 <= words.length <= 5000
1 <= words[i].length <= 30
s and words[i] consist of lowercase English letters.
"""
import collections
from typing import List


class SubstringWithConcatenationOfAllWords:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        res = []
        single_len = len(words[0])
        words_len = single_len * len(words)
        cnt = {}
        for word in words:
            cnt[word] = cnt[word] + 1 if word in cnt else 1

        def _findSubstring(start, end):
            cur = {}
            while end + single_len <= len(s):
                w = s[end: end + single_len]
                end += single_len
                if w not in cnt:
                    start = end
                    cur.clear()
                else:
                    cur[w] = cur[w] + 1 if w in cur else 1
                    while cur[w] > cnt[w]:
                        bw = s[start: start + single_len]
                        cur[bw] -= 1
                        start += single_len
                    if end - start == words_len:
                        res.append(start)

        for i in range(min(single_len, len(s) - words_len + 1)):
            _findSubstring(i, i)
        return res
