package BST;

public class SubtreeWithMaximumAverage {
	private class ResultType {
		int sum, size;
		public ResultType(int sum, int size) {
			this.sum = sum;
			this.size = size;
		}
	}
	private TreeNode res = null;
	private double maxAvg = Double.MIN_VALUE;
	/**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
    	dfs(root);
    	return res;
    	
    }
    private ResultType dfs(TreeNode root){
    	if(root == null){
    		return new ResultType(0, 0);
    	}
    	ResultType leftRes = dfs(root.left);
    	ResultType rightRes = dfs(root.right);
    	int sum = leftRes.sum + root.val + rightRes.sum;
    	int size = leftRes.size + 1 + rightRes.size;
    	double avg = sum * 1.0 / size;
    	if(avg > maxAvg){
    		maxAvg = avg;
    		res = root;
    	}
    	return new ResultType(sum, size);
    }
}
