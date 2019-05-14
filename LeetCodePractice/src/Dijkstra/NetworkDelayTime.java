package Dijkstra;
/*There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example
Example1

Input:
times = [[2,1,1],[2,3,1],[3,4,1]]
N = 4
K = 2
Output:
2
Notice
1.N will be in the range [1, 100].
2.K will be in the range [1, N].
3.The length of times will be in the range [1, 6000].
4.All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.*/

import java.util.*;

public class NetworkDelayTime {
	class Node {
		int index, dist;

		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
	}

	/**
	 * @param times:
	 *            a 2D array
	 * @param N:
	 *            an integer, # of network nodes, labelled 1 to N
	 * @param K:
	 *            an integer, we send a signal from a certain node K
	 * @return: how long will it take for all nodes to receive the signal
	 */
	public int networkDelayTime(int[][] times, int N, int K) {
		// Write your code here
		Map<Integer, List<Node>> graph = new HashMap<>();
		Map<Integer, Integer> distMap = new HashMap<>();
		for (int[] entry : times) {
			if (!graph.containsKey(entry[0])) {
				graph.put(entry[0], new ArrayList<Node>());
			}
			List<Node> neighbors = graph.get(entry[0]);
			neighbors.add(new Node(entry[1], entry[2]));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(N, new Comparator<Node>() {
			public int compare(Node a, Node b) {
				return a.dist - b.dist;
			}
		});
		Node start = new Node(K, 0);
		pq.offer(start);
		// Dijkstra's Algorithm
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (distMap.containsKey(cur.index)) {
				continue;
			}
			distMap.put(cur.index, cur.dist);
			if (graph.containsKey(cur.index)) {
				List<Node> neighbors = graph.get(cur.index);
				for (Node nb : neighbors) {
					if (!distMap.containsKey(nb.index)) {
						nb.dist = cur.dist + nb.dist;
						pq.offer(nb);
					}
				}
			}
		}

		if (distMap.size() != N) {
			return -1;
		}
		int ans = 0;
		for (int d : distMap.values()) {
			ans = Math.max(ans, d);
		}
		return ans;
	}

	public int networkDelayTimeV2(int[][] times, int N, int K) {
		// Write your code here
		Map<Integer, List<Node>> graph = new HashMap<>();
		int[] dist = new int[N + 1];
		HashSet<Integer> visited = new HashSet<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int[] entry : times) {
			if (!graph.containsKey(entry[0])) {
				graph.put(entry[0], new ArrayList<Node>());
			}
			List<Node> neighbors = graph.get(entry[0]);
			neighbors.add(new Node(entry[1], entry[2]));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(N, new Comparator<Node>() {
			public int compare(Node a, Node b) {
				return a.dist - b.dist;
			}
		});
		Node start = new Node(K, 0);
		dist[start.index] = start.dist;
		visited.add(start.index);
		pq.offer(start);
		// Dijkstra's Algorithm
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited.contains(cur)) {
				continue;
			}
			visited.add(cur.index);
			if (graph.containsKey(cur.index)) {
				List<Node> neighbors = graph.get(cur.index);
				for (Node nb : neighbors) {
					if (dist[nb.index] > cur.dist + nb.dist) {
						dist[nb.index] = cur.dist + nb.dist;
						nb.dist = dist[nb.index];
						pq.offer(nb);
					}
				}
			}
		}
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				return -1;
			}
			ans = Math.max(ans, dist[i]);
		}
		return ans;
	}
}
