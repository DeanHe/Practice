"""
You are given two arrays of strings wordsContainer and wordsQuery.

For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is the smallest in length. If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.

Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].

Example 1:
Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
Output: [1,1,1]

Explanation:
Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "cd", strings from wordsContainer that share the longest common suffix "cd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[1] = "bcd", strings from wordsContainer that share the longest common suffix "bcd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[2] = "xyz", there is no string from wordsContainer that shares a common suffix. Hence the longest common suffix is "", that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.

Example 2:
Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]
Output: [2,0,2]

Explanation:
Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "gh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.
For wordsQuery[1] = "acbfgh", only the string at index 0 shares the longest common suffix "fgh". Hence it is the answer, even though the string at index 2 is shorter.
For wordsQuery[2] = "acbfegh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.


Constraints:
1 <= wordsContainer.length, wordsQuery.length <= 10^4
1 <= wordsContainer[i].length <= 5 * 10^3
1 <= wordsQuery[i].length <= 5 * 10^3
wordsContainer[i] consists only of lowercase English letters.
wordsQuery[i] consists only of lowercase English letters.
Sum of wordsContainer[i].length is at most 5 * 10^5.
Sum of wordsQuery[i].length is at most 5 * 10^5.

hints:
1 If we reverse the strings, the problem changes to finding the longest common prefix.
2 Build a Trie, each node is a letter and only saves the best word’s index in each node, based on the criteria.
"""
from typing import List


class LongestCommonSuffixQueries:
    def stringIndices(self, wordsContainer: List[str], wordsQuery: List[str]) -> List[int]:
        res = []
        root = TrieNode(0)

        def insert(idx):
            cur = root
            for c in wordsContainer[idx][::-1]:
                j = ord(c) - ord('a')
                if not cur.children[j]:
                    cur.children[j] = TrieNode(idx)
                cur = cur.children[j]
                if len(wordsContainer[idx]) < len(wordsContainer[cur.index]):
                    cur.index = idx

        def search(word):
            cur = root
            res = cur.index
            for c in word[::-1]:
                j = ord(c) - ord('a')
                cur = cur.children[j]
                if not cur:
                    return res
                res = cur.index
            return res

        for i in range(len(wordsContainer)):
            if len(wordsContainer[i]) < len(wordsContainer[root.index]):
                root.index = i
            insert(i)
        for w in wordsQuery:
            res.append(search(w))
        return res


class TrieNode:
    def __init__(self, idx):
        self.children = [None] * 26
        self.index = idx
