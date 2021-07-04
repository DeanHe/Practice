package bst;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example: Given binary tree

          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].

Explanation:

Removing the leaves [4, 5, 3] would result in this tree:


       1
      /
     2
Now removing the leaf [2] would result in this tree:
       1
Now removing the leaf [1] would result in the empty tree:
          []
Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private int dfs(List<List<Integer>> res, TreeNode root) {
        if(root == null){
            return -1;
        }
        int l = dfs(res, root.left);
        int r = dfs(res, root.right);
        int lvl = Math.max(l, r) + 1;
        if(res.size() <= lvl){
            res.add(new ArrayList<>());
        }
        res.get(lvl).add(root.val);
        return lvl;
    }
}

