package dfs;

import java.util.*;

/*
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.

analysis:
mark different island with different color
for each 0 find its island neighbors and connect them all
*/
public class MakingaLargeIsland {
	int rows, cols;
	Map<Integer, Integer> area;
	int[] dirs = { 1, 0, -1, 0, 1 };

	public int largestIsland(int[][] grid) {
		rows = grid.length;
		cols = grid[0].length;
		area = new HashMap<>();
		int mark = 1;
		int res = 1;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1) {
					mark++;
					area.put(mark, getArea(grid, mark, r, c));
					res = Math.max(res, area.get(mark));
				}
			}
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 0) {
					int total = 1;
					Set<Integer> nbColorSet = new HashSet<>();
					for (int i = 0; i < dirs.length - 1; i++) {
						nbColorSet.add(getColor(grid, r + dirs[i], c + dirs[i + 1]));
					}
					for (int color : nbColorSet) {
						total += area.getOrDefault(color, 0);
					}
					res = Math.max(res, total);
				}
			}
		}
		return res;
	}

	private int getColor(int[][] grid, int r, int c) {
		if (r >= rows || r < 0 || c >= cols || c < 0) {
			return 0;
		} else {
			return grid[r][c];
		}
	}

	private int getArea(int[][] grid, int color, int r, int c) {
		if (r >= rows || r < 0 || c >= cols || c < 0 || grid[r][c] != 1) {
			return 0;
		}
		grid[r][c] = color;
		int res = 1;
		for (int i = 0; i < dirs.length - 1; i++) {
			res += getArea(grid, color, r + dirs[i], c + dirs[i + 1]);
		}
		return res;
	}

	//////////////////slower way ///////////////////////////////////////////////
	public int largestIslandII(int[][] grid) {
		int res = -1;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 0) {
					grid[r][c] = 1;
					res = Math.max(res, dfs(grid, r, c, new boolean[rows][cols]));
					grid[r][c] = 0;
				}
			}
		}
		return res == -1 ? rows * cols : res;
	}

	private int dfs(int[][] grid, int r, int c, boolean[][] visited) {
		if (r >= rows || r < 0 || c >= cols || c < 0 || visited[r][c] || grid[r][c] == 0) {
			return 0;
		}
		visited[r][c] = true;
		int res = 1;
		for (int i = 0; i < dirs.length - 1; i++) {
			res += dfs(grid, r + dirs[i], c + dirs[i + 1], visited);
		}
		return res;
	}
}
