package contest;

import bst.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
You are given n BST (binary search tree) root nodes for n separate BSTs stored in an array trees (0-indexed). Each BST in trees has at most 3 nodes, and no two roots have the same value. In one operation, you can:

Select two distinct indices i and j such that the value stored at one of the leaves of trees[i] is equal to the root value of trees[j].
Replace the leaf node in trees[i] with trees[j].
Remove trees[j] from trees.
Return the root of the resulting BST if it is possible to form a valid BST after performing n - 1 operations, or null if it is impossible to create a valid BST.

A BST (binary search tree) is a binary tree where each node satisfies the following property:

Every node in the node's left subtree has a value strictly less than the node's value.
Every node in the node's right subtree has a value strictly greater than the node's value.
A leaf is a node that has no children.



Example 1:


Input: trees = [[2,1],[3,2,5],[5,4]]
Output: [3,2,5,1,null,4]
Explanation:
In the first operation, pick i=1 and j=0, and merge trees[0] into trees[1].
Delete trees[0], so trees = [[3,2,5,1],[5,4]].

In the second operation, pick i=0 and j=1, and merge trees[1] into trees[0].
Delete trees[1], so trees = [[3,2,5,1,null,4]].

The resulting tree, shown above, is a valid BST, so return its root.
Example 2:


Input: trees = [[5,3,8],[3,2,6]]
Output: []
Explanation:
Pick i=0 and j=1 and merge trees[1] into trees[0].
Delete trees[1], so trees = [[5,3,8,2,6]].

The resulting tree is shown above. This is the only valid operation that can be performed, but the resulting tree is not a valid BST, so return null.
Example 3:


Input: trees = [[5,4],[3]]
Output: []
Explanation: It is impossible to perform any operations.
Example 4:


Input: trees = [[2,1,3]]
Output: [2,1,3]
Explanation: There is only one tree, and it is already a valid BST, so return its root.


Constraints:

n == trees.length
1 <= n <= 5 * 10^4
The number of nodes in each tree is in the range [1, 3].
No two roots of trees have the same value.
All the trees in the input are valid BSTs.
1 <= TreeNode.val <= 5 * 10^4.

hint:
Is it possible to have multiple leaf nodes with the same values?
How many possible positions are there for each tree?
The root value of the final tree does not occur as a value in any of the leaves of the original tree.
 */
public class MergeBSTsToCreateSingleBST {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (TreeNode root : trees) {
            rootMap.put(root.val, root);
            countElement(root, freqMap);
        }
        //find root after merge
        int rootVal = -1;
        for (int n : rootMap.keySet()) {
            if (freqMap.get(n) == 1) {
                rootVal = n;
                break;
            }
        }
        if (rootVal == -1) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = rootMap.get(rootVal);
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode leaf = q.poll();
                if (rootMap.containsKey(leaf.val)) {
                    TreeNode dup = rootMap.get(leaf.val);
                    if (dup.left != null) {
                        leaf.left = dup.left;
                        q.offer(leaf.left);
                    }
                    if (dup.right != null) {
                        leaf.right = dup.right;
                        q.offer(leaf.right);
                    }
                }
                rootMap.remove(leaf.val);
            }

        }
        if (rootMap.isEmpty() && isValidBST(root)) {
            return root;
        }
        return null;
    }

    private void countElement(TreeNode root, Map<Integer, Integer> freqMap) {
        if (root != null) {
            freqMap.put(root.val, freqMap.getOrDefault(root.val, 0) + 1);
            countElement(root.left, freqMap);
            countElement(root.right, freqMap);
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBSTHelper(root, null, null);
    }

    private boolean isBSTHelper(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        if (root == null) {
            return true;
        }
        if ((minNode != null && root.val <= minNode.val) || (maxNode != null && root.val >= maxNode.val)) {
            return false;
        }
        boolean left = isBSTHelper(root.left, minNode, root);
        boolean right = isBSTHelper(root.right, root, maxNode);
        return left && right;
    }
}

