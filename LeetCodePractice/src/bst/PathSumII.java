package bst;

import java.util.*;

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
public class PathSumII {

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
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
           res.add(new ArrayList<>(temp));
       } else {
           dfs(res, temp, root.left, sum - root.val);
           dfs(res, temp, root.right, sum - root.val);  
       }
       temp.remove(temp.size() - 1); 
   }
}
