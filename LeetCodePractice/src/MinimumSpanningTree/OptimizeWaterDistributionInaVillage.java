package MinimumSpanningTree;

import java.util.*;

/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.

Example 1:

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: 
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]
*/

public class OptimizeWaterDistributionInaVillage {
	int[] parent;
	public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
		parent = new int[n + 1];
		List<int[]> edges = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			parent[i + 1] = i + 1;
			edges.add(new int[] {0, i + 1, wells[i]});
		}
		for(int[] pipe : pipes) {
			edges.add(pipe);
		}
		Collections.sort(edges, (a, b) -> a[2] - b[2]);
		int res = 0;
		for(int[] pipe : edges) {
			int rootA = getRoot(pipe[0]);
			int rootB = getRoot(pipe[1]);
			if(rootA != rootB) {
				parent[rootA] = rootB;
				res += pipe[2];
			}
		}
		return res;
	}
	
	private int getRoot(int x) {
        while(parent[x] != x){
            x = parent[x];
        }
        return x;
	}
}
