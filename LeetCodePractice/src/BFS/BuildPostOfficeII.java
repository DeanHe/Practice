package BFS;

/*Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find a place to build a post office so that the sum of the distance from the post office to all the houses is smallest.

Return the smallest sum of distance. Return -1 if it is not possible.

You cannot pass through wall and house, but can pass through empty.
You only build post office on an empty.
Have you met this question in a real interview?  
Example
Given a grid:

0 1 0 0 0
1 0 0 2 1
0 1 0 0 0
return 8, You can build at (1,1). (Placing a post office at (1,1), the distance that post office to all the house sum is smallest.)*/
import java.util.*;

public class BuildPostOfficeII {
	/**
	 * @param grid:
	 *            a 2D grid
	 * @return: An integer
	 */
	int[][] reach;
	int[][] dist;
	boolean[][] visited;
	int rows, cols;
	int[] dx = { 0, -1, 0, 1 };
	int[] dy = { 1, 0, -1, 0 };

	public int shortestDistance(int[][] grid) {
		// write your code here
		if (grid == null || grid.length == 0) {
			return -1;
		}
		int res = Integer.MAX_VALUE;
		int numOfHouse = 0;
		rows = grid.length;
		cols = grid[0].length;
		reach = new int[rows][cols];
		dist = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1) {
					visited = new boolean[rows][cols];
					bfs(r, c, grid);
					numOfHouse++;
				}
			}
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 0) {
					if (reach[r][c] == numOfHouse) {
						res = Math.min(res, dist[r][c]);
					}
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private void bfs(int r, int c, int[][] grid) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;
		int step = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cur_r = cur[0];
				int cur_c = cur[1];
				reach[cur_r][cur_c]++;
				dist[cur_r][cur_c] += step;
				for (int j = 0; j < 4; j++) {
					int nb_r = cur_r + dx[j];
					int nb_c = cur_c + dy[j];
					if (inbound(nb_r, nb_c)) {
						if (!visited[nb_r][nb_c]) {
							if (grid[nb_r][nb_c] == 0) {
								queue.offer(new int[] { nb_r, nb_c });
								visited[nb_r][nb_c] = true;
							}
						}

					}
				}
			}
			step++;
		}
	}

	private boolean inbound(int r, int c) {
		return r >= 0 && r < rows && c >= 0 && c < cols ? true : false;
	}
}
