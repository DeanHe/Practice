package BFS;

import java.util.*;

/*Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).*/
public class PacificAtlanticWaterFlow {
	int[] dirs = { 0, 1, 0, -1, 0 };
	int rows, cols;

	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> res = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		rows = matrix.length;
		cols = matrix[0].length;
		Queue<int[]> pacificQueue = new LinkedList<>();
		Queue<int[]> atlanticQueue = new LinkedList<>();
		boolean[][] pacificVisited = new boolean[rows][cols];
		boolean[][] atlanticVisited = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			pacificQueue.offer(new int[] { r, 0 });
			pacificVisited[r][0] = true;
			atlanticQueue.offer(new int[] { r, cols - 1 });
			atlanticVisited[r][cols - 1] = true;
		}
		for (int c = 0; c < cols; c++) {
			pacificQueue.offer(new int[] { 0, c });
			pacificVisited[0][c] = true;
			atlanticQueue.offer(new int[] { rows - 1, c });
			atlanticVisited[rows - 1][c] = true;
		}
		bfs(matrix, pacificQueue, pacificVisited);
		bfs(matrix, atlanticQueue, atlanticVisited);		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(pacificVisited[r][c] && atlanticVisited[r][c]) {
					res.add(new int[] {r, c});
				}
			}
		}
		return res;
	}

	private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < dirs.length - 1; i++) {
				int nb_r = cur[0] + dirs[i];
				int nb_c = cur[1] + dirs[i + 1];
				if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !visited[nb_r][nb_c]
						&& matrix[nb_r][nb_c] >= matrix[cur[0]][cur[1]]) {
					queue.offer(new int[] { nb_r, nb_c });
					visited[nb_r][nb_c] = true;
				}
			}
		}
	}
}
