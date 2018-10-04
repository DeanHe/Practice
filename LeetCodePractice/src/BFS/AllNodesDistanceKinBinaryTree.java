package BFS;

import java.util.*;

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
