package UnionFind;

/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.*/
import java.util.*;

public class GraphValidTree {
	// map of node and its parent
	int[] parent;

	// find the root of x and compress the path
	int compressed_find(int x) {
		int root = x;
		while (root != parent[root]) {
			root = parent[root];
		}
		// here parent equals root of x
		while (parent[x] != root) {
			int fa = parent[x];
			parent[x] = root;
			x = fa;
		}
		return root;
	}

	void union(int a, int b) {
		int root_a = compressed_find(a);
		int root_b = compressed_find(b);
		if (root_a != root_b) {
			parent[root_a] = root_b;
		}
	}

	public boolean validTree(int n, int[][] edges) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		// Write your code here
		if (n - 1 != edges.length) {
			return false;
		}

		for (int i = 0; i < edges.length; i++) {
			// check if there is cycle
			if (compressed_find(edges[i][0]) == compressed_find(edges[i][1])) {
				return false;
			}
			union(edges[i][0], edges[i][1]);
		}
		return true;
	}
}
