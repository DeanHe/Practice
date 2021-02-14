package BST;

import java.util.ArrayList;
import java.util.List;

/*
#655
Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   /
  3
 /
4
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
Note: The height of binary tree is in the range of [1, 10].

analysis: dfs divide and conquer
 */
public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        int cols = (1 << height) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            List<String> row = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                row.add("");
            }
            res.add(row);
        }
        dfs(root, res, 0, height, 0, cols - 1);
        return res;
    }

    private void dfs(TreeNode root, List<List<String>> res, int r, int height, int start, int end) {
        if (r == height || root == null) {
            return;
        }
        List<String> row = res.get(r);
        int mid = (start + end) / 2;
        row.set(mid, String.valueOf(root.val));
        dfs(root.left, res, r + 1, height, start, mid - 1);
        dfs(root.right, res, r + 1, height, mid + 1, end);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        return 1 + Math.max(l, r);
    }
}
