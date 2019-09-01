package BST;

import java.util.*;

/*Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. The path does not need to start or end at the root or a leaf, but it must go in a straight line down.

Example
Example 1:

Input:
{1,2,3,4,#,2}
6
Output:
[[2, 4],[1, 3, 2]]
Explanation:
The binary tree is like this:
    1
   / \
  2   3
 /   /
4   2
for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
Example 2:

Input:
{1,2,3,4}
10
Output:
[]
Explanation:
The binary tree is like this:
    1
   / \
  2   3
 /   
4   
for target 10, there is no way to reach it.*/
public class BinaryTreePathSumII {
	/*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
	int target;
	List<List<Integer>> res;
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
    	res = new ArrayList<>();
    	this.target = target;
    	dfs(root, new ArrayList<>());
    	return res;
    }
    
    private void dfs(TreeNode root, List<Integer> path){
    	if(root == null){
    		return;
    	}
    	int sum = target;
    	path.add(root.val);
    	int len = path.size();
    	for(int i = len - 1; i >= 0; i--){
    		sum -= path.get(i);
    		if(sum == 0){
    			List<Integer> cand = new ArrayList<>();
    			for(int j = i; j < len; j++){
    				cand.add(path.get(j));
    			}
    			res.add(cand);
    		}
    	}
    	dfs(root.left, path);
    	dfs(root.right, path);
    	path.remove(path.size() - 1);
    }
}
