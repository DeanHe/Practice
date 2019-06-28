package BST;

import java.util.*;

/*Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?*/
public class BinaryTreeInorderTraversal {

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
