package bst;
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


Example 2:
    3
   / \
  2   2
2-3
*/
public class BinaryTreeLongestConsecutiveSequenceII {
	/**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
    	return dfs(root).maxLen;
    	
    }
    
    private ResultType dfs(TreeNode root){
    	if(root == null){
    		return new ResultType(0, 0, 0);
    	}
    	ResultType leftResult = dfs(root.left);
    	ResultType rightResult = dfs(root.right);
    	int maxLen = 1, maxDec = 1, maxInc = 1;
    	if(root.left != null && root.left.val == root.val + 1){
    		maxInc = Math.max(maxInc, leftResult.maxInc + 1);
    	}
    	if(root.right != null && root.right.val == root.val + 1){
    		maxInc = Math.max(maxInc, rightResult.maxInc + 1);
    	}
    	if(root.left != null && root.left.val == root.val - 1){
    		maxDec = Math.max(maxDec, leftResult.maxDec + 1);
    	}
    	if(root.right != null && root.right.val == root.val - 1){
    		maxDec = Math.max(maxDec, rightResult.maxDec + 1);
    	}
    	maxLen = maxDec + maxInc - 1;
    	maxLen = Math.max(maxLen, Math.max(leftResult.maxLen, rightResult.maxLen));
    	return new ResultType(maxLen, maxDec, maxInc);
    }

    class ResultType {
    	//maxDec from this node, maxInc from this node.
    	public int maxLen, maxDec, maxInc;
    	public ResultType(int maxLen, int maxDec, int maxInc){
    		this.maxLen = maxLen;
    		this.maxDec = maxDec;
    		this.maxInc = maxInc;
    	}
    }
}
