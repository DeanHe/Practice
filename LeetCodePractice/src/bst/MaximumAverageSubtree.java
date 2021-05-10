package bst;
/*
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values,
divided by the number of nodes.)



Example 1:



Input: [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.


Note:

The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.

analysis:
bottom to top return {sum, count}
 */
public class MaximumAverageSubtree {
	private double maxAvg = Double.MIN_VALUE;

	public double maximumAverageSubtree(TreeNode root) {
		dfs(root);
		return maxAvg;
	}
    private int[] dfs(TreeNode root){
    	if(root == null){
    		return new int[2];
    	}
    	int[] l = dfs(root.left);
		int[] r = dfs(root.right);
    	int sum = l[0] + r[0] + root.val;
    	int size = l[1] + r[1] + 1;
    	double avg = sum * 1.0 / size;
    	if(avg > maxAvg){
    		maxAvg = avg;
    	}
    	return new int[]{sum, size};
    }
}
