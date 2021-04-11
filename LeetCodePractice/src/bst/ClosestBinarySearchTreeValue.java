package bst;
/*
Given a non-empty binary search tree and a target value, find the value in the bst that is closest to the target.
Example
Given root = {1}, target = 4.428571, return 1.

Given target value is a floating point.
You are guaranteed to have only one unique value in the bst that is closest to the target.
*/
public class ClosestBinarySearchTreeValue {
	int res = 0;
	double diff = Double.MAX_VALUE;
	public int closestValue(TreeNode root, double target) {
		dfs(root, target);
		return res;
    }

    private void dfs(TreeNode root, double target) {
		if(root == null){
			return;
		}
		if(Math.abs(root.val - target) < diff){
			res = root.val;
			diff = Math.abs(root.val - target);
		}
		if(root.val > target){
			closestValue(root.left, target);
		} else {
			closestValue(root.right, target);
		}
	}
}
