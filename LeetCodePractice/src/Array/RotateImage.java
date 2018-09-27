package Array;

public class RotateImage {
	public void rotate(int[][] matrix) {
		int len = matrix.length;
		for (int r = 0; r < len / 2; r++) {
			for (int c = 0; c < Math.ceil(((double) len) / 2.0); c++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[len - 1 - c][r];
				matrix[len - 1 - c][r] = matrix[len - 1 - r][len - 1 - c];
				matrix[len - 1 - r][len - 1 - c] = matrix[c][len - 1 - r];
				matrix[c][len - 1 - r] = temp;
			}
		}
	}
}
