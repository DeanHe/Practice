package BST;
/*Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

        Assume a BST is defined as follows:
        The left subtree of a node contains only nodes with keys less than the node's key.
        The right subtree of a node contains only nodes with keys greater than the node's key.
        Both the left and right subtrees must also be binary search trees.

        Example 1:
        Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Output: 20
        Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.

        Example 2:
        Input: root = [4,3,null,1,2]
        Output: 2
        Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.

        Example 3:
        Input: root = [-4,-2,-5]
        Output: 0
        Explanation: All values are negatives. Return an empty BST.

        Example 4:
        Input: root = [2,1,3]
        Output: 6

        Example 5:
        Input: root = [5,4,8,3,null,6,3]
        Output: 7

        Constraints:

        Each tree has at most 40000 nodes..
        Each node's value is between [-4 * 10^4 , 4 * 10^4].*/
public class MaximumSumBSTinBinaryTree {
    int maxSum = Integer.MIN_VALUE;
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum >= 0 ? maxSum : 0;
    }

    private BSTinfo dfs(TreeNode root) {
        if(root == null){
            return new BSTinfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }
        BSTinfo leftInfo = dfs(root.left);
        BSTinfo rightInfo = dfs(root.right);
        int min = Math.min(root.val, leftInfo.min);
        int max = Math.max(root.val, rightInfo.max);
        int sum = leftInfo.sum + rightInfo.sum + root.val;
        // validate BST node
        if(leftInfo.isBST && rightInfo.isBST && root.val > leftInfo.max && root.val < rightInfo.min){
            maxSum = Math.max(maxSum, sum);
            return new BSTinfo(min, max, sum, true);
        } else {
            return new BSTinfo(min, max, -1, false);
        }
    }

    class BSTinfo {
        int min, max, sum;
        boolean isBST;
        BSTinfo(int min, int max, int sum, boolean isBST){
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }
    }
}
