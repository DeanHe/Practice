package UnionFind;
/*n this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
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
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.*/

/*
case1: There is a loop in the graph, and no vertex has more than 1 parent. In this case, you can simply output the edge in the loop that occurs last.
case2: A vertex has more than 1 parent, but there isn't a loop in the graph
case3: 1 A vertex has more than 1 parent, and is part of a loop.

1) Check whether there is a node having two parents. 
    If so, store them as candidates A and B, and set the second edge invalid. 
2) Perform normal union find. 
    If the tree is now valid 
           simply return candidate B
    else if candidates not existing 
           we find a circle, return current edge; 
    else 
           remove candidate A instead of B.
*/
public class RedundantConnectionII {
	int[] parent;
	int[] id, size;
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int len = edges.length;
		parent = new int[len + 1];
		id = new int[len + 1];
		size = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			id[i] = i;
			size[i] = 1;
		}
		int[] candidate1 = {-1, -1}; 
		int[] candidate2 = {-1, -1};
		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			if (parent[end] > 0) {
				// already has parent
				candidate1 = new int[]{parent[end], end}; //case 3
				candidate2 = new int[]{start, end};   // case 2
				//mark edge
				edge[0] = -1;
				edge[1] = -1;
			}
			parent[end] = start;
		}
		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			if (start == -1 && end == -1) {
				continue;
			}
			int root_s = findRoot(start);
			int root_e = findRoot(end);
			if(root_s == root_e){
				if (candidate1[0] == -1) {
					return edge; // case 1
				} else {
					return candidate1;
				}
			}
			// union, always merge smaller set to larger set
			if (size[root_s] > size[root_e]) {
				int temp = root_s;
				root_s = root_e;
				root_e = temp;
			}
			id[root_s] = root_e;
			size[root_e] += size[root_s];
			size[root_s] = 0;
		}
		return candidate2;
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
}
