package bst;

import java.util.*;
/*
#95
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n.
Return the answer in any order.



Example 1:


Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 8
 */
public class UniqueBinarySearchTreesII {
	/**
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        // write your code here
    	return helper(1, n);
    }
    private List<TreeNode> helper(int start, int end){
    	List<TreeNode> res = new ArrayList<>();
    	if(start > end){
    		res.add(null);
    		return res;
    	}
    	for(int i = start; i <= end; i++){
    		List<TreeNode> leftCandidates = helper(start, i - 1);
    		List<TreeNode> rightCandidates = helper(i + 1, end);
    		for(TreeNode l : leftCandidates){
    			for(TreeNode r : rightCandidates){
    				TreeNode root = new TreeNode(i);
    				root.left = l;
    				root.right = r;
    				res.add(root);
    			}
    		}
    		
    	}
    	return res;
    }
}
