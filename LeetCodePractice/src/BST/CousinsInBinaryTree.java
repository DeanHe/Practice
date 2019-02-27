package BST;
/*In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.
https://leetcode.com/problems/cousins-in-binary-tree/
*/

import java.util.HashMap;
import java.util.Map;

public class CousinsInBinaryTree {
	Map<Integer, Integer> depth;
	Map<Integer, TreeNode> parent;
	public boolean isCousins(TreeNode root, int x, int y) {
		depth = new HashMap<>();
		parent = new HashMap<>();
		dfs(root, null);
		return depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y);
    }
	private void dfs(TreeNode root, TreeNode par){
		if(root == null){
			return;
		}
		if(par == null){
			depth.put(root.val, 0);
		} else {
			int parentDepth = depth.get(par.val);
			depth.put(root.val, parentDepth + 1);
		}
		parent.put(root.val, par);
		dfs(root.left, root);
		dfs(root.right, root);
	}
}
