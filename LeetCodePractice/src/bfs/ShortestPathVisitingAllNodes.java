package bfs;

import java.util.*;

/*
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

Example 1:
Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]

Example 2:
Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3] 

Note:
1 <= graph.length <= 12
0 <= graph[i].length < graph.length

analysis:
bfs
DP dist[i][state] means the shortest steps to reach state with last node in i
TC: O(N * 2^N)
*/
public class ShortestPathVisitingAllNodes {
	public int shortestPathLength(int[][] graph) {
		int N = graph.length;
		Integer[][] dist = new Integer[N][1 << N];
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			queue.offer(new int[]{i, 1 << i});
			dist[i][1 << i] = 0;
		}
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int node = cur[0];
			int state = cur[1];
			int step = dist[cur[0]][state];
			if (state == (1 << N) - 1) {
				return step;
			}
			int[] neighbors = graph[node];
			for (int nb : neighbors) {
				int nb_state = state| 1 << nb;
				if (dist[nb][nb_state] == null || step + 1 < dist[nb][nb_state]) {
					dist[nb][nb_state] = step + 1;
					queue.offer(new int[]{nb, nb_state});
				}
			}
		}
		throw null;
	}
}
