package bst;

/*
Given a binary tree, we install cameras on the nodes of the tree.
Each camera at a node can monitor its parent, itself, and its immediate children.
Calculate the minimum number of cameras needed to monitor all nodes of the tree.

Example 1:
Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:
Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:
The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.

https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
*/

public class BinaryTreeCameras {
	int camera = 0;
    public int minCameraCover(TreeNode root) {
        if(dfs(root) < 1){
        	return camera + 1;
        } else {
        	return camera;
        }
    }
    
    //state 0: node itself should have camera
    //state 1: node's parent should have camera
    //state 2: node's parent should not have camera
    // child 0 -> parent 2; child 1 -> parent 0; child 2 -> parent 1
    private int dfs(TreeNode root){
    	int left, right;
    	if(root.left == null){
    		left = 1;
    	} else {
    		left = dfs(root.left);
    	}
    	if(root.right == null){
    		right = 1;
    	} else {
    		right = dfs(root.right);
    	}
    	if(left == 0 || right == 0){
    		camera++;
    		return 2;
    	}
    	if(left == 2 || right == 2){
    		return 1;
    	} 
    	// if(left == 1 || right == 1)
    	return 0;
    }
}
