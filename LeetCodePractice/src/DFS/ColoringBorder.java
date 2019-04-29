package DFS;

/*Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.
Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.
The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.

Example 1:

Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
Output: [[3, 3], [3, 2]]
Example 2:

Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
Output: [[1, 3, 3], [2, 3, 3]]
Example 3:

Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 

Note:

1 <= grid.length <= 50
1 <= grid[0].length <= 50
1 <= grid[i][j] <= 1000
0 <= r0 < grid.length
0 <= c0 < grid[0].length
1 <= color <= 1000
https://leetcode.com/problems/coloring-a-border/discuss/282847/C%2B%2B-with-picture-DFS
*/
public class ColoringBorder {
	int rows, cols;

	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		rows = grid.length;
		cols = grid[0].length;
		dfs(grid, r0, c0, grid[r0][c0]);
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				if(grid[r][c] < 0){
					grid[r][c] = color;
				}
			}
		}
		return grid;
	}

	private void dfs(int[][] grid, int r, int c, int color) {
		if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != color) {
			return;
		}
		grid[r][c] = -color;
		int[][] dirs = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
		for (int i = 0; i < dirs.length; i++) {
			dfs(grid, r + dirs[i][0], c + dirs[i][1], color);
		}
		if(!isBorder(grid, r, c)){
			grid[r][c] = color;
		}
	}

	private boolean isBorder(int[][] grid, int r, int c) {
		if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
			return true;
		}
		int top = Math.abs(grid[r - 1][c]);
		int bottom = Math.abs(grid[r + 1][c]);
		int left = Math.abs(grid[r][c - 1]);
		int right = Math.abs(grid[r][c + 1]);
		int self = Math.abs(grid[r][c]);
		if (self == left && self == right && self == top && self == bottom) {
			return false;
		}
		return true;
	}
}
