package BST;

//Given a BST, find the path with the minimum sum from root to leaves.
public class MinimumSumPath {
	/**
     * @param root: the root
     * @return: minimum sum
     */
    int ans = Integer.MAX_VALUE;
    public int minimumSum(TreeNode root) {
        // Write your code here
        dfs(root, 0);
        return ans;
    }
    private void dfs(TreeNode root, int sum){
        if(root == null){
            return;
        }
        sum += root.val;
        if(root.left == null && root.right == null){
            if(sum < ans){
                ans =sum;
            }
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
}
