package BST;

import java.util.*;

/*Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?*/
public class BinaryTreePreorderTraversal {
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
    
    public ArrayList<Integer> preorderTraversalRecursion(TreeNode root) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;

    }
    
    public void helper(TreeNode root, ArrayList<Integer> res){
        if(root == null){
            return;
        } 
   
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
        return;
    }
}
