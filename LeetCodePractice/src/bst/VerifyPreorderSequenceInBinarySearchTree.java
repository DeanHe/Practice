package bst;

import java.util.*;
/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?

Examples:

Input:  pre[] = {2, 4, 3}
Output: true
Given array can represent preorder traversal
of below tree
    2
     \
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
   /   \
 30    80 
  \      \
  35     100 


Input:  pre[] = {40, 30, 35, 20, 80, 100}
Output: false

https://www.lintcode.com/problem/verify-preorder-sequence-in-binary-search-tree/
https://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
 */
public class VerifyPreorderSequenceInBinarySearchTree {
	/**
	 * @param preorder: List[int]
	 * @return: return a boolean
	 */
	public boolean verifyPreorder(int[] preorder) {
	    Stack<Integer> stack = new Stack<>();
	    int root = Integer.MIN_VALUE;
	    for (int n : preorder) {
	        if (n < root) {
	            return false;
	        }
	        while (!stack.empty() && stack.peek() < n) {
	            root = stack.pop();
	        }
	        stack.push(n);
	    }
	    return true;
	}
}
