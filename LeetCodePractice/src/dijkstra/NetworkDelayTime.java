package dijkstra;
/*
There are N network nodes, labelled 1 to N.

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
4.All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

analysis:
dist map track shortest dist from node i to K
TC O(V + ElogE)
*/

import java.util.*;

public class NetworkDelayTime {

	/**
	 * @param times:
	 *            a 2D array
	 * @param n:
	 *            an integer, # of network nodes, labelled 1 to N
	 * @param k:
	 *            an integer, we send a signal from a certain node K
	 * @return: how long will it take for all nodes to receive the signal
	 */
	public int networkDelayTime(int[][] times, int n, int k) {
		// Write your code here
		Map<Integer, List<int[]>> graph = new HashMap<>();
		Map<Integer, Integer> dist = new HashMap<>();
		for (int[] entry : times) {
			int start = entry[0];
			int end = entry[1];
			int travel = entry[2];
			graph.computeIfAbsent(start, x -> new ArrayList<>()).add(new int[]{end, travel});
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a, b) -> a[1] - b[1]);
		int res = 0;
		int[] start = new int[]{k, 0};
		pq.offer(start);
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int stop = cur[0];
			int cost = cur[1];
			if (!dist.containsKey(stop)) {
				dist.put(stop, cost);
				res = Math.max(res, cost);
				if (graph.containsKey(stop)) {
					List<int[]> neighbors = graph.get(stop);
					for (int[] nb : neighbors) {
						int nb_stop = nb[0];
						int nb_travel = nb[1];
						if (!dist.containsKey(nb_stop)) {
							int nb_cost = cost + nb_travel;
							pq.offer(new int[]{nb_stop, nb_cost});
						}
					}
				}
			}
		}

		if (dist.size() != n) {
			return -1;
		}
		return res;
	}
}
