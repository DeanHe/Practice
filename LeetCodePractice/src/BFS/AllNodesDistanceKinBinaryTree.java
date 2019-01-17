package BFS;

import java.util.*;
/*We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.*/

public class AllNodesDistanceKinBinaryTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		int dist = 0;
		List<Integer> res = new ArrayList<>();
		if (K == 0) {
			res.add(target.val);
			return res;
		}
		Map<TreeNode, ArrayList<TreeNode>> graph = new HashMap<>();
		dfs(root, null, graph);
		Queue<TreeNode> queue = new LinkedList<>();
		HashSet<TreeNode> visited = new HashSet<>();
		queue.offer(target);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				visited.add(cur);
				ArrayList<TreeNode> neighbors = graph.get(cur);
				for (TreeNode nb : neighbors) {
					if (!visited.contains(nb)) {
						queue.offer(nb);
					}
				}
			}
			dist++;
			if (dist == K) {
				for (TreeNode n : queue) {
					res.add(n.val);
				}
				return res;
			}
		}
		return res;
	}

	void dfs(TreeNode cur, TreeNode parent, Map<TreeNode, ArrayList<TreeNode>> graph) {
		if (cur == null) {
			return;
		}
		if (!graph.containsKey(cur)) {
			graph.put(cur, new ArrayList<TreeNode>());
		}
		if (parent != null) {
			graph.get(cur).add(parent);
		}
		if (cur.left != null) {
			graph.get(cur).add(cur.left);
		}
		if (cur.right != null) {
			graph.get(cur).add(cur.right);
		}
		dfs(cur.left, cur, graph);
		dfs(cur.right, cur, graph);
	}
}
