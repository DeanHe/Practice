package bst.inorderTraversalWithPreviousNode;

import bst.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Description
Convert a bst to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the pre and next pointers in a doubly-linked list.

Let's take the following bst as an example, it may help you understand the problem better:

We want to transform this bst into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the bst above. The "head" symbol means the node it points to is the smallest element of the linked list.

Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed bst. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example
Example 1:

Input: {4,2,5,1,3}
        4
       /  \
      2   5
     / \
    1   3
Output: "left:1->5->4->3->2  right:1->2->3->4->5"
Explanation:
Left: reverse output
Right: positive sequence output
Example 2:

Input: {2,1,3}
        2
       /  \
      1   3
Output: "left:1->3->2  right:1->2->3"

https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description
use inorder traversal
O(N) space complexity
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    // O(1) space complexity
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    private TreeNode head, tail;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = tail; // tail is at last node
        tail.right = head;
        return head;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if(head == null){
            head = root;
        }
        if (tail != null) {
            root.left = tail;
            tail.right = root;
        }
        tail = root;
        inorder(root.right);
    }

    public TreeNode treeToDoublyListIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.isEmpty()){
            if(root != null){
                st.push(root);
                root = root.left;
            } else {
                root = st.pop();
                if(head == null){
                    head = root;
                }
                if (tail != null) {
                    root.left = tail;
                    tail.right = root;
                }
                tail = root;
                root = root.right;
            }
        }
        head.left = tail;
        tail.right = head;
        return head;
    }
}
