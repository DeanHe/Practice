package bst;

import java.util.Stack;

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100


Follow up: Could you solve it both recursively and iteratively?
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return root == null || dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            if (a == null && b == null) {
                return true;
            }
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    }

    public boolean isSymmetricStack(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode a = stack.pop();
            TreeNode b = stack.pop();
            if (a == null && b == null) {
                continue;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }
            stack.push(a.left);
            stack.push(b.right);
            stack.push(a.right);
            stack.push(b.left);
        }
        return true;
    }
}

