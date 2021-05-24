package bst;
/*
    Given a binary tree root, a ZigZag path for a binary tree is defined as follow:

        Choose any node in the binary tree and a direction (right or left).
        If the current direction is right then move to the right child of the current node otherwise move to the left child.
        Change the direction from right to left or right to left.
        Repeat the second and third step until you can't move in the tree.
        Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

        Return the longest ZigZag path contained in that tree.



        Example 1:
        Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
        Output: 3
        Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

        Example 2:
        Input: root = [1,1,1,null,1,null,null,1,1,null,1]
        Output: 4
        Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

        Example 3:
        Input: root = [1]
        Output: 0


        Constraints:

        Each tree has at most 50000 nodes..
        Each node's value is between [1, 100].

        TC O(N)
        SC O(height)
*/
public class LongestZigZagPathInaBinaryTree {
    int res = 0;
    public int longestZigZag(TreeNode root) {
        dfs(root);
        return res == 0 ? 0 : res - 1;
    }

    private int[] dfs(TreeNode root){
        if(root == null){
            int[] ans = new int[2]; // left max path from root : right max path from root
            return ans;
        }
        int[] cur = new int[2];
        int[] l = dfs(root.left);
        cur[0] = l[1] + 1;
        int[] r = dfs(root.right);
        cur[1] += r[0] + 1;
        res = Math.max(res, cur[0]);
        res = Math.max(res, cur[1]);
        return cur;
    }
}
