package BST;

import java.util.*;
//Given a binary tree, return the postorder traversal of its nodes' values.
public class BinaryTreePostorderTraversal {

	/**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
     //http://www.programcreek.com/2012/12/leetcode-solution-of-iterative-binary-tree-postorder-traversal-in-java/
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if(temp.left == null && temp.right == null){
                TreeNode n = stack.pop();
                res.add(n.val);
            } else {
                if(temp.right != null){
                    stack.push(temp.right);
                    temp.right = null;
                }
                if(temp.left != null){
                    stack.push(temp.left);
                    temp.left = null;
                }
            }
        }
        return res;
    }
}
