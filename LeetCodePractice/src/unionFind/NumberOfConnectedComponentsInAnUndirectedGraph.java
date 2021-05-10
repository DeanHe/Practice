package unionFind;
/*
#323

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    int[] parent;
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        for(int[] edge : edges){
            int root_a = findRoot(edge[0]);
            int root_b = findRoot(edge[1]);
            if(root_a != root_b){
                n--;
                parent[root_a] = root_b;
            }
        }
        return n;
    }

    private int findRoot(int x){
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }
        while(parent[x] != root){
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }
}
