"""
Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.

A substring is a contiguous sequence of characters within a string

Example 1:
Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.

Example 2:
Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".

Example 3:
Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.

Analysis:
suffix Trie

TC: O(M^2 * N)
Let n be the size of the words array and m be the length of the longest string in words.
"""
import collections
from typing import List

class TrieNode:
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.cnt = 0

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        cur = self.root
        for c in word:
            cur = cur.children[c]
            cur.cnt += 1

    def is_substring(self, word: str) -> bool:
        cur = self.root
        for c in word:
            cur = cur.children[c]
        return cur.cnt > 1


class StringMatchingInAnArray:
    def stringMatching(self, words: List[str]) -> List[str]:
        res = []
        trie = Trie()
        for word in words:
            for i in range(len(word)):
                trie.insert(word[i:])
        for word in words:
            if trie.is_substring(word):
                res.append(word)
        return res