package bst;

import java.util.Stack;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100


Follow up: Can you flatten the tree in-place (with O(1) extra space)?

analysis:
approach 1: Straightforward recursion
approach 2: using post order traversal of [right, left, root]
 */
public class FlattenBinaryTreeToLinkedList {
    // recursion
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode l = root.left;
        TreeNode r = root.right;
        root.left = null;
        flatten(l);
        flatten(r);
        root.right = l;
        while(root.right != null){
            root = root.right;
        }
        root.right = r;
    }

    // recursion
    TreeNode pre = null;
    public void flatten2(TreeNode root) {
        if(root == null){
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    //iterative
    public void flatten3(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode cur = st.pop();
            if(cur.right != null){
                st.push(cur.right);
            }
            if(cur.left != null){
                st.push(cur.left);
            }
            cur.left = null;
            if(!st.isEmpty()){
                cur.right = st.peek();
            }
        }
    }
}

