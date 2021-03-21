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
0 <= graph[i].length < graph.length*/
public class ShortestPathVisitingAllNodes {
	public int shortestPathLength(int[][] graph) {
		int N = graph.length;
		int[][] dist = new int[N][1 << N];
		for (int[] row : dist) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		Queue<State> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			queue.offer(new State(i, 1 << i));
			dist[i][1 << i] = 0;
		}
		while (!queue.isEmpty()) {
			State state = queue.poll();
			int step = dist[state.cur][state.visited];
			if (state.visited == (1 << N) - 1) {
				return step;
			}
			int[] neighbors = graph[state.cur];
			for (int nb : neighbors) {
				int nb_visited = state.visited | 1 << nb;
				if (step + 1 < dist[nb][nb_visited]) {
					dist[nb][nb_visited] = step + 1;
					queue.offer(new State(nb, nb_visited));
				}
			}
		}
		throw null;
	}

	private class State {
		int cur, visited;

		public State(int c, int v) {
			cur = c;
			visited = v;
		}
	}
}
