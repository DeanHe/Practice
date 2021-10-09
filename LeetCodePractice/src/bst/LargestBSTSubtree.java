package bst;
/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where a largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \
 1   8   7
The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    private class Wrapper {
        int size, least, most;
        boolean isBST;

        public Wrapper(){
            size = 0;
            least = Integer.MAX_VALUE;
            most = Integer.MIN_VALUE;
            isBST = false;
        }
    }
    public int largestBSTSubtree(TreeNode root) {
        return  dfs(root).size;
    }

    private Wrapper dfs(TreeNode root) {
        Wrapper res = new Wrapper();
       if(root == null){
           res.isBST = true;
           return res;
       }
       Wrapper l = dfs(root.left);
       Wrapper r = dfs(root.right);
       if(l.isBST && r.isBST && l.most < root.val && root.val < r.least){
           res.isBST = true;
           res.least = l.least;
           res.most = r.most;
           res.size = l.size + 1 + r.size;
       } else {
           res.isBST = false;
       }
       return res;
    }
}
