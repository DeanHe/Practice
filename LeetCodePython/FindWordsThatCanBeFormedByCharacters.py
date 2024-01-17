"""
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

Example 1:
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

Example 2:
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.


Constraints:
1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
"""
from collections import defaultdict
from typing import List


class Solution:
    def countCharacters(self, words: List[str], chars: str) -> int:
        cnt = defaultdict(int)
        for c in chars:
            cnt[c] += 1
        res = 0
        for word in words:
            word_cnt = defaultdict(int)
            for c in word:
                word_cnt[c] += 1
            good = True
            for c, freq in word_cnt.items():
                if cnt[c] < freq:
                    good = False
                    break
            if good:
                res += len(word)
        return res

