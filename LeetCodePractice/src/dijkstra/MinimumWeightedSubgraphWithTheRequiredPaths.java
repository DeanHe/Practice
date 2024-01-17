package dijkstra;

import java.util.*;

/*
You are given an integer n denoting the number of nodes of a weighted directed graph. The nodes are numbered from 0 to n - 1.

You are also given a 2D integer array edges where edges[i] = [fromi, toi, weighti] denotes that there exists a directed edge from fromi to toi with weight weighti.

Lastly, you are given three distinct integers src1, src2, and dest denoting three distinct nodes of the graph.

Return the minimum weight of a subgraph of the graph such that it is possible to reach dest from both src1 and src2 via a set of edges of this subgraph. In case such a subgraph does not exist, return -1.

A subgraph is a graph whose vertices and edges are subsets of the original graph. The weight of a subgraph is the sum of weights of its constituent edges.

Example 1:
Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
Output: 9
Explanation:
The above figure represents the input graph.
The blue edges represent one of the subgraphs that yield the optimal answer.
Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.

Example 2:
Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
Output: -1
Explanation:
The above figure represents the input graph.
It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.


Constraints:

3 <= n <= 10^5
0 <= edges.length <= 10^5
edges[i].length == 3
0 <= fromi, toi, src1, src2, dest <= n - 1
fromi != toi
src1, src2, and dest are pairwise distinct.
1 <= weight[i] <= 10^5

hint:
1 Consider what the paths from src1 to dest and src2 to dest would look like in the optimal solution.
2 It can be shown that in an optimal solution, the two paths from src1 and src2 will coincide at one node, and the remaining part to dest will be the same for both paths.
Now consider how to find the node where the paths will coincide.
3 How can algorithms for finding the shortest path between two nodes help us?

analysis:
dijkstra
TC O(V + E * log E)
SC O(E)
 */
public class MinimumWeightedSubgraphWithTheRequiredPaths {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        long res = Long.MAX_VALUE;
        Long[] dist1 = new Long[n];
        Long[] dist2 = new Long[n];
        Long[] revDist = new Long[n];
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, List<int[]>> revGraph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(new int[]{e[1], e[2]});
            revGraph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(new int[]{e[0], e[2]});
        }
        bfs(graph, dist1, src1);
        bfs(graph, dist2, src2);
        bfs(revGraph, revDist, dest);
        if (revDist[src1] == null || revDist[src2] == null) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if(dist1[i] != null && dist2[i] != null && revDist[i] != null){
                res = Math.min(res, dist1[i] + dist2[i] + revDist[i]);
            }
        }
        return res;
    }

    private void bfs(Map<Integer, List<int[]>> graph, Long[] dist, long src) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (int)(a[1] - b[1]));
        pq.offer(new long[]{src, 0});
        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            int cur = (int)arr[0];
            long d = arr[1];
            if (dist[cur] == null) {
                dist[cur] = d;
                for (int[] edge : graph.getOrDefault(cur, new ArrayList<>())) {
                    int nb = edge[0];
                    int travel = edge[1];
                    if (dist[nb] == null) {
                        pq.offer(new long[]{nb, d + travel});
                    }
                }
            }
        }
    }
}
