package DFS;

/*Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

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

Output: 3*/

public class NumberOfIslands {
	/**
	 * @param grid
	 *            a boolean 2D matrix
	 * @return an integer
	 */
	public int numIslands(boolean[][] grid) {
		// DFS
		int rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		int cols = grid[0].length;
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
		int rows = grid.length;
		int cols = grid[0].length;
		if (r < 0 || r >= rows || c < 0 || c >= cols || !grid[r][c]) {
			return;
		}
		grid[r][c] = false;
		int[] direct = new int[]{0, 1, 0, -1, 0};
		for(int i = 0; i < direct.length - 1; i++){
			dfs(grid, r + direct[i], c + direct[i + 1]);
		}
	}

	// Union Find way
	public int numIslandsUN(boolean[][] grid) {
		int rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		int cols = grid[0].length;
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
					if (r > 0 && grid[r - 1][c]) {
						temp2 = (r - 1) * cols + c;
						unionFind.union(temp1, temp2);
					}
					if (r < rows - 1 && grid[r + 1][c]) {
						temp2 = (r + 1) * cols + c;
						unionFind.union(temp1, temp2);
					}
					if (c > 0 && grid[r][c - 1]) {
						temp2 = r * cols + c - 1;
						unionFind.union(temp1, temp2);
					}
					if (c < cols - 1 && grid[r][c + 1]) {
						temp2 = r * cols + c + 1;
						unionFind.union(temp1, temp2);
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