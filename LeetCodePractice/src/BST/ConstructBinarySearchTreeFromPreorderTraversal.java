package BST;

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
        return dfs(Integer.MAX_VALUE);
    }

    private TreeNode dfs(int upperBound) {
        if (idx == preorder.length || preorder[idx] > upperBound) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[idx]);
        idx++;
        node.left = dfs(node.val);
        node.right = dfs(Integer.MAX_VALUE);
        return node;
    }
}
