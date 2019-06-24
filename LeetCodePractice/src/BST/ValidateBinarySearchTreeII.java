package BST;

import java.util.*;
/*
Given an array of numbers, return true if given array can represent preorder traversal of a Binary Search Tree, else return false. Expected time complexity is O(n).

Examples:

Input:  pre[] = {2, 4, 3}
Output: true
Given array can represent preorder traversal
of below tree
    2
     
      4
     /
    3

Input:  pre[] = {2, 4, 1}
Output: false
Given array cannot represent preorder traversal
of a Binary Search Tree.

Input:  pre[] = {40, 30, 35, 80, 100}
Output: true
Given array can represent preorder traversal
of below tree
     40
   /   
 30    80 
        
  35     100 


Input:  pre[] = {40, 30, 35, 20, 80, 100}
Output: false
Given array cannot represent preorder traversal
of a Binary Search Tree.

https://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
 */
public class ValidateBinarySearchTreeII {
	public boolean isValidBST(int[] arr) {
	    Stack<Integer> stack = new Stack<>();
	    int root = Integer.MIN_VALUE, n = arr.length;
	    for (int i = 0; i < n; i++) {
	        if (arr[i] < root) {
	            return false;
	        }
	        while (!stack.empty() && stack.peek() < arr[i]) {
	            root = stack.peek();
	            stack.pop();
	        }
	        stack.push(arr[i]);
	    }
	    return true;
	}
}
