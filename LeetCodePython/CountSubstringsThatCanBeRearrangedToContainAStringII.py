"""
You are given two strings word1 and word2.

A string x is called valid if x can be rearranged to have word2 as a prefix.

Return the total number of valid substrings of word1.
Note that the memory limits in this problem are smaller than usual, so you must implement a solution with a linear runtime complexity.

Example 1:
Input: word1 = "bcca", word2 = "abc"
Output: 1
Explanation:

The only valid substring is "bcca" which can be rearranged to "abcc" having "abc" as a prefix.

Example 2:
Input: word1 = "abcabc", word2 = "abc"
Output: 10
Explanation:
All the substrings except substrings of size 1 and size 2 are valid.

Example 3:
Input: word1 = "abcabc", word2 = "aaabc"
Output: 0

Constraints:
1 <= word1.length <= 10^6
1 <= word2.length <= 10^4
word1 and word2 consist only of lowercase English letters.

hints:
1 Use sliding window along with two-pointer here.
2 Use constant space to store the frequency of characters.

Analysis:
sliding window, two pointers
TC: O(N)
"""
from collections import Counter


class CountSubstringsThatCanBeRearrangedToContainAStringII:
    def validSubstringCount(self, word1: str, word2: str) -> int:
        res = 0
        cnt2 = Counter(word2)
        len1 = len(word1)
        len_cnt2 = len(cnt2)
        l = 0
        for r, c in enumerate(word1):
            if c in cnt2:
                cnt2[c] -= 1
                if cnt2[c] == 0:
                    len_cnt2 -= 1
            while len_cnt2 == 0:
                res += len1 - r
                if word1[l] in cnt2:
                    cnt2[word1[l]] += 1
                    if cnt2[word1[l]] == 1:
                        len_cnt2 += 1
                l += 1
        return res

