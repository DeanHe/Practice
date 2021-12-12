package OOD.TicTacToe;

/*
A Tic-Tac-Toe board is given as a string array board. Return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares ' '.
The first player always places 'X' characters, while the second player always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.

Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Notice
board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {' ', 'X', 'O'}.
*/
public class ValidTicTacToeState {
	/**
	 * @param board:
	 *            the given board
	 * @return: True if and only if it is possible to reach this board position
	 *          during the course of a valid tic-tac-toe game
	 */
	public boolean validTicTacToe(String[] board) {
		char[][] arr = new char[3][3];
		boolean xWin = false, oWin = false;
		int[] rows = new int[3];
		int[] cols = new int[3];
		int diag = 0, antidiag = 0;
		int turns = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				arr[r][c] = board[r].charAt(c);
				if (arr[r][c] == 'X') {
					turns++;
					rows[r]++;
					cols[c]++;
					if(r == c){
						diag++;
					}
					if(r + c == 2){
						antidiag++;
					}
				} else if (arr[r][c] == 'O') {
					turns--;
					rows[r]--;
					cols[c]--;
					if(r == c){
						diag--;
					}
					if(r + c == 2){
						antidiag--;
					}
				}
			}
		}
		for(int i = 0; i < 3; i++){
			xWin = xWin || (rows[i] == 3) || (cols[i] == 3);
			oWin = oWin || (rows[i] == -3) || (cols[i] == -3);
		}
		xWin = xWin || (diag == 3) || (antidiag == 3);
		oWin = oWin || (diag == -3) || (antidiag == -3);
		if((xWin && turns != 1) || (oWin && turns != 0)){
			return false;
		}
		return (turns == 0 || turns == 1) && !(xWin && oWin);
	}
}
