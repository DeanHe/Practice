package bst;
/*Given the root node of a binary search tree (bst) and a value to be inserted into the tree, insert the value into the bst. Return the root node of the bst after the insertion. It is guaranteed that the new value does not exist in the original bst.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a bst after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
*/
public class InsertIntoaBinarySearchTree {
	public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
        	return new TreeNode(val);
        }
        TreeNode cur = root;
        while(true){
        	if(cur.val > val){
        		if(cur.left == null){
        			cur.left = new TreeNode(val);
        			break;
        		} else {
        			cur = cur.left;
        		}
        	} else {
        		if(cur.right == null){
        			cur.right = new TreeNode(val);
        			break;
        		} else {
        			cur = cur.right;
        		}
        	}
        }
        return root;   
    }
}
