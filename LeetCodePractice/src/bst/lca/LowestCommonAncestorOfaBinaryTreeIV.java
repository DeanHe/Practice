package bst.lca;

import bst.TreeNode;

import java.util.HashSet;
import java.util.Set;

/*
Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree’s nodes are unique.

Extending the definition of LCA on Wikipedia: “The lowest common ancestor of n nodes p_1, p_2, …, p_n in a binary tree T is the lowest node that has every p_i as a descendant (where we allow a node to be a descendant of itself) for every valid i”. A descendant of a node x is a node y that is on the path from node x to some leaf node.

Example 1:

Image text

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]

Output: 2

Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.

Example 2:

Image text

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]

Output: 1

Explanation: The lowest common ancestor of a single node is the node itself.

Example 3:

Image text

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]

Output: 5

Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.

Example 4:

Image text

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8] Output: 3 Explanation: The lowest common ancestor of all the nodes is the root node.

Constraints:**

The number of nodes in the tree is in the range [1, 10^4].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
All nodes[i] will exist in the tree.
All nodes[i] are distinct.
 */
public class LowestCommonAncestorOfaBinaryTreeIV {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if(root == null || nodes == null || nodes.length == 0){
            return null;
        }
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode n : nodes){
            set.add(n);
        }
        return dfs(root, set);
    }

    private TreeNode dfs(TreeNode root, Set<TreeNode> set) {
        if(root == null || set.contains(root)){
            return root;
        }
        TreeNode l = dfs(root.left, set);
        TreeNode r = dfs(root.right, set);
        if(l != null && r != null){
            return root;
        }
        if(l != null){
            return l;
        }
        if(r != null){
            return r;
        }
        return null;
    }
}

