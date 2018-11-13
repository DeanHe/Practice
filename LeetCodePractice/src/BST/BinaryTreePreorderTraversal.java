package BST;

import java.util.*;

//Given a binary tree, return the preorder traversal of its nodes' values.
public class BinaryTreePreorderTraversal {
	class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;

		TreeNode(int x) {
			val = x;
		}
	}
	/**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
       Stack<TreeNode> stack = new Stack<TreeNode>();
       if(root == null){
           return res;
       }
       stack.push(root);
       while(!stack.isEmpty()){
           TreeNode node = stack.pop();
           res.add(node.val);
           // first right later left as it is preordr
           if(node.right != null){
               stack.push(node.right);
           }
           if(node.left != null){
               stack.push(node.left);
           }
       }
       return res;
    }
}
