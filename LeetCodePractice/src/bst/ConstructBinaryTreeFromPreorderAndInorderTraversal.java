package bst;

import java.util.*;
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

You may assume that duplicates do not exist in the tree.

Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree
*/
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
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for(int i = 0; i < len; i++){
	            map.put(inorder[i], i);
	        }
	        return helper(preorder, inorder, map, 0, len-1, 0, len-1);
	        
	    }
	    // need to index for partition
	    public TreeNode helper(int[] preorder, int[] inorder, HashMap<Integer, Integer> map, int preS, int preE, int inS, int inE){
	        if(preS > preE || inS > inE){
	            return null;
	        }
	        TreeNode root = new TreeNode(preorder[preS]);
	        int idx = map.get(root.val);
	        root.left = helper(preorder, inorder, map, preS + 1, preS + (idx - inS), inS, idx - 1);
	        root.right = helper(preorder, inorder, map, preS + (idx - inS) + 1, preE, idx + 1, inE);
	        return root;
	    }
}
