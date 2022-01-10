package dfs;

/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

analysis:
TC O(E)
*/
import java.util.*;

public class GraphValidTree {
	Map<Integer, List<Integer>> graph;
	boolean[] visited;

	public boolean validTree(int n, int[][] edges) {
		if (edges == null || edges.length != n - 1) {
			return false;
		}
		graph = new HashMap<>();
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			graph.put(i, new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		if (findCycle(0, -1)) {
			return false;
		}
		for (boolean vi : visited) {
			if (!vi) {
				return false;
			}
		}
		return true;
	}

	private boolean findCycle(int cur, int parent) {
		visited[cur] = true;
		for (int nb : graph.get(cur)) {
			if (visited[nb]) {
				if (nb != parent) {
					return true;
				}
				continue;
			}
			if (findCycle(nb, cur)) {
				return true;
			}
		}
		return false;
	}
}
