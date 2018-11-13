package BST;

import java.util.*;

/*Given a binary tree, return the inorder traversal of its nodes' values.
*/
public class BinaryTreeInorderTraversal {
	class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * @param root:
	 *            The root of binary tree.
	 * @return: Inorder in ArrayList which contains node values.
	 */
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		// write your code here
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				res.add(cur.val);
				cur = cur.right;
			}
		}
		return res;
	}
}
