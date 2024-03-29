"""
Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example 1:
Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
"dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Example 2:
Input: words = ["cat","dog","catdog"]
Output: ["catdog"]

Constraints:
1 <= words.length <= 10^4
1 <= words[i].length <= 30
words[i] consists of only lowercase English letters.
All the strings of words are unique.
1 <= sum(words[i].length) <= 10^5

analysis:
Here, N is the total number of strings in the array words, namely words.length, and M is the length of the longest string in the array words.
Time complexity: O(M ^ 3 * N)
"""
from typing import List


class Solution:
    def findAllConcatenatedWordsInADict(self, words: List[str]) -> List[str]:
        words.sort(key=lambda x: len(x))
        res = []
        visited = set()

        def can_break(w):
            if not visited:
                return False
            n = len(w)
            dp = [False] * (n + 1)
            dp[0] = True
            for i in range(1, n + 1):
                for j in range(i):
                    if dp[j] and w[j:i] in visited:
                        dp[i] = True
                        break
            return dp[-1]

        for word in words:
            if can_break(word):
                res.append(word)
            visited.add(word)
        return res

