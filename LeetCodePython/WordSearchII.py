"""
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
"""
import collections


class TrieNode():
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.isEnd = False

class Trie():
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for c in word:
            node = node.children[c]
        node.isEnd = True

    def search(self, word):
        node = self.root
        for c in word:
            node = node.children.get(c)
            if not node:
                return False
        return node.isEnd

class WordSearchII(object):
    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        res = []
        trie = Trie()
        node = trie.root
        for word in words:
            trie.insert(word)
        for r in range(len(board)):
            for c in range(len(board[0])):
                self.dfs(board, node, r, c, "", res)
        return res

    def dfs(self, board, cur, r, c, path, res):
        if  cur.isEnd:
            res.append(path)
            cur.isEnd = False
        if r >= 0 and r < len(board) and c >= 0 and c < len(board[0]):
            letter = board[r][c]
            cur = cur.children.get(letter)
            if not cur:
                return
            board[r][c] = "#"
            self.dfs(board, cur, r + 1, c, path + letter, res)
            self.dfs(board, cur, r - 1, c, path + letter, res)
            self.dfs(board, cur, r, c + 1, path + letter, res)
            self.dfs(board, cur, r, c - 1, path + letter, res)
            board[r][c] = letter