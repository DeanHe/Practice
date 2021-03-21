package bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:

Note:

1 <= N <= 20*/
public class AllPossibleFullBinaryTrees {
	Map<Integer, List<TreeNode>> cache = new HashMap<>();

	public List<TreeNode> allPossibleFBT(int N) {
		List<TreeNode> res = new ArrayList<>();
		if (N % 2 == 0) {
			return res;
		}
		if (N == 1) {
			TreeNode root = new TreeNode(0);
			res.add(root);
			return res;
		}
		if (cache.containsKey(N)) {
			return cache.get(N);
		}
		N--;
		for (int i = 1; i < N; i += 2) {
			List<TreeNode> leftPool = allPossibleFBT(i);
			List<TreeNode> rightPool = allPossibleFBT(N - i);
			for (TreeNode left : leftPool) {
				for (TreeNode right : rightPool) {
					TreeNode root = new TreeNode(0);
					root.left = left;
					root.right = right;
					res.add(root);
				}
			}
		}
		cache.put(N, res);
		return res;
	}
}
