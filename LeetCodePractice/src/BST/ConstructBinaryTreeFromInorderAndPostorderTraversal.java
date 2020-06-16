package BST;

import java.util.*;
/*Given inorder and postorder traversal of a tree, construct the binary tree.

You may assume that duplicates do not exist in the tree.

Given inorder [1,2,3] and postorder [1,3,2], return a tree:
*/
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
	
	/**
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        if(inorder.length != postorder.length){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = inorder.length;
        for(int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        return helper(inorder, postorder, map, 0, len - 1, 0 , len - 1);
    }
    public TreeNode helper(int[] in, int[] post, HashMap<Integer, Integer> map, int inS, int inE, int postS, int postE){
        if(inS > inE || postS > postE){
            return null;
        }
        TreeNode root = new TreeNode(post[postE]);
        int idx = map.get(root.val);
        root.left = helper(in, post, map, inS, idx - 1, postS, postS + idx - inS - 1);
        root.right = helper(in, post, map, idx + 1, inE, postS + idx -inS,  postE - 1);
        return root;
    }
}
