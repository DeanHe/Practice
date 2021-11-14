package bst.iterator;

import bst.TreeNode;

import java.util.Stack;

/*
Implement the BSTpostOrderIterator class that represents an iterator over the post-order traversal of a binary search tree (BST):

BSTpostOrderIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

Constraints:

The number of nodes in the tree is in the range [1, 10^5].
0 <= Node.val <= 10^6
At most 105 calls will be made to hasNext, and next.


Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?

 */
public class BSTpostOrderIterator {
    Stack<TreeNode> stack;
    public BSTpostOrderIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pop();
        if(!stack.isEmpty()){
            TreeNode top = stack.peek();
            if(cur == top.left){
                pushAll(top.right);
            }
        }
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAll(TreeNode node){
        while(node != null){
            stack.push(node);
            if(node.left != null){
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
}
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

