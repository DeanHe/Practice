package BST;
/*Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
Example
Given root = {1}, target = 4.428571, return 1.

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.*/
public class ClosestBinarySearchTreeValue {
	
	public int closestValue(TreeNode root, double target) {
        // write your code here
		if(root == null){
			return 0;
		}		
		TreeNode lowerNode = lowerBound(root, target);
		TreeNode upperNode = upperBound(root, target);
		
		if(lowerNode == null){
			return upperNode.val;
		}
		if(upperNode == null){
			return lowerNode.val;
		}
		if(target - lowerNode.val > upperNode.val - target){
			return upperNode.val;
		} else {
			return lowerNode.val;
		}
    }
	// find the node with the largest value that smaller than target
	private TreeNode lowerBound(TreeNode root, double target) {
		if(root == null){
			return null;
		}
		if(root.val >= target){
			return lowerBound(root.left, target);
		} else {
			TreeNode lowerNode = lowerBound(root.right, target);
			if(lowerNode != null){
				return lowerNode;
			} else {
				return root;
			}
		}
	}
	// find the node with the smallest value that greater than target
	private TreeNode upperBound(TreeNode root, double target) {
		if(root == null){
			return null;
		}
		if(root.val < target){
			return upperBound(root.right, target);
		} else {
			TreeNode upperNode = upperBound(root.left, target);
			if(upperNode != null){
				return upperNode;
			} else {
				return root;
			}
		}
	}
}
