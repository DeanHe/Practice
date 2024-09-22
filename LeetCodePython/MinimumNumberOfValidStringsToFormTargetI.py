"""
You are given an array of strings words and a string target.

A string x is called valid if x is a
prefix
 of any string in words.

Return the minimum number of valid strings that can be concatenated to form target. If it is not possible to form target, return -1.

A prefix of a string is a substring that starts from the beginning of the string and extends to any point within it.

Example 1:
Input: words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
Output: 3
Explanation:
The target string can be formed by concatenating:
Prefix of length 2 of words[1], i.e. "aa".
Prefix of length 3 of words[2], i.e. "bcd".
Prefix of length 3 of words[0], i.e. "abc".

Example 2:
Input: words = ["abababab","ab"], target = "ababaababa"
Output: 2
Explanation:
The target string can be formed by concatenating:
Prefix of length 5 of words[0], i.e. "ababa".
Prefix of length 5 of words[0], i.e. "ababa".

Example 3:
Input: words = ["abcdef"], target = "xyz"
Output: -1

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 5 * 10^3
The input is generated such that sum(words[i].length) <= 10^5.
words[i] consists only of lowercase English letters.
1 <= target.length <= 5 * 10^3
target consists only of lowercase English letters.

hints:
1 Let dp[i] be the minimum cost to form the prefix of length i of target.
2 If target[(i + 1)..j] matches any prefix, update the range dp[(i + 1)..j] to minimum between original value and dp[i] + 1.
3 Use a Trie to check prefix matching.

analysis:
O(N * max(len(words[i])))
"""
import collections
import math
from typing import List

class TrieNode:
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.is_word = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        cur = self.root
        for c in word:
            cur = cur.children[c]
        cur.is_word = True

    def search_prefix_lengths(self, word: str, start: int) -> [int]:
        res = []
        cur = self.root
        for i in range(start, len(word)):
            c = word[i]
            cur = cur.children.get(c)
            if cur is None:
                break
            else:
                res.append(i - start + 1)
        return res

class MinimumNumberOfValidStringsToFormTargetI:
    def minValidStrings(self, words: List[str], target: str) -> int:
        trie = Trie()
        for word in words:
            trie.insert(word)
        dp = [math.inf] * (len(target) + 1)
        dp[0] = 0
        for i in range(len(target)):
            if not dp[i] == math.inf:
                prefix_lengths = trie.search_prefix_lengths(target, i)
                for l in prefix_lengths:
                    dp[i + l] = min(dp[i + l], dp[i] + 1)
        return dp[len(target)] if dp[len(target)] != math.inf else -1
