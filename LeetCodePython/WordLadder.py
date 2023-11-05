"""
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Constraints:
1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.

anlaysis:
BFS
TC: O(M^2 * N) M is the word character length, N is th wordlist length
"""
import collections
from typing import List


class WordLadder:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        wordList = set(wordList)
        queue = collections.deque([(beginWord, 1)])
        while queue:
            w, l = queue.popleft()
            if w == endWord:
                return l
            for i in range(len(w)):
                for c in 'abcdefghijklmnopqrstuvwxyz':
                    nb = w[:i] + c + w[i + 1:]
                    if nb in wordList:
                        wordList.remove(nb)
                        queue.append((nb, l + 1))
        return 0

    def ladderLengthBidirectionBFS(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        wordList = set(wordList)
        if endWord not in wordList:
            return 0
        visited = set()
        begin_set = {beginWord}
        end_set = {endWord}
        step = 1
        while begin_set and end_set:
            next_set = set()
            for w in begin_set:
                if w in end_set:
                    return step
                visited.add(w)
                for i in range(len(w)):
                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        nb = w[:i] + c + w[i + 1:]
                        if nb in wordList and nb not in visited:
                            next_set.add(nb)
            if len(end_set) < len(next_set):
                begin_set = end_set
                end_set = next_set
            else:
                begin_set = next_set
            step += 1
        return 0
