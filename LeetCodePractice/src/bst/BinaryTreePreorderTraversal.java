package bst;

import java.util.*;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/
public class BinaryTreePreorderTraversal {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                res.add(cur.val);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return res;
    }

    public ArrayList<Integer> preorderTraversalRecursion(TreeNode root) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;

    }

    public void helper(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }

        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
