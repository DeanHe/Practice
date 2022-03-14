package bst;
/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1
*/

import java.util.Stack;

public class ValidateBinarySearchTree {
	
	public boolean isValidBST(TreeNode root) {
		if(root == null){
            return true;
        }
        return isBSTHelper(root, null, null);
    }
	private boolean isBSTHelper(TreeNode root, TreeNode minNode, TreeNode maxNode) {
		if(root == null){
            return true;
        }
        if((minNode !=null && root.val <= minNode.val) || (maxNode !=null && root.val >= maxNode.val)){
            return false;
        }
        boolean left = isBSTHelper(root.left, minNode, root);
        boolean right = isBSTHelper(root.right, root, maxNode);
        return left && right;
	}

    public boolean isValidBSTiterative(TreeNode root) {
	    TreeNode pre = null;
	    Stack<TreeNode> st = new Stack<>();
	    while(root != null || !st.isEmpty()){
	        if(root != null){
	            st.push(root);
	            root = root.left;
            } else {
                root = st.pop();
	            if(pre != null && pre.val < root.val){
	                return false;
                }
	            pre = root;
	            root = root.right;
            }
        }
	    return true;
    }
}
