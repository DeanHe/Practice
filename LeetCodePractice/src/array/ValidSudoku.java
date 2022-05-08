package array;
/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
		int rows = board.length, cols = board[0].length;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (board[r][c] == '.') {
					continue;
				}
				// check if row contains duplicates
				for (int i = 0; i < cols; i++) {
					if (board[r][i] == board[r][c] && i != c) {
						return false;
					}
				}
				// check if col contains duplicates
				for (int i = 0; i < rows; i++) {
					if (board[i][c] == board[r][c] && i != r) {
						return false;
					}
				}

				// check the 3*3 matrix contains duplicates
				int startRow = r - r % 3;
				int startCol = c - c % 3;
				for (int m = 0; m < 3; m++) {
					for (int n = 0; n < 3; n++) {
						if (board[startRow + m][startCol + n] == board[r][c]
								&& (r != startRow + m || c != startCol + n)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
