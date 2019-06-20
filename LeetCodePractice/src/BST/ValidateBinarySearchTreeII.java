package BST;

import java.util.*;
/*
 Given a binary tree pre-order array, determine if it is a valid binary search tree (BST).
 */
public class ValidateBinarySearchTreeII {
	public boolean isValidBST(int[] pre) {
	    Stack<Integer> stack = new Stack<>();
	    int root = Integer.MIN_VALUE, n = pre.length;
	    for (int i = 0; i < n; i++) {
	        if (pre[i] < root) {
	            return false;
	        }
	        while (!stack.empty() && stack.peek() < pre[i]) {
	            root = stack.peek();
	            stack.pop();
	        }
	        stack.push(pre[i]);
	    }
	    return true;
	}
}
