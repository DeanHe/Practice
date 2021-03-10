package BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode cur, int level) {
        if (cur == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(new LinkedList<>());
        }
        if (level % 2 == 0) {
            res.get(level).add(cur.val);
        } else {
            res.get(level).add(0, cur.val);
        }
        dfs(res, cur.left, level + 1);
        dfs(res, cur.right, level + 1);
    }
}
