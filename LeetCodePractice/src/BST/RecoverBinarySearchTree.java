package BST;

/*In a binary search tree, (Only) two nodes are swapped. Find out these nodes and swap them. 
If there no node swapped, return original root of tree.*/
public class RecoverBinarySearchTree {

	TreeNode first = null;
	TreeNode second = null;
	TreeNode previous = new TreeNode(Integer.MIN_VALUE);
	/**
     * @param root: the given tree
     * @return: the tree after swapping
     */
    public TreeNode bstSwappedNode(TreeNode root) {
        // write your code here
    	traverse(root);
    	if(first != null && second != null){
            int temp = first.val;
    	    first.val = second.val;
    	    second.val = temp;
        }
    	return root; 
    }
    
    private void traverse(TreeNode root){
    	if(root == null){
    		return;           
    	}
    	traverse(root.left);
    	if(first == null && root.val < previous.val){
    		first = previous;
    	}
    	if(first !=  null && root.val < previous.val){
    		second = root;
    	}
    	previous = root;
    	traverse(root.right);
    }
}
