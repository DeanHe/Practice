package Array;

import java.util.Arrays;

/*Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
Return the maximum number of rows that have all values equal after some number of flips.

Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
 
Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1*/
public class FlipColumnsForMaximumNumberOfEqualRows {
	public int maxEqualRowsAfterFlips(int[][] matrix) {
		int res = 0;
		int rows = matrix.length, cols = matrix[0].length;
		int[] flipRow = new int[cols];
		for (int r = 0; r < rows; r++) {
			int count = 0;
			for (int c = 0; c < cols; c++) {
				flipRow[c] = 1 - matrix[r][c];
			}
			for (int i = 0; i < rows; i++) {
				if (Arrays.equals(matrix[r], matrix[i]) || Arrays.equals(flipRow, matrix[i])) {
					count++;
				}
			}
			res = Math.max(res, count);
		}
		return res;
	}
}
