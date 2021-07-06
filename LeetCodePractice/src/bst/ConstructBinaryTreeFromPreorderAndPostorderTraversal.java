package bst;

import java.util.*;

/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.
Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Note:
1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.

analysis:
divide and conquer
TC O(N)
*/
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		if(pre.length != post.length) {
			return null;
		}
		Map<Integer, Integer> postOrder_map = new HashMap<>();
		for(int i = 0; i < post.length; i++) {
			postOrder_map.put(post[i], i);
		}
		return helper(pre, post, postOrder_map, 0, pre.length - 1, 0, post.length - 1);
	}
	private TreeNode helper(int[] pre, int[] post, Map<Integer, Integer> postOrder_map, int preStart, int preEnd, int postStart, int postEnd) {
		if(preStart > preEnd || postStart > postEnd) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preStart]);
		if(preStart == preEnd) {
			return root;
		}
		int postOrder_split = postOrder_map.get(pre[preStart + 1]);
		root.left = helper(pre, post, postOrder_map, preStart + 1, preStart + 1 + postOrder_split - postStart, postStart, postOrder_split);
		root.right = helper(pre, post, postOrder_map, preEnd - postEnd + postOrder_split + 2, preEnd, postOrder_split + 1, postEnd - 1);
		return root;
	}

}
