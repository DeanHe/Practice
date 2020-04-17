package UnionFind;

/*
n this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

solution:
There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists
*/
public class RedundantConnectionII {
    private int[] parent;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        parent = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            parent[i] = i;
        }
        int[] candidate1 = null;
        int[] candidate2 = null;
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int root_s = findRoot(start);
            int root_e = findRoot(end);
            if (root_e != end) {
                candidate1 = edge; //Record the last edge which results in "multiple parents" issue
            } else if (root_s == root_e) {
                candidate2 = edge; // Record last edge which results in "cycle" issue, if any.
            } else {
                union(root_e, root_s);
            }
        }
        // If there is only one issue, return this one.
        if (candidate1 == null) {
            return candidate2;
        }
        if (candidate2 == null) {
            return candidate1;
        }
        /* If both issues present, then the answer should be the first edge which results in "multiple parents" issue
        The reason is, when an issue happens, we skip the "union" process.
		Therefore, if both issues happen, it means the incorrent edge which results in "multiple parents" was ignored. */
        for(int[] edge : edges){
            if(edge[1] == candidate1[1]){
                return edge;
            }
        }
        return new int[2];
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

    private void union(int a, int b) {
        parent[a] = b;
    }
}
