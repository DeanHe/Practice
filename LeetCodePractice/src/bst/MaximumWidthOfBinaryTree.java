package bst;

/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
The binary tree has the same structure as a full binary tree, but some nodes are null.
The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input:
1
/   \
3     2
/ \     \
5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input:
1
/
3
/ \
5   3
Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input:

1
/ \
3   2
/
5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input:
1
/ \
3   2
/     \
5       9
/         \
6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer.

analysis:
level order traversal
mark each node index of 1 to n
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> nodeToIdx = new HashMap<>();
        nodeToIdx.put(root, 1);
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            int width = 0, l = 0, r = 0;
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                int idx = nodeToIdx.get(cur);
                if (i == 0) {
                    l = idx;
                }
                if (i == sz - 1) {
                    r = idx;
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                    nodeToIdx.put(cur.left, 2 * idx);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                    nodeToIdx.put(cur.right, 2 * idx + 1);
                }
            }
            width = r - l + 1;
            res = Math.max(width, res);
        }
        return res;
    }
}
