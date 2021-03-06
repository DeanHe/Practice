package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
In this problem, a tree is an undirected graph that is connected and has no cycles.
The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

// analysis:
DFS, before adding the edge, check if in the graph there is an existing path from edge[0] to edge[1]
*/
public class RedundantConnection {
    private int[] parent, size;

    public int[] findRedundantConnectionUN(int[][] edges) {
        int len = edges.length;
        parent = new int[len + 1];
        size = new int[len + 1];
        //init unionFind
        for (int i = 1; i <= len; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int root_s = findRoot(start);
            int root_e = findRoot(end);
            if (root_s == root_e) {
                return edge;
            }
            union(root_e, root_s);
        }
        return new int[]{-1, -1};
    }

    private void union(int a, int b) {
        // union, always merge smaller set to larger set
        if (size[a] > size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[a] = b;
        size[b] += size[a];
        size[a] = 0;
    }

    private int findRoot(int x) {
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[x] != root) {
            int father = parent[x];
            parent[x] = root;
            x = father;
        }
        return root;
    }

    //-----------------------------------------------------------------------------------------------------//
    public int[] findRedundantConnectionDFS(int[][] edges) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int[] edge : edges) {
            if (canReach(edge[0], edge[1], graph, visited)) {
                return edge;
            }
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        return new int[]{-1, -1};
    }

    private boolean canReach(int cur, int target, Map<Integer, ArrayList<Integer>> graph, Set<Integer> visited) {
        if(cur == target){
            return true;
        }
        visited.add(cur);
        if(graph.containsKey(cur)){
            for (int nb : graph.get(cur)) {
                if (!visited.contains(nb)) {
                    if (canReach(nb, target, graph, visited)) {
                        visited.remove(cur);
                        return true;
                    }
                }
            }
        }
        visited.remove(cur);
        return false;
    }
}
