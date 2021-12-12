package OOD.TicTacToe;
/*
Design a Tic-tac-toe game that is played between two players on a n x n grid.
You may assume the following rules:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
Hint:
Could you trade extra space such that move() operation can be done in O(1)?
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.

Solution:
Use addtional arrays rows[n], cols[n] and two varialbes diagonal, anti_diagonal to mark the number of Xs and Os.

Google question:
Rules: After a player places three marks on the board, for all following moves, he/she needs to remove the oldest move first and then make the new move.
	Q1: Define your own data structure to represent the state of the game. Use it for the rest of the problem.
	answer: using an array of length rows[3] + cols[3] + diag[1] + antidiag[1] + turn[1]
	Q2: Give a state of the current game and a proposed move, check whether this move is allowed or not.
	answer:
	Q3: Give a state of the current game, return the next move to guarantee a win if possible, otherwise just return any move. The win-or-not can be checked by a given API. Win(state)
	Q4: Give a state of the current game, return the next move to guarantee a win if possible. If not possible, return the move so that the next player cannot win in the next step.

 */

public class TicTacToe {
    int[] rows, cols;
    int n, diag, antidiag;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int val = player == 1 ? 1 : -1;
        rows[row] += val;
        cols[col] += val;
        if (row == col) {
            diag += val;
        }
        if (row + col == n - 1) {
            antidiag += val;
        }
        if (Math.abs(rows[row]) == n
                || Math.abs(cols[col]) == n
                || Math.abs(diag) == n
                || Math.abs(antidiag) == n) {
            return val > 0 ? 1 : 2;
        }
        return 0;
    }
}
