package bst;
/*
Given a binary tree, determine if it is a valid binary search tree (bst).

Assume a bst is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
