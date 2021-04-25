package bst.lca;

import java.util.HashSet;
import java.util.Set;

/*
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q exist in the tree.

analysis:
get depth from p and q to root, by using parent node
move the one with longer depth, such that p q has the same depth.
move together until p and q meet, then it is the LCA

TC O(N)
SC O(1)

approach 2:
use a set to store all parent node, check any reoccurrence is result
 */
public class LowestCommonAncestorOfaBinaryTreeIII {
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (p == null || q == null) {
            return null;
        }
        if (p == q) {
            return p;
        }
        Node pp = p, qq = q;
        int depth_p = 0, depth_q = 0;
        while (pp != root) {
            depth_p++;
            pp = pp.parent;
        }
        while (qq != root) {
            depth_q++;
            qq = qq.parent;
        }
        if (depth_p > depth_q) {
            int diff = depth_p - depth_q;
            while (diff > 0) {
                p = p.parent;
                diff--;
            }
        } else {
            int diff = depth_q - depth_p;
            while (diff > 0) {
                q = q.parent;
                diff--;
            }
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }

    public Node lowestCommonAncestor2(Node root, Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while(true){
            if(p != null && !set.add(p)){
                return p;
            }
            if(q != null && !set.add(q)){
                return q;
            }
            if(p != null){
                p = p.parent;
            }
            if(q != null){
               q = q.parent;
            }
        }
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
