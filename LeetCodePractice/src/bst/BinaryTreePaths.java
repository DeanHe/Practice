package bst;
/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
*/

import java.util.*;

public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null){
        	return res;
        }
        dfs(root, res, "");
        return res;
    }
    
    private void dfs(TreeNode root, List<String> res, String path){
        if(root == null){
        	return;
        }
        path = path + String.valueOf(root.val);
        if(root.left == null && root.right == null){
        	res.add(path);
        }
        if(root.left != null){
        	dfs(root.left, res, path + "->");
        }
        if(root.right != null){
        	dfs(root.right, res, path + "->");
        }
    }
}
