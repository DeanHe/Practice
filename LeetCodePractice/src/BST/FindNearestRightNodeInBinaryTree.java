package BST;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree and a node u in the tree,
return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.

Example 1:
Input: root = [1,2,3,null,4,5,6], u = 4
Output: 5
Explanation: The nearest node on the same level to the right of node 4 is node 5.

Example 2:
Input: root = [3,null,4,2], u = 2
Output: null
Explanation: There are no nodes to the right of 2.

Example 3:
Input: root = [1], u = 1
Output: null

Example 4:
Input: root = [3,4,2,null,null,null,1], u = 4
Output: 2

Constraints:
The number of nodes in the tree is in the range [1, 10^5].
1 <= Node.val <= 10^5
All values in the tree are distinct.
u is a node in the binary tree rooted at root.

analysis:
BFS
 */
public class FindNearestRightNodeInBinaryTree {
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int len = q.size();
            for(int i = 0; i < len; i++){
                TreeNode cur = q.poll();
                if(i + 1 < len && cur.val == u.val){
                    return q.peek();
                }
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
            }
        }
        return null;
    }
}
