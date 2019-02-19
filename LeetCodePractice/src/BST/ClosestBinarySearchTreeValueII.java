package BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Example
Given root = {1}, target = 0.000000, k = 1, return [1].

Challenge
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Notice
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.*/
public class ClosestBinarySearchTreeValueII {
	/**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
    	List<Integer> res = new ArrayList<>();
    	if(root == null){
    		return res;
    	}
    	Stack<Integer> precedessor = new Stack<>();
    	Stack<Integer> successor = new Stack<>();
    	getPredecessor(root, target, precedessor);
    	getSuccessor(root, target, successor);
    	for(int i = 0; i < k; i++){
    		if(precedessor.isEmpty()){
    			res.add(successor.pop());
    		} else if(successor.isEmpty()){
    			res.add(precedessor.pop());
    		} else if(target - (double)precedessor.peek() < (double)successor.peek() - target){
    			res.add(precedessor.pop());
    		} else {
    			res.add(successor.pop());
    		}
    	}
    	return res;
    }
    
    private void getPredecessor(TreeNode root, double target, Stack<Integer> stack){
    	if(root == null){
    		return;
    	}
    	getPredecessor(root.left, target, stack);
    	if(root.val > target){
    		return;
    	}
    	stack.push(root.val);
    	getPredecessor(root.right, target, stack);
    }
    
    private void getSuccessor(TreeNode root, double target, Stack<Integer> stack){
    	if(root == null){
    		return;
    	}
    	getSuccessor(root.right, target, stack);
    	if(root.val <= target){
    		return;
    	}
    	stack.push(root.val);
    	getSuccessor(root.left, target, stack);
    }
    
    
    
    
    
    
	/**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValuesInOrderTraverse(TreeNode root, double target, int k) {
        // write your code here
    	List<Integer> values = new ArrayList<>();
    	inOrderTraverse(root, values);
    	int len = values.size();
    	int i = 0;
    	for(; i < len; i++){
    		if(values.get(i) >= target){
    			break;
    		}
    	}
    	if(i == len){
    		return values.subList(len - k, len);
    	}
    	int left = i - 1, right = i;
    	List<Integer> res = new ArrayList<>();
    	for(int j = 0; j < k; j++){
    		if(left >= 0){
    			if(right < len){
    				if(target - values.get(left) < values.get(right) - target){
    					res.add(values.get(left));
    					left--;
    				} else {
    					res.add(values.get(right));
    					right++;
    				}
    			} 
    		} else {
    			if(right < len){
    				res.add(values.get(right));
    				right++;
    			}
    		}
    	}
    	return res;
    }
    private void inOrderTraverse(TreeNode root, List<Integer> values){
    	if(root == null){
    		return;
    	}
    	inOrderTraverse(root.left, values);
    	values.add(root.val);
    	inOrderTraverse(root.right, values);
    }
}
