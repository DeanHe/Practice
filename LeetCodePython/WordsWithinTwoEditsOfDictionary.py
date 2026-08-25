"""
You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.

In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.

Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.

Example 1:
Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
Output: ["word","note","wood"]
Explanation:
- Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
- Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
- It would take more than 2 edits for "ants" to equal a dictionary word.
- "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
Thus, we return ["word","note","wood"].

Example 2:
Input: queries = ["yes"], dictionary = ["not"]
Output: []
Explanation:
Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.


Constraints:
1 <= queries.length, dictionary.length <= 100
n == queries[i].length == dictionary[j].length
1 <= n <= 100
All queries[i] and dictionary[j] are composed of lowercase English letters.

hints:
1 Try brute-forcing the problem.
2 For each word in queries, try comparing to each word in dictionary.
3 If there is a maximum of two edit differences, the word should be present in answer.

analysis:
Trie
TC:O(len(dictionary) * avg length of word <insertion> + len(query) * (avg length of word)^2 * 26^2 <search>)
SC: O(avg length of word * len(dictionary))
"""
from typing import List


class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.isEnd = False


class WordsWithinTwoEditsOfDictionary:

    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for c in word:
            idx = ord(c) - ord('a')
            if not node.children[idx]:
                node.children[idx] = TrieNode()
            node = node.children[idx]
        node.isEnd = True

    def search(self, word, i, node, edits):
        if edits > 2 or not node:
            return False
        if i == len(word):
            return node.isEnd
        idx = ord(word[i]) - ord('a')
        # no change
        if node.children[idx] and self.search(word, i + 1, node.children[idx], edits):
            return True
        # make changes
        for j in range(26):
            if idx != j:
                if node.children[j] and self.search(word, i + 1, node.children[j], edits + 1):
                    return True
        return False

    def twoEditWords(self, queries: List[str], dictionary: List[str]) -> List[str]:
        for word in dictionary:
            self.insert(word)
        res = []
        for q in queries:
            if self.search(q, 0, self.root, 0):
                res.append(q)
        return res
