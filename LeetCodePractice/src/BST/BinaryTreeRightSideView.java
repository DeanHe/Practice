package BST;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res, int depth) {
        if(root == null){
            return;
        }
        if(depth == res.size()){
            res.add(root.val);
        }
        dfs(root.right, res, depth + 1);
        dfs(root.left, res, depth + 1);
    }
}
