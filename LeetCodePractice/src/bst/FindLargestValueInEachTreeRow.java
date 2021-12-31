package bst;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
Example 2:

Input: root = [1,2,3]
Output: [1,3]


Constraints:

The number of nodes in the tree will be in the range [0, 104].
-2^31 <= Node.val <= 2^31 - 1

analysis:
both BFS and DFS are fine
dfs is faster because it only cost you pay is the stack-space (logN)
bfs needs to maintain a queue which can grow up to 2^logN
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(root.val);
        } else {
            int most = res.get(depth);
            if (most < root.val) {
                res.set(depth, root.val);
            }
        }
        dfs(root.left, depth + 1, res);
        dfs(root.right, depth + 1, res);
    }
}
