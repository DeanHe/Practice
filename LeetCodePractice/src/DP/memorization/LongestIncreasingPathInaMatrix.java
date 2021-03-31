package DP.memorization;

/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.*/
/*To get max length of increasing sequences:

Do dfs from every cell
Compare every 4 direction and skip cells that are out of boundary or smaller
Get matrix max from every cell's max
Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
The key is to cache the distance because it's highly possible to revisit a cell

analysis:
dfs + memorization
*/
public class LongestIncreasingPathInaMatrix {
	int rows, cols;
	int[] direct = { 0, 1, 0, -1, 0 };

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int maxPath = 1;
		rows = matrix.length;
		cols = matrix[0].length;
		Integer[][] mem = new Integer[rows][cols]; // mem[r][c] means the maximum path start from {r, c}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int count = dfs(matrix, mem, r, c);
				maxPath = Math.max(maxPath, count);
			}
		}
		return maxPath;
	}

	private int dfs(int[][] matrix, Integer[][] mem, int r, int c) {
		if (mem[r][c] != null) {
			return mem[r][c];
		}
		int count = 1;
		for (int i = 0; i < direct.length - 1; i++) {
			int nb_r = r + direct[i];
			int nb_c = c + direct[i + 1];
			if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && matrix[nb_r][nb_c] > matrix[r][c]) {
				int temp = 1 + dfs(matrix, mem, nb_r, nb_c);
				count = Math.max(count, temp);
			}
		}
		return mem[r][c] = count;
	}
}
