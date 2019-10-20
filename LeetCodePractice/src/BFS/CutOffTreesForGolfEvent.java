package BFS;

import java.util.*;
import java.util.function.IntPredicate;

/*
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
 
Example 2:

Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 
Example 3:

Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.

Hint: size of the given matrix will not exceed 50x50.
*/
public class CutOffTreesForGolfEvent {
	int rows, cols;
	int[] dirs = { 0, 1, 0, -1, 0 };

	public int cutOffTree(List<List<Integer>> forest) {
		if (forest == null || forest.size() == 0) {
			return 0;
		}
		rows = forest.size();
		cols = forest.get(0).size();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int height = forest.get(r).get(c);
				if (height > 1) {
					pq.offer(new int[] { r, c, height });
				}
			}
		}
		int res = 0;
		int[] start = new int[] { 0, 0 };
		while (!pq.isEmpty()) {
			int[] tree = pq.poll();
			int step = minStep(forest, start, tree);
			if (step < 0) {
				return -1;
			}
			res += step;
			start[0] = tree[0];
			start[1] = tree[1];
		}
		return res;
	}

	private int minStep(List<List<Integer>> forest, int[] start, int[] target) {
		int step = 0;
		boolean[][] visited = new boolean[rows][cols];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				int[] cur = queue.poll();
				if (cur[0] == target[0] && cur[1] == target[1]) {
					return step;
				}
				for (int j = 0; j < dirs.length - 1; j++) {
					int nb_r = cur[0] + dirs[j];
					int nb_c = cur[1] + dirs[j + 1];
					if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && forest.get(nb_r).get(nb_c) != 0
							&& !visited[nb_r][nb_c]) {
						queue.offer(new int[] { nb_r, nb_c });
						visited[nb_r][nb_c] = true;
					}
				}
			}
			step++;
		}
		return -1;
	}
}
