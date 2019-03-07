package BST;
/*Given a binary tree, task is to find subtree with maximum sum in tree.

Examples:

Input :       1
            /   \
           2      3
          / \    / \
         4   5  6   7
Output : 28
As all the tree elements are positive,
the largest subtree sum is equal to
sum of all tree elements.

Input :       1
            /    \
          -2      3
          / \    /  \
         4   5  -6   2
Output : 7
Subtree with largest sum is :  -2
                             /  \ 
                            4    5
Also, entire tree sum is also 7.*/
public class FindMaximumSubtree {
	public TreeNode result = null;
	public int maximum = Integer.MIN_VALUE;
	public TreeNode findSubtree(TreeNode root) {
	    helper(root);
	    return result;
	}
	private int helper(TreeNode root){
	    if(root == null){
	        return 0;
	    }
	    int left = helper(root.left);
	    int  right = helper(root.right);
	    if(result == null || left + right + root.val > maximum){
	        maximum = left + right + root.val;
	        result = root;
	    }
	    return left + right + root.val;
	}
}
