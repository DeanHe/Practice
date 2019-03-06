package UnionFind;

import java.util.*;

/*In this problem, a tree is an undirected graph that is connected and has no cycles.
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
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.*/
public class RedundantConnection {
	int[] id, size;
	public int[] findRedundantConnectionUN(int[][] edges) {
		int len = edges.length;
		id = new int[len + 1];
		size = new int[len + 1];
		//init UN
		for (int i = 1; i <= len; i++) {
			id[i] = i;
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
			// union, always merge smaller set to larger set
			if(size[root_s] > size[root_e]){
				int temp = root_s;
				root_s = root_e;
				root_e = temp;
			}
			id[root_s] = id[root_e];
			size[root_e] += size[root_s];
			size[root_s] = 0;
		}
		return new int[]{-1,-1};
	}
	private int findRoot(int x) {
		int root = x;
		while (id[root] != root) {
			root = id[root];
		}
		while (id[x] != root) {
			int father = id[x];
			id[x] = root;
			x = father;
		}
		return root;
	}
	//-----------------------------------------------------------------------------------------------------//
	public int[] findRedundantConnectionDFS(int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int[] edge : edges) {
        	if(formCircle(edge[0], edge[1], graph, visited)){
    			return edge;
    		}
        	if(!graph.containsKey(edge[0])){
        		graph.put(edge[0], new ArrayList<>());
        	}
        	graph.get(edge[0]).add(edge[1]);
        	if(!graph.containsKey(edge[1])){
        		graph.put(edge[1], new ArrayList<>());
        	}
        	graph.get(edge[1]).add(edge[0]);
        }
        return new int[]{-1,-1};
    }
	private boolean formCircle(int cur, int target, HashMap<Integer, ArrayList<Integer>> graph, HashSet<Integer> visited) {
		if (cur == target) {
			return true;
		}
		visited.add(cur);
		if (graph.containsKey(cur) && graph.containsKey(target)) {
			for (int nb : graph.get(cur)) {
				if (!visited.contains(nb)) {
					if (formCircle(nb, target, graph, visited)) {
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
