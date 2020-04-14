package BST;
/*
Given a binary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree

Example
    1
   / \
  2   0
 /
3
Return 4 // 0-1-2-3
*/
public class BinaryTreeLongestConsecutiveSequenceII {
	/**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        // write your code here
    	return dfs(root).maxLen;
    	
    }
    
    private ResultType dfs(TreeNode root){
    	if(root == null){
    		return new ResultType(0, 0, 0);
    	}
    	ResultType leftResult = dfs(root.left);
    	ResultType rightResult = dfs(root.right);
    	int maxLen = 0, maxDecrease = 0, maxIncrease = 0;
    	if(root.left != null && root.left.val == root.val + 1){
    		maxIncrease = Math.max(maxIncrease, leftResult.maxIncrease + 1);
    	}
    	if(root.right != null && root.right.val == root.val + 1){
    		maxIncrease = Math.max(maxIncrease, rightResult.maxIncrease + 1);
    	}
    	if(root.left != null && root.left.val == root.val - 1){
    		maxDecrease = Math.max(maxDecrease, leftResult.maxDecrease + 1);
    	}
    	if(root.right != null && root.right.val == root.val - 1){
    		maxDecrease = Math.max(maxDecrease, rightResult.maxDecrease + 1);
    	}
    	maxLen = 1 + maxDecrease + maxIncrease;
    	maxLen = Math.max(maxLen, Math.max(leftResult.maxLen, rightResult.maxLen));
    	return new ResultType(maxLen, maxDecrease, maxIncrease);
    }
    
    class ResultType {
    	//maxDecrease from this node, maxIncrease from this node.
    	public int maxLen, maxDecrease, maxIncrease;
    	public ResultType(int maxLen, int maxDecrease, int maxIncrease){
    		this.maxLen = maxLen;
    		this.maxDecrease = maxDecrease;
    		this.maxIncrease = maxIncrease;
    	}
    }
}
