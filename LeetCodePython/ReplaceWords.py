"""
In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.

Example 1:
Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Example 2:
Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"

Constraints:
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 10^6
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Every two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.

analysis:
Trie
Let d be the number of words in the dictionary,
s be the number of words in the sentence,
and w be the average length of each word.
TC: O(d⋅w+s⋅w)
"""
from typing import List

class TrieNode:
    def __init__(self):
        self.is_end = False
        self.children = [None] * 26

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        cur = self.root
        for c in word:
            idx = ord(c) - ord('a')
            if not cur.children[idx]:
                cur.children[idx] = TrieNode()
            cur = cur.children[idx]
        cur.is_end = True

    def find_shortest_root(self, word):
        cur = self.root
        for i, c in enumerate(word):
            idx = ord(c) - ord('a')
            if not cur.children[idx]:
                return word
            cur = cur.children[idx]
            if cur.is_end:
                return word[:i + 1]
        return word

class ReplaceWords:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        res = []
        words = sentence.split()
        trie = Trie()
        for root in dictionary:
            trie.insert(root)
        for word in words:
            res.append(trie.find_shortest_root(word))
        return ' '.join(res)