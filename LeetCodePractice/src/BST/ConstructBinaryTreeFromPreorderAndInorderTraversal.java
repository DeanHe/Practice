package BST;

import java.util.*;
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

You may assume that duplicates do not exist in the tree.

Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree*/
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {	 
	  /**
	     *@param preorder : A list of integers that preorder traversal of a tree
	     *@param inorder : A list of integers that inorder traversal of a tree
	     *@return : Root of a tree
	     */
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	        // write your code here
	        if(preorder.length != inorder.length){
	            return null;
	        }
	        int len = inorder.length;
	        // in order value : index
	        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	        for(int i = 0; i < len; i++){
	            map.put(inorder[i], i);
	        }
	        return helper(preorder, inorder, map, 0, len-1, 0, len-1);
	        
	    }
	    // need to index for partition
	    public TreeNode helper(int[] preorder, int[] inorder, HashMap<Integer, Integer> map, int preStart, int preEnd, int inStart, int inEnd){
	        if(preStart > preEnd || inStart > inEnd){
	            return null;
	        }
	        TreeNode root = new TreeNode(preorder[preStart]);
	        int inorderIndex = map.get(root.val);
	        root.left = helper(preorder, inorder, map, preStart + 1, preStart + (inorderIndex - inStart), inStart, inorderIndex - 1);
	        root.right = helper(preorder, inorder, map, preStart + (inorderIndex - inStart) + 1, preEnd, inorderIndex + 1, inEnd);
	        return root;
	    }
}
