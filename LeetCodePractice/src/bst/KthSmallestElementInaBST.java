package bst;

import java.util.Stack;

/*Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example
Given root = {1,#,2}, k = 2, return 2.

Challenge
What if the bst is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

Notice
You may assume k is always valid, 1 ≤ k ≤ bst's total elements.

analysis:
leverage inorder iterative traversal
*/
public class KthSmallestElementInaBST {
	/**
     * @param root: the given bst
     * @param k: the given k
     * @return: the kth smallest element in bst
     */
    public int kthSmallest(TreeNode root, int k) {
    	//Inorder traversal
    	if(root == null){
    		return -1;
    	}
    	int count = 0;
    	TreeNode cur = root;
    	Stack<TreeNode> stack = new Stack<>();
    	while(cur != null || !stack.isEmpty()){
    		if(cur != null){
    			stack.push(cur);
    			cur = cur.left;
    		} else {
    			cur = stack.pop();
    			count++;
        		if(count == k){
        			return cur.val;
        		}
        		cur = cur.right;
    		}
    	}
    	return -1;
    }
}
