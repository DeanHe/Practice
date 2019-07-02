package Array;

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
