package dfs;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/

public class NumberOfIslands {
	/**
	 * @param grid
	 *            a boolean 2D matrix
	 * @return an integer
	 */
	int[] direct = new int[]{0, 1, 0, -1, 0};
	int rows;
	int cols;
	public int numIslands(boolean[][] grid) {
		// dfs
		rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		cols = grid[0].length;
		if (cols == 0) {
			return 0;
		}
		int count = 0;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c]) {
					count++;
					dfs(grid, r, c);
				}
			}
		}
		return count;
	}

	private void dfs(boolean[][] grid, int r, int c) {
		grid[r][c] = false;
		for(int i = 0; i < direct.length - 1; i++){
			int nb_r = r + direct[i];
			int nb_c = c + direct[i + 1];
			if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c]){
				dfs(grid, nb_r, nb_c);
			}
		}
	}

	// Union Find way
	public int numIslandsUN(boolean[][] grid) {
		if (rows == 0) {
			return 0;
		}
		if (cols == 0) {
			return 0;
		}
		int count = 0;
		UnionFind unionFind = new UnionFind(rows * cols);
		// set unionFind labels for 1 square
		int labels = 0;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c]) {
					labels++;
				}
			}
		}
		unionFind.count = labels;
		// connect
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c]) {
					int temp1 = r * cols + c;
					int temp2 = 0;
					for(int i = 0; i < direct.length - 1; i++){
						int nb_r = r + direct[i];
						int nb_c = c + direct[i + 1];
						if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c]){
							temp2 = nb_r * cols + nb_c;
							unionFind.union(temp1, temp2);
						}
					}
				}
			}
		}
		return unionFind.count;
	}
}

class UnionFind {
	public int count;
	int[] id; // id[i] is parent of i

	public UnionFind(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	public int getRoot(int x) {
		while (id[x] != x) {
			x = id[x];
		}
		return x;
	}

	public void union(int a, int b) {
		int root_a = getRoot(a);
		int root_b = getRoot(b);
		if (root_a != root_b) {
			id[root_a] = root_b;
			count--;
		}
	}
}