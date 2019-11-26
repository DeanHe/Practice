package BST;
/*Given a complete binary tree, count the number of nodes.

        Note:

        Definition of a complete binary tree from Wikipedia:
        In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

        Example:

        Input:
        1
        / \
        2   3
        / \  /
        4  5 6

        Output: 6
*/

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int res = 0;
        int leftHeight = getLeftHeight(root.left);
        int rightHeight = getRightHeight(root.right);
        if(leftHeight == rightHeight){
            return (2 << rightHeight) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int getLeftHeight(TreeNode root){
        return root == null ? 0 : 1 + getLeftHeight(root.left);
    }

    private int getRightHeight(TreeNode root){
        return root == null ? 0 : 1 + getRightHeight(root.right);
    }
}
