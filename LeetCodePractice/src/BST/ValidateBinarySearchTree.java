package BST;
/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/

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
}
