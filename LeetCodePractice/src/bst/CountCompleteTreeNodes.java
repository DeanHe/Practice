package bst;
/*
Given a complete binary tree, count the number of nodes.

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

analysis:
case 1: left subtree height equals right subtree height -> left subtree is complete tree
case 2: left subtree height not equals right subtree height -> right subtree is complete tree
T(n) = T(n / 2) + theta(log(n))
time complexity: O(log(n)^2)
Binary Search on leaves = log(N + 1)
height = log(2N + 1)
*/

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == rightHeight) { // forming a perfect binary tree
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            return (1 << (leftHeight - 1)) + countNodes(root.left);
        }
    }

    private int getHeight(TreeNode root) {
        int res = 0;
        while(root != null){
            root = root.left;
            res++;
        }
        return res;
    }
}
