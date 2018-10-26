package BST;

import java.util.*;

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
public class PathSumII {
	class TreeNode {
		public int val;
		public TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        dfs(res, temp, root, sum);
        return res;
   }
   
   private void dfs(List<List<Integer>> res, List<Integer> temp, TreeNode root, int sum){
       if(root == null){
           return;
       }
       temp.add(root.val);
       if(root.val == sum && root.left == null && root.right == null){
           res.add(new ArrayList<Integer>(temp));
       } else {
           dfs(res, temp, root.left, sum - root.val);
           dfs(res, temp, root.right, sum - root.val);  
       }
       temp.remove(temp.size() - 1); 
   }
}
