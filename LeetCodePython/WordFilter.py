"""
Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.

Example 1:
Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".


Constraints:
1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.

hint:
1 For a word like "test", consider "#test", "t#test", "st#test", "est#test", "test#test". Then if we have a query like prefix = "te", suffix = "t", we can find it by searching for something we've inserted starting with "t#te".
"""
import collections
from typing import List

class TrieNode:
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.idx = 0

class WordFilter:

    def __init__(self, words: List[str]):
        self.root = TrieNode()
        for idx, word in enumerate(words):
            pattern = word + "#" + word
            for i in range(len(word)):
                cur = self.root
                cur.idx = idx
                for j in range(i, len(pattern)):
                    cur = cur.children[pattern[j]]
                    cur.idx = idx

    def f(self, prefix: str, suffix: str) -> int:
        cur = self.root
        pattern = suffix + "#" + prefix
        for c in pattern:
            if c not in cur.children:
                return -1
            cur = cur.children[c]
        return cur.idx


# Your WordFilter object will be instantiated and called as such:
# obj = WordFilter(words)
# param_1 = obj.f(prefix,suffix)