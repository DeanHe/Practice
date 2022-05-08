"""
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:
Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:
Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:
1 <= s.length <= 5 * 10^4
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
"""
import collections
from typing import List


class NumberOfMatchingSubsequences:
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:
        word_dict = collections.defaultdict(list)
        res = 0
        for word in words:
            word_dict[word[0]].append(word)

        for c in s:
            words_start_with_c = word_dict[c]
            word_dict[c] = []
            for word in words_start_with_c:
                if len(word) == 1:
                    res += 1
                else:
                    word_dict[word[1]].append(word[1:])
        return res
