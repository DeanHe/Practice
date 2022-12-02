"""
Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

Example 1:
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.

Example 2:
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false

Example 3:
Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true


Constraints:
1 <= word1.length, word2.length <= 10^3
1 <= word1[i].length, word2[i].length <= 10^3
1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
word1[i] and word2[i] consist of lowercase letters.

hints:
1 Concatenate all strings in the first array into a single string in the given order, the same for the second array.
2 Both arrays represent the same string if and only if the generated strings are the same.

analysis:
TC: O(N)
SC: O(1) with generator
"""
from typing import List


class Solution:
    def arrayStringsAreEqual(self, word1: List[str], word2: List[str]) -> bool:
        def generate(word_list):
            for word in word_list:
                for c in word:
                    yield c
            yield None

        for c1, c2 in zip(generate(word1), generate(word2)):
            if c1 != c2:
                return False
        return True