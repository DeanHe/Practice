package unionFind;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

analysis:
TC O(E)
*/

public class GraphValidTree {
	private int[] parent;
	public boolean validTree(int n, int[][] edges) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		if (n - 1 != edges.length) {
			return false;
		}

		for (int i = 0; i < edges.length; i++) {
			// check if there is cycle
			if (find(edges[i][0]) == find(edges[i][1])) {
				return false;
			}
			union(edges[i][0], edges[i][1]);
		}
		return true;
	}

	// find the root of x and compress the path
	int find(int x) {
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
		int root_a = find(a);
		int root_b = find(b);
		if (root_a != root_b) {
			parent[root_a] = root_b;
		}
	}
}
