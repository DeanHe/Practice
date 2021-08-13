package bst;
/*
Given a binary tree, the val of each node can be either 1 or 0, and the node's val equals leftchild's val & rightchild's val
suppose a leaf node val is flipped, return the impacted tree
 */
public class FlipBinaryTreeLeafFacebook {
    public TreeNode flipLeaf(TreeNode root, TreeNode leaf){
        if(root == leaf){
            root.val = 1 - root.val;
            return root;
        }
        if(root.left == null && root.right == null){
            return root;
        }
        TreeNode left = flipLeaf(root.left, leaf);
        TreeNode right = flipLeaf(root.right, leaf);
        root.val = left.val & right.val;
        return root;
    }
}

