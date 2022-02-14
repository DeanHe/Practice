package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.

The graph is given as follows: edges[k] is a list of integer pairs (i, j, n) such that (i, j) is an edge of the original graph,

and n is the total number of new nodes on that edge. 

Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1, x_2, ..., x_n) are added to the original graph,

and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to the original graph.

Now, you start at node 0 from the original graph, and in each move, you travel along one edge. 

Return how many nodes you can reach in at most M moves.

Example 1:
Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
Output: 13
Explanation: 
The nodes that are reachable in the final graph after M = 6 moves are indicated below.

Example 2:
Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
Output: 23
 

Note:

0 <= edges.length <= 10000
0 <= edges[i][0] < edges[i][1] < N
There does not exist any i != j for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1].
The original graph has no parallel edges.
0 <= edges[i][2] <= 10000
0 <= M <= 10^9
1 <= N <= 3000
A reachable node is a node that can be travelled to using at most M moves starting from node 0.

analysis:
get all nodes can be reachable by node 0 by pq.
Instead of maintaining a MinHeap which keeps track of shortest distances to the source, we maintain a MaxHeap that keeps track of maximum moves remained for each node.

dist map saves remain move after stop
Time Complexity: O(E log N), where EE is the length of edges.
SC: O(E)
*/
public class ReachableNodesInSubdividedGraph {
	public int reachableNodes(int[][] edges, int maxMoves, int n) {
		Map<Integer, List<int[]>> graph = new HashMap<>();
		Map<Integer, Integer> dist = new HashMap<>();
		for(int[] edge : edges){
			int start = edge[0];
			int end = edge[1];
			int travel = edge[2];
			graph.computeIfAbsent(start, x -> new ArrayList<>()).add(new int[]{end, travel});
			graph.computeIfAbsent(end, x -> new ArrayList<>()).add(new int[]{start, travel});
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]); // max heap
		pq.offer(new int[]{0, maxMoves});
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			int stop = cur[0];
			int remain = cur[1];
			if(!dist.containsKey(stop)){
				dist.put(stop, remain);
				for (int[] nb : graph.getOrDefault(stop, new ArrayList<>())) {
					int nb_stop =nb[0];
					int nb_travel = nb[1];
					int remainMove = remain - nb_travel - 1;
					if (!dist.containsKey(nb_stop) && remainMove >= 0) {
						pq.offer(new int[]{nb_stop, remainMove});
					}
				}
			}
		}
		int res = dist.size();
		for(int[] edge : edges){
			int start = edge[0];
			int end = edge[1];
			int travel = edge[2];
			//counting the reachable nodes lying on edge e from node start
			int remainMove1 = dist.getOrDefault(start, 0);
			//counting the reachable nodes lying on edge e from node end
			int remainMove2 = dist.getOrDefault(end, 0);
			res += Math.min(remainMove1 + remainMove2, travel);
		}
		return res;
    }
}
