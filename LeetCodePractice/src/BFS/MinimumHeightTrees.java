package BFS;
/*For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]and thus will not appear together in edges.

Example
Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Notice
(1) According to the definition of tree on Wikipedia: a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.�

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

analysis:
bfs
time complexity: O(n)
*/

import java.util.*;

public class MinimumHeightTrees {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
		int[] indeg = new int[n];
		List<Integer> res = new ArrayList<>();
		if(n == 1){
			res.add(0);
			return res;
		}
		for(int[] edge : edges){
			int node1 = edge[0];
			int node2 = edge[1];
			graph.putIfAbsent(node1, new ArrayList<>());
			graph.get(node1).add(node2);
			graph.putIfAbsent(node2, new ArrayList<>());
			graph.get(node2).add(node1);
			indeg[node1]++;
			indeg[node2]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		//put leaf to queue
		for(int i = 0; i < n; i++){
			if(indeg[i] == 1){
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()){
			res.clear();
			int size = queue.size();
			for(int i = 0; i < size; i++){
				int leaf = queue.poll();
				res.add(leaf);
				indeg[leaf] = 0;
				for(int nb : graph.get(leaf)){
					if(indeg[nb] == 0){
						continue;
					}
					indeg[nb]--;
					if(indeg[nb] == 1){
						queue.offer(nb);
					}
				}
			}
		}
		return res;
	}
}
