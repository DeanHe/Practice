package bst;
//Given a binary tree, find the maximum path sum.
//The path may start and end at any node in the tree
public class BinaryTreeMaximumPathSum {
	
	private class ResultType {
        // singlePath: the max path starts from current node towards bottom, it can be 0
       // maxPath: the max path between any two node in tree, it must be below or pass the current node
        int singlePath;
        int maxPath;
        public ResultType(int singlePath, int maxPath){
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }
	/**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
        ResultType result = helper(root);
        return result.maxPath;
    }
    
    public ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        return new ResultType(singlePath, maxPath);
    }

    //Method 2
    int res = Integer.MIN_VALUE;
    public int maxPathSumII(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right + root.val);
        int temp = Math.max(left, right) + root.val;
        return Math.max(temp, 0);
    }
}
