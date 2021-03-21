package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an 01 matrix gird of size n*m, 1 is a wall, 0 is a road, now you can turn a 1 in the grid into 0, Is there a way to go from the upper left corner to the lower right corner? If there is a way to go, how many steps to take at least?
*/
public class ZeroOneMatrixWalkingProblem {
	/**
	 * @param grid:
	 *            The gird
	 * @return: Return the steps you need at least
	 */
	int[] dirs = { 0, 1, 0, -1, 0 };
	int rows, cols;

	public int getBestRoad(int[][] grid) {
		rows = grid.length;
		cols = grid[0].length;
		int[][] srcDist = new int[rows][cols];
		int[][] desDist = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				srcDist[r][c] = Integer.MAX_VALUE;
				desDist[r][c] = Integer.MAX_VALUE;
			}
		}
		bfs(0, 0, grid, srcDist);
		bfs(rows - 1, cols - 1, grid, desDist);
		int res = srcDist[rows - 1][cols - 1];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if(srcDist[r][c] != Integer.MAX_VALUE && desDist[r][c] != Integer.MAX_VALUE){
					res = Math.min(res, srcDist[r][c] + desDist[r][c]);
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private void bfs(int start_r, int start_c, int[][] grid, int[][] dist) {
		dist[start_r][start_c] = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { start_r, start_c });
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cur_r = cur[0];
				int cur_c = cur[1];
				for (int j = 0; j < dirs.length - 1; j++) {
					int nb_r = cur_r + dirs[j];
					int nb_c = cur_c + dirs[j + 1];
					if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
						if (dist[nb_r][nb_c] == Integer.MAX_VALUE) {
							dist[nb_r][nb_c] = dist[cur_r][cur_c] + 1;
							if (grid[nb_r][nb_c] == 0) {
								queue.offer(new int[] { nb_r, nb_c });
							}
						}
					}
				}
			}
		}
	}
}
