package bst.lca;

import bst.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
different from LCA I is that it
Return null if LCA does not exist.
node A or node B may not exist in tree.

TC O(N)
SC O(h)
 */
public class LowestCommonAncestorOfaBinaryTreeII {
    public TreeNode lowestCommonAncestor(TreeNode root, int a, int b) {
        CounterNode res = helper(root, a, b);
        if (res.cnt == 2) {
            return res.node;
        }
        return null;
    }

    private CounterNode helper(TreeNode root, int a, int b) {
        if (root == null) {
            return new CounterNode(0, null);
        }
        CounterNode left = helper(root.left, a, b);
        if (left.cnt == 2) {
            return left;
        }
        CounterNode right = helper(root.right, a, b);
        if (right.cnt == 2) {
            return right;
        }
        int c = left.cnt + right.cnt;
        if (root.val == a) {
            c++;
        } else if (root.val == b) {
            c++;
        }
        return new CounterNode(c, root);
    }

    private class CounterNode {
        public int cnt;
        public TreeNode node;

        public CounterNode(int cnt, TreeNode node) {
            this.cnt = cnt;
            this.node = node;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, int a, int b) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        dfs(root, path1, a);
        dfs(root, path2, b);
        if (path1.isEmpty() || path2.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < path1.size() && i < path2.size()) {
            if (path1.get(i) == path2.get(i)) {
                i++;
            } else {
                return path1.get(i - 1);
            }
        }
        return path1.get(i - 1);
    }

    private boolean dfs(TreeNode root, List<TreeNode> path, int target) {
        if (!path.isEmpty() && path.get(path.size() - 1).val == target) {
            return true;
        }
        if (root == null) {
            return false;
        }
        path.add(root);
        if (dfs(root.left, path, target)) {
            return true;
        }
        if (dfs(root.right, path, target)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}
