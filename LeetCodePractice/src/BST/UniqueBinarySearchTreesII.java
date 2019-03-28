package BST;

import java.util.*;

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
