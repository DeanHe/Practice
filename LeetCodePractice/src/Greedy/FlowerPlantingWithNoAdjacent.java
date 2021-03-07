package Greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

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
It is guaranteed an answer exists.

analysis:
Greedily paint nodes one by one.
Because there is no node that has more than 3 neighbors,
always one possible color to choose.

TC O(N)
SC O(N)
*/
public class FlowerPlantingWithNoAdjacent {
	public int[] gardenNoAdj(int n, int[][] paths) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int[] path : paths) {
			graph.computeIfAbsent(path[0] - 1, x -> new HashSet<>()).add(path[1] - 1);
			graph.computeIfAbsent(path[1] - 1, x -> new HashSet<>()).add(path[0] - 1);
		}
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			if (!graph.containsKey(i)) {
				res[i] = 1;
				continue;
			}
			Set<Integer> nbs = graph.get(i);
			int[] colors = new int[5];
			for (int nb : nbs) {
				colors[res[nb]] = 1;
			}
			for(int j = 4; j > 0; j--){
				if(colors[j] == 0){
					res[i] = j;
					break;
				}
			}
		}
		return res;
	}
}
