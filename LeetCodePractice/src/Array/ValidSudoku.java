package Array;

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
	       if (board == null || board.length != 9 || board[0].length != 9) {
				return false;
			}

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (board[i][j] == '.') {
						continue;
					} else {
						// check if row contains duplicates
						for (int k = 0; k < board[0].length; k++) {
							if (board[i][k] == board[i][j] && k != j) {
								return false;
							}
							// check if col contains duplicates
							for (int p = 0; p < board.length; p++) {
								if (board[p][j] == board[i][j] && p != i) {
									return false;
								}
							}

							// check the 3*3 matrix contains duplicates
							int startRow = i - i % 3;
							int startCol = j - j % 3;
							for (int m = 0; m < 3; m++) {
								for (int n = 0; n < 3; n++) {
									if (board[startRow + m][startCol + n] == board[i][j]
											&& i != startRow + m
											&& j != startCol + n) {
										return false;
									}
								}
							}
						}
					}
				}
			}
			return true;
	    }
}
