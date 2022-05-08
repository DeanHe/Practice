"""
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

Example 1:
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

Example 2:
Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.


Follow up:
1 Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
2 In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
"""
import collections
from typing import List

class GameOfLife:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        live = {(r, c) for r, row in enumerate(board) for c, val in enumerate(row) if val == 1}
        live = self.gameOfLifeInfinite(live)
        for r, row in enumerate(board):
            for c in range(len(row)):
                row[c] = int((r, c) in live)

    def gameOfLifeInfinite(self, live):
        # include nearby dead cells, count of each cell's nearby live cell
        ctr = collections.Counter((nb_r, nb_c)
                                    for r, c in live
                                    for nb_r in range(r - 1, r + 2)
                                    for nb_c in range(c - 1, c + 2)
                                    if nb_r != r or nb_c != c
                                  )
        return {(r, c) for (r, c) in ctr if ctr[(r, c)] == 3 or (ctr[(r, c)] == 2 and (r, c) in live) }