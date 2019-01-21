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
}
