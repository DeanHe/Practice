package unionFind;

import java.util.Arrays;

/*
Alice and Bob have an undirected graph of n nodes and 3 types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can by traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi,
find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob.
The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if it's impossible for the graph to be fully traversed by Alice and Bob.

Example 1:
Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
Output: 2
Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.

Example 2:
Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
Output: 0
Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.

Example 3:
Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
Output: -1
Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.

Constraints:
1 <= n <= 10^5
1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
edges[i].length == 3
1 <= edges[i][0] <= 3
1 <= edges[i][1] < edges[i][2] <= n
All tuples (typei, ui, vi) are distinct.

analysis:
check type 3 first
 */
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        UnionFind uf_alice = new UnionFind(n);
        UnionFind uf_bob = new UnionFind(n);
        int edgeAdd = 0;
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (uf_alice.union(edge[1], edge[2])) {
                    edgeAdd++;
                }
            } else if (edge[0] == 2) {
                if (uf_bob.union(edge[1], edge[2])) {
                    edgeAdd++;
                }
            } else { // type 3
                boolean un_alice = uf_alice.union(edge[1], edge[2]);
                boolean un_bob = uf_bob.union(edge[1], edge[2]);
                if (un_alice || un_bob) {
                    edgeAdd++;
                }
            }
        }
        if (uf_alice.count == 1 && uf_bob.count == 1) {
            return edges.length - edgeAdd;
        }
        return -1;
    }

    private class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n + 1];
            count = n;
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
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

        public boolean union(int a, int b) {
            int root_a = findRoot(a);
            int root_b = findRoot(b);
            if (root_a != root_b) {
                parent[root_a] = root_b;
                count--;
                return true;
            } else {
                return false;
            }
        }
    }
}
