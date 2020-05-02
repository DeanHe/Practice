package BST;

import java.util.Stack;

/*
    Return the root node of a binary search tree that matches the given preorder traversal.
    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.
    left has a value < node.val, and any descendant of node.right has a value > node.val.
    Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

        Example 1:
        Input: [8,5,1,7,10,12]
        Output: [8,5,10,1,7,null,12]

        Note:

        1 <= preorder.length <= 100
        The values of preorder are distinct.
*/
public class ConstructBinarySearchTreeFromPreorderTraversal {
    int idx;
    int[] preorder;

    public TreeNode bstFromPreorder(int[] preorder) {
        this.idx = 0;
        this.preorder = preorder;
        return dfs(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode dfs(int lower, int upper) {
        if (idx == preorder.length || preorder[idx] < lower || preorder[idx] > upper) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[idx]);
        idx++;
        node.left = dfs(lower, node.val);
        node.right = dfs(node.val, upper);
        return node;
    }

    /* method 2 */
    public TreeNode bstFromPreorderIterative(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            if (node.val < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = stack.peek();
                while (!stack.isEmpty() && stack.peek().val < node.val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }
}
