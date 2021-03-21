package bfs;

import java.util.*;

/*Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if destination cannot be reached.

Example
Example 1:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] 
Output: 2
Explanation:
[2,0]->[0,1]->[2,2]
Example 2:

Input:
[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2] 
Output:-1
Clarification
If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
Notice
source and destination must be empty.
Knight can not enter the barrier.*/
public class KnightShortestPath {
	int[][] direct = { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 } };
	int rows, cols;

	/**
	 * @param grid:
	 *            a chessboard included 0 (false) and 1 (true)
	 * @param source:
	 *            a point
	 * @param destination:
	 *            a point
	 * @return: the shortest path
	 */
	public int shortestPath(boolean[][] grid, Point source, Point destination) {
		// write your code here
		rows = grid.length;
		cols = grid[0].length;
		int step = 0;
		boolean[][] visited = new boolean[rows][cols];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(source);
		visited[source.x][source.y] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				Point cur = queue.poll();
				if(cur.x == destination.x && cur.y == destination.y){
					return step;
				}
				for(int j = 0; j < direct.length; j++){
					int nb_x = cur.x + direct[j][0];
					int nb_y = cur.y + direct[j][1];
					Point nb = new Point(nb_x, nb_y);
					if(isValid(grid, nb) && !visited[nb.x][nb.y]){
						queue.offer(nb);
						visited[nb.x][nb.y] = true;
					}
				}
			}
			step++;
		}
		return -1;
	}
	private boolean isValid(boolean[][] grid, Point cur){
		if(cur.x >= 0 && cur.x < rows && cur.y >= 0 && cur.y < cols && !grid[cur.x][cur.y]){
			return true;
		}
		return false;
	}
}
