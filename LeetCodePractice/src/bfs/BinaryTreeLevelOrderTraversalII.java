package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
*/

public class BinaryTreeLevelOrderTraversalII {
	/**
	 * @param root:
	 *            The root of binary tree.
	 * @return: Level order a list of lists of integer
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()){
			int sz = q.size();
			List<Integer> layer = new ArrayList<>();
			for(int i = 0; i < sz; i++){
				TreeNode cur = q.poll();
				layer.add(cur.val);
				if(cur.left != null){
					q.offer(cur.left);
				}
				if(cur.right != null){
					q.offer(cur.right);
				}
			}
			res.add(0, layer);
		}
		return res;
	}
}