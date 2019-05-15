package Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.

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
A reachable node is a node that can be travelled to using at most M moves starting from node 0.*/
public class ReachableNodesInSubdividedGraph {
	public int reachableNodes(int[][] edges, int M, int N) {
		Map<Integer, List<Node>> graph = new HashMap<>();
		Map<Integer, Integer> moveMap = new HashMap<>();
		for(int[] edge : edges){
			if(!graph.containsKey(edge[0])){
				graph.put(edge[0], new ArrayList<>());
			}
			if(!graph.containsKey(edge[1])){
				graph.put(edge[1], new ArrayList<>());
			}
			graph.get(edge[0]).add(new Node(edge[1], edge[2]));
			graph.get(edge[1]).add(new Node(edge[0], edge[2]));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.dist - a.dist);
		pq.offer(new Node(0, M));
		while(!pq.isEmpty()){
			Node cur = pq.poll();
			if(moveMap.containsKey(cur.index)){
				continue;
			}
			moveMap.put(cur.index, cur.dist);
			if (graph.containsKey(cur.index)) {
				List<Node> neighbors = graph.get(cur.index);
				for (Node nb : neighbors) {
					int remainMove = cur.dist - nb.dist - 1;
					if (!moveMap.containsKey(nb.index) && remainMove >= 0) {
						pq.offer(new Node(nb.index, remainMove));
					}
				}
			}
		}
		int res = moveMap.size();
		for(int[] edge : edges){
			int remainMove1 = moveMap.getOrDefault(edge[0], 0);
			int remainMove2 = moveMap.getOrDefault(edge[0], 0);
			res += Math.min(remainMove1 + remainMove2, edge[2]);
		}
		return res;
    }
}
