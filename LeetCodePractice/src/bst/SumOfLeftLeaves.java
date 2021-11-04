package bst;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        return dfs(root.left, true) + dfs(root.right, false);
    }

    private int dfs(TreeNode root, boolean isLeft){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null && isLeft){
            return root.val;
        }
        return dfs(root.left, true) + dfs(root.right, false);
    }
}
