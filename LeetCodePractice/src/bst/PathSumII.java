package bst;

import java.util.*;

/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.

A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
 */
public class PathSumII {

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        List<Integer> ls = new ArrayList<>();
        dfs(res, ls, root, targetSum);
        return res;
   }
   
   private void dfs(List<List<Integer>> res, List<Integer> ls, TreeNode root, int sum){
       if(root == null){
           return;
       }
       ls.add(root.val);
       if(root.val == sum && root.left == null && root.right == null){
           res.add(new ArrayList<>(ls));
       } else {
           dfs(res, ls, root.left, sum - root.val);
           dfs(res, ls, root.right, sum - root.val);  
       }
       ls.remove(ls.size() - 1); 
   }
}
