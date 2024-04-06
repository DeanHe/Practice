"""
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?
"""
from collections import Counter, defaultdict
from typing import List


class WordSearch:
    def exist(self, board: List[List[str]], word: str) -> bool:
        rows, cols = len(board), len(board[0])
        if len(word) > rows * cols:
            return False
        board_letters = defaultdict(int)
        for r in range(rows):
            for c in range(cols):
                board_letters[board[r][c]] += 1
        letters = Counter(word)
        for c in letters:
            if c not in board_letters or board_letters[c] < letters[c]:
                return False
        dirs = [0, 1, 0, -1, 0]

        def dfs(r, c, cnt):
            if cnt == len(word):
                return True
            if 0 <= r < rows and 0 <= c < cols and board[r][c] == word[cnt]:
                letter = board[r][c]
                board[r][c] = '#'
                res = False
                for i in range(len(dirs) - 1):
                    nb_r, nb_c = r + dirs[i], c + dirs[i + 1]
                    res |= dfs(nb_r, nb_c, cnt + 1)
                board[r][c] = letter
                return res
            return False

        for r in range(rows):
            for c in range(cols):
                if dfs(r, c, 0):
                    return True
        return False