"""
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:
1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.
"""
import collections
from typing import List


class FindAllAnagramsInaString:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        p_letter_cnt = collections.Counter(p)
        start, end, p_letters, res = 0, 0, len(p_letter_cnt.keys()), []
        while end < len(s):
            if s[end] in p_letter_cnt:
                p_letter_cnt[s[end]] -= 1
                if p_letter_cnt[s[end]] == 0:
                    p_letters -= 1
            end += 1
            while p_letters == 0:
                if end - start == len(p):
                    res.append(start)
                if s[start] in p_letter_cnt:
                    p_letter_cnt[s[start]] += 1
                    if p_letter_cnt[s[start]] == 1:
                        p_letters += 1
                start += 1
        return res
