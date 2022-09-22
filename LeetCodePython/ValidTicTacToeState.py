"""
Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares ' '.
The first player always places 'X' characters, while the second player always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.


Example 1:
Input: board = ["O  ","   ","   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX"," X ","   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XOX","O O","XOX"]
Output: true

Constraints:
board.length == 3
board[i].length == 3
board[i][j] is either 'X', 'O', or ' '.
"""
from typing import List


class ValidTicTacToeState:
    def validTicTacToe(self, board: List[str]) -> bool:
        x_win = o_win = False
        rows = [0] * 3
        cols = [0] * 3
        diagonal = anti_diagonal = 0
        turns = 0
        for r in range(3):
            for c in range(3):
                if board[r][c] == 'X':
                    turns += 1
                    rows[r] += 1
                    cols[c] += 1
                    if r == c:
                        diagonal += 1
                    if r + c == 2:
                        anti_diagonal += 1
                elif board[r][c] == 'O':
                    turns -= 1
                    rows[r] -= 1
                    cols[c] -= 1
                    if r == c:
                        diagonal -= 1
                    if r + c == 2:
                        anti_diagonal -= 1

        for i in range(3):
            x_win = x_win or rows[i] == 3 or cols[i] == 3
            o_win = o_win or rows[i] == -3 or cols[i] == -3

        x_win = x_win or diagonal == 3 or anti_diagonal == 3
        o_win = o_win or diagonal == -3 or anti_diagonal == -3

        if (x_win and turns != 1) or (o_win and turns != 0):
            return False

        return not (x_win and o_win) and (turns == 0 or turns == 1)
