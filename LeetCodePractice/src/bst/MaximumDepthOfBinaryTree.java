package bst;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Note: A leaf is a node with no children.

Example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
*/
public class MaximumDepthOfBinaryTree {
	public int maxDepth(TreeNode root) {
        if(root == null){
        	return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
	
	public int maxDepthBFS(TreeNode root) {
        if(root == null){
        	return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
        	int size = queue.size();
        	for(int i = 0; i < size; i++){
        		TreeNode cur = queue.poll();
        		if(cur.left != null){
        			queue.offer(cur.left);
        		}
        		if(cur.right != null){
        			queue.offer(cur.right);
        		}
        	}
        	depth++;
        }
        return depth;
    }
}
