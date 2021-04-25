package bst.lca;

import bst.TreeNode;

import java.util.*;

/*
Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 

Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
Example 2:

Input: root = [1,2,3,4]
Output: [4]
Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]
 

Constraints:

The given tree will have between 1 and 1000 nodes.
Each node of the tree will have a distinct value between 1 and 1000.

analysis:
approach 1: two pass, first pass get depth of the leaves, second pass get LCA
TC O(N^2)

approach 2: one pass, pass leaf depth back to its parent
TC O(N)
*/
public class LowestCommonAncestorOfDeepestLeaves {
    Map<TreeNode, Integer> depthMap = new HashMap<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth == rightDepth) {
            return root;
        } else if (leftDepth > rightDepth) {
            return lcaDeepestLeaves(root.left);
        } else {
            return lcaDeepestLeaves(root.right);
        }
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (depthMap.containsKey(root)) {
            return depthMap.get(root);
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int res = Math.max(leftDepth, rightDepth) + 1;
        depthMap.put(root, res);
        return res;
    }

    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        Pair res = dfs(root, 0);
        return res.node;
    }

    private Pair dfs(TreeNode root, int depth) {
        if (root == null) {
            return new Pair(null, depth);
        }
        Pair l = dfs(root.left, depth + 1);
        Pair r = dfs(root.right, depth + 1);

        if (l.depth == r.depth) {
            return new Pair(root, l.depth);
        } else if (l.depth < r.depth) {
            return r;
        } else {
            return l;
        }
    }

    class Pair {
        TreeNode node;
        int depth;

        Pair(TreeNode n, int d) {
            node = n;
            depth = d;
        }
    }
}
