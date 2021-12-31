package bst;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary search tree, return a balanced binary search tree with the same node values.
A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
If there is more than one answer, return any of them.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.


Constraints:
The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
*/
public class BalanceaBinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        inOrderTraverse(sorted, root);
        return dfs(sorted, 0, sorted.size() - 1);
    }

    private void inOrderTraverse(List<Integer> sorted, TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(sorted, root.left);
        sorted.add(root.val);
        inOrderTraverse(sorted, root.right);
    }

    private TreeNode dfs(List<Integer> sorted, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = dfs(sorted, start, mid - 1);
        root.right = dfs(sorted, mid + 1, end);
        return root;
    }
}
