package BST;
/*
Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.

Challenge
O(h), where h is the height of the BST.

Notice
It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)
*/
public class InorderSuccessorInBST {
	/*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
    	if(p == null){
    		return null;
    	}
    	TreeNode successor = null;
    	while(root != null && root.val != p.val){
    		if(root.val > p.val){
    			successor = root;
    			root = root.left;
    		} else {
    			root = root.right;
    		}
    	}
    	if(root == null){
    		return null;
    	}
    	//root.val == p.val
    	root = root.right;
    	while(root != null){
    		successor = root;
    		root = root.left;
    	}
    	return successor;
    }
}

