package bfs;

import java.util.*;

/*In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.

We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.

Return true if and only if it is possible to reach the target square through a sequence of moves.

Example 1:
Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
Output: false
Explanation: 
The target square is inaccessible starting from the source square, because we can't walk outside the grid.

Example 2:
Input: blocked = [], source = [0,0], target = [999999,999999]
Output: true
Explanation: 
Because there are no blocked cells, it's possible to reach the target square.
 

Note:

0 <= blocked.length <= 200
blocked[i].length == 2
0 <= blocked[i][j] < 10^6
source.length == target.length == 2
0 <= source[i][j], target[i][j] < 10^6
source != target*/
public class EscapeALargeMaze {
	Set<String> blockedSet = new HashSet<>(); 
	public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
		for(int[] p : blocked){
			blockedSet.add(p[0] + "," + p[1]);
		}
		return bfs(source, target) && bfs(target, source);
	}

	private boolean bfs(int[] source, int[] target) {
		Set<String> visited = new HashSet<>();
		Queue<int[]> queue = new LinkedList<>();
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		queue.offer(source);
		visited.add(source[0] + "," + source[1]);
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();			
			int cur_x = cur[0];
			int cur_y = cur[1];
			if(cur_x == target[0] && cur_y == target[1]){
				return true;
			}
			for(int i = 0; i < dirs.length; i++){
				int nb_x = cur[0] + dirs[i][0];
				int nb_y = cur[1] + dirs[i][1];
				String nbStr = nb_x + "," + nb_y;
				if(nb_x >= 0 && nb_x < 1e6 && nb_y >= 0 && nb_y < 1e6 && !visited.contains(nbStr) && !blockedSet.contains(nbStr)){
					queue.offer(new int[] {nb_x, nb_y});
					visited.add(nbStr);
				}
			}
			if(queue.size() > blockedSet.size()){
				return true;
			}
		}
		return false;
	}
}
