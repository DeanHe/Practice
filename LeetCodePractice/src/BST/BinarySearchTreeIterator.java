package BST;

import java.util.Stack;

/*Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.*/
public class BinarySearchTreeIterator {
	class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;

		TreeNode(int x) {
			val = x;
		}
	}
	Stack<TreeNode> stack;
	public BinarySearchTreeIterator(TreeNode root) {
		stack = new Stack<>();
		pushAll(root);
    }
    
    /** @return the next smallest number */
    public int next() {
    	TreeNode cur = stack.pop();
        pushAll(cur.right);
        return cur.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushAll(TreeNode node){
    	while(node != null){
    		stack.push(node);
    		node = node.left;
    	}
    }
}
