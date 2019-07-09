package BST;

import java.util.*;

/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example
Example1
Input: {1,2,3,4,5}
Output: [[4, 5, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 / \     
4   5    
Example2
Input: {1,2,3,4}
Output: [[4, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 /
4
*/
public class BinaryTreeLeavesOrderTraversal {
	/*
	 * @param root: the root of binary tree
	 * 
	 * @return: collect and remove all leaves
	 */
	public List<List<Integer>> findLeaves(TreeNode root) {
		// write your code here
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, List<Integer>> depthMap = new HashMap<>(); // depth : list
																// of node
		int maxDepth = getDepth(root, depthMap);
		for (int i = 1; i <= maxDepth; i++) {
			res.add(depthMap.get(i));
		}
		return res;
	}

	private int getDepth(TreeNode root, Map<Integer, List<Integer>> depthMap) {
		if (root == null) {
			return 0;
		}
		int leftDepth = getDepth(root.left, depthMap);
		int rightDepth = getDepth(root.right, depthMap);
		int depth = Math.max(leftDepth, rightDepth) + 1;
		depthMap.putIfAbsent(depth, new ArrayList<>());
		depthMap.get(depth).add(root.val);
		return depth;
	}
}
