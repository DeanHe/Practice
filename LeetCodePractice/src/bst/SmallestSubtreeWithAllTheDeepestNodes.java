package bst;

import java.util.*;

/*
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.
 

Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.

TC O(N)
SC O(N)
*/
public class SmallestSubtreeWithAllTheDeepestNodes {
	Map<TreeNode, Integer> dist = new HashMap<>();
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		if (root == null) {
			return null;
		}
		int leftDepth = depth(root.left);
		int rightDepth = depth(root.right);
		if (leftDepth == rightDepth) {
			return root;
		} else if (leftDepth > rightDepth) {
			return subtreeWithAllDeepest(root.left);
		} else {
			return subtreeWithAllDeepest(root.right);
		}
	}

	private int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (dist.containsKey(root)) {
			return dist.get(root);
		}
		int l = depth(root.left);
		int r = depth(root.right);
		dist.put(root, Math.max(l, r) + 1);
		return dist.get(root);
	}

	public TreeNode subtreeWithAllDeepest2(TreeNode root) {
		return dfs(root).node;
	}

	private Result dfs(TreeNode root) {
		if(root == null){
			return new Result(root, 0);
		}
		Result l = dfs(root.left);
		Result r = dfs(root.right);
		if(l.depth == r.depth){
			return new Result(root, l.depth + 1);
		} else if(l.depth > r.depth){
			return new Result(l.node, l.depth + 1);
		} else {
			return new Result(r.node, r.depth + 1);
		}
	}


	class Result {
		TreeNode node;
		int depth;
		public Result(TreeNode node, int depth){
			this.node = node;
			this.depth = depth;
		}
	}
}
