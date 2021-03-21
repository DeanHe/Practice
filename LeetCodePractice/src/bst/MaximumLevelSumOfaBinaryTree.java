package bst;

import java.util.*;

/*Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

Example 1:

Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5*/
public class MaximumLevelSumOfaBinaryTree {
	public int maxLevelSum(TreeNode root) {
        int maxLvlSum = Integer.MIN_VALUE, lvl = 0, resLvl = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
        	int size = queue.size();
        	int lvlSum = 0;
        	lvl++;
        	for(int i = 0; i < size; i++){
        		TreeNode cur = queue.poll();
        		lvlSum += cur.val;
        		if(cur.left != null){
        			queue.offer(cur.left);
        		}
        		if(cur.right != null){
        			queue.offer(cur.right);
        		}
        	}
        	if(lvlSum > maxLvlSum){
        		maxLvlSum = lvlSum;
        		resLvl = lvl;
        	}
        }
        return resLvl;
    }
}
