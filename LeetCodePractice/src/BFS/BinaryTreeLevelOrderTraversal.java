package BFS;

import java.util.*;
/*Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]*/

public class BinaryTreeLevelOrderTraversal {
	/**
	 * @param root:
	 *            The root of binary tree.
	 * @return: Level order a list of lists of integer
	 */
	// DFS method
	public ArrayList<ArrayList<Integer>> levelOrderDFS(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		helper(res, root, 0);
		return res;
	}

	private void helper(ArrayList<ArrayList<Integer>> res, TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (res.size() > level) {
			res.get(level).add(root.val);
		} else {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(root.val);
			res.add(temp);
		}
		helper(res, root.left, level + 1);
		helper(res, root.right, level + 1);
	}

	// BFS
	public ArrayList<ArrayList<Integer>> levelOrderBFS(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			ArrayList<Integer> level = new ArrayList<>();
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				TreeNode n = queue.poll();
				level.add(n.val);
				if (n.left != null) {
					queue.offer(n.left);
				}
				if (n.right != null) {
					queue.offer(n.right);
				}
			}
			res.add(level);
		}
		return res;
	}
}

class TreeNode {
	public int val;
	public TreeNode left, right;

	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}