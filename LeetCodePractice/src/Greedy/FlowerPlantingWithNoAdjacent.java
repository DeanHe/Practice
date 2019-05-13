package Greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 

Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 

Note:

1 <= N <= 10000
0 <= paths.size <= 20000
No garden has 4 or more paths coming into or leaving it.
It is guaranteed an answer exists.*/
public class FlowerPlantingWithNoAdjacent {
	public int[] gardenNoAdj(int N, int[][] paths) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		int len = paths.length;
		for (int i = 0; i < len; i++) {
			if (!graph.containsKey(paths[i][0])) {
				graph.put(paths[i][0], new HashSet<>());
			}
			if (!graph.containsKey(paths[i][1])) {
				graph.put(paths[i][1], new HashSet<>());
			}
			graph.get(paths[i][0]).add(paths[i][1]);
			graph.get(paths[i][1]).add(paths[i][0]);
		}
		int[] res = new int[N];
		for (int i = 1; i <= N; i++) {
			Set<Integer> nbs = graph.get(i);
			if (nbs == null) {
				res[i - 1] = 1;
				continue;
			}
			for (int color = 1; color <= 4; color++) {
				boolean colorUsed = false;
				for (int nb : nbs) {
					if (res[nb - 1] == color) {
						colorUsed = true;
					}
				}
				if (!colorUsed) {
					res[i - 1] = color;
					break;
				}
			}
		}
		return res;
	}
}
