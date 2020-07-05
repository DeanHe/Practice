package MinimumSpanningTree;


/*
Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi.
A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph.
An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge.
A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.
 Note that you can return the indices of the edges in any order.


        Example 1:

        Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
        Output: [[0,1],[2,3,4,5]]
        Explanation: The figure above describes the graph.
        The following figure shows all the possible MSTs:

        Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
        The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.

        Example 2:

        Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
        Output: [[],[0,1,2,3]]
        Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.


        Constraints:

        2 <= n <= 100
        1 <= edges.length <= min(200, n * (n - 1) / 2)
        edges[i].length == 3
        0 <= fromi < toi < n
       1 <= weighti <= 1000
        All pairs (fromi, toi) are distinct.

        Hint:
        Use the Kruskal algorithm to find the minimum spanning tree by sorting the edges and picking edges from ones with smaller weights.
        Use a disjoint set to avoid adding redundant edges that result in a cycle.
        To find if one edge is critical, delete that edge and re-run the MST algorithm and see if the weight of the new MST increases.
        To find if one edge is non-critical (in any MST), include that edge to the accepted edge list and continue the MST algorithm, then see if the resulting MST has the same weight of the initial MST of the entire graph.
*/

import java.util.*;

public class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> criticals = new ArrayList<>();
        List<Integer> pseduos = new ArrayList<>();
        Map<int[], Integer> edgeIdx = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            edgeIdx.put(edges[i], i);
        }
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int minCost = buildMST(n, edges, null, null);
        for (int[] edge : edges) {
            int idx = edgeIdx.get(edge);
            int costWithout = buildMST(n, edges, null, edge);
            if (costWithout > minCost) {
                criticals.add(idx);
            } else {
                int costWith = buildMST(n, edges, edge, null);
                if (costWith == minCost) {
                    pseduos.add(idx);
                }
            }
        }
        return Arrays.asList(criticals, pseduos);
    }

    private int buildMST(int n, int[][] edges, int[] pick, int[] skip) {
        UF uf = new UF(n);
        int cost = 0;
        if (pick != null) {
            uf.union(pick[0], pick[1]);
            cost += pick[2];
        }
        for (int[] edge : edges) {
            if (edge != skip) {
                if (uf.union(edge[0], edge[1])) {
                    cost += edge[2];
                }
            }
        }
        return uf.count == 1 ? cost : Integer.MAX_VALUE;
    }

    class UF {
        final int[] parent;
        int count;

        public UF(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int root_x = findRoot(x);
            int root_y = findRoot(y);
            if (root_x != root_y) {
                count--;
                parent[root_x] = root_y;
                return true;
            }
            return false;
        }

        public int findRoot(int x) {
            int root = x;
            while (parent[root] != root) {
                root = parent[root];
            }
            while (parent[x] != root) {
                int fa = parent[x];
                parent[x] = root;
                x = fa;
            }
            return root;
        }
    }
}
