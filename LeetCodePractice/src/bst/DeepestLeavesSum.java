package bst;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return the sum of values of its deepest leaves.


Example 1:


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
Example 2:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19


Constraints:

The number of nodes in the tree is in the range [1, 10^4].
1 <= Node.val <= 100
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            res = 0;
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                res += cur.val;
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
}

