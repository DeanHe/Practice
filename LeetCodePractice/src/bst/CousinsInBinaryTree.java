package bst;
/*In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.
https://leetcode.com/problems/cousins-in-binary-tree/
*/

import java.util.HashMap;
import java.util.Map;

public class CousinsInBinaryTree {
	Map<Integer, Integer> depthMap;
	Map<Integer, TreeNode> parentMap;
	public boolean isCousins(TreeNode root, int x, int y) {
		depthMap = new HashMap<>();
		parentMap = new HashMap<>();
		dfs(root, 0,null);
		return x != y && depthMap.get(x) == depthMap.get(y) && parentMap.get(x) != parentMap.get(y);
    }
	private void dfs(TreeNode root, int depth, TreeNode parent){
		if(root == null){
			return;
		}
		depthMap.put(root.val, depth);
		parentMap.put(root.val, parent);
		dfs(root.left, depth + 1, root);
		dfs(root.right,depth + 1, root);
	}
}
