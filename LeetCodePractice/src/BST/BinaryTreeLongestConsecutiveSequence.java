package BST;

/*Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example
For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.*/
public class BinaryTreeLongestConsecutiveSequence {
	/**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
    	return dfs(root, null, 0);
    }
    
    private int dfs(TreeNode root, TreeNode parent, int accumulated){
    	if(root == null){
    		return 0;
    	}
    	int length = 1;
    	if(parent != null && parent.val + 1 == root.val){
    		length = accumulated + 1;
    	}
    	int leftMax = dfs(root.left, root, length);
    	int rightMax = dfs(root.right, root, length);
    	int temp = Math.max(leftMax, rightMax);
    	return Math.max(length, temp);
    }
    
    ///////////////// using result type ////////////////////////
    
    public int longestConsecutive2(TreeNode root) {
    	return dfs2(root).maxLen;
    }
    
    private ResultType dfs2(TreeNode root){
    	if(root == null){
    		return new ResultType(0, 0);
    	}
    	ResultType leftResult = dfs2(root.left);
    	ResultType rightResult = dfs2(root.right);
    	int maxLen = 1, singleLen = 1;
    	if(root.left != null && root.left.val == root.val + 1){
    		singleLen = Math.max(singleLen, leftResult.singleLen + 1);
    	}
    	if(root.right != null && root.right.val == root.val + 1){
    		singleLen = Math.max(singleLen, rightResult.singleLen + 1);
    	}
    	maxLen = singleLen;
    	maxLen = Math.max(maxLen, Math.max(leftResult.maxLen, rightResult.maxLen));
    	return new ResultType(maxLen, singleLen);
    }
    
    class ResultType {
    	//singleLen from this node, maxLen below this node.
    	public int maxLen, singleLen;
    	public ResultType(int maxLen, int singleLen){
    		this.maxLen = maxLen;
    		this.singleLen = singleLen;
    	}
    }
}
