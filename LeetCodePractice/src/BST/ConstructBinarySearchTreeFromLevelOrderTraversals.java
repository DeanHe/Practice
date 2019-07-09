package BST;
/*
Construct the BST (Binary Search Tree) from its given level order traversal.

Examples:

Input : arr[] = {7, 4, 12, 3, 6, 8, 1, 5, 10}
Output : BST: 
        7        
       / \       
      4   12      
     / \  /     
    3  6 8    
   /  /   \
  1   5   10
*/
public class ConstructBinarySearchTreeFromLevelOrderTraversals {
	public TreeNode constructTree(int[] levelOrder){
		int len = levelOrder.length;
		if(len == 0){
			return null;
		}
		TreeNode root = new TreeNode(levelOrder[0]);
		for(int i = 1; i < len; i++){
			dfs(root, levelOrder[i]);
		}
		return root;
	}
	private void dfs(TreeNode root, int data){
		if(root.val > data){
			if(root.left == null){
				root.left = new TreeNode(data);
				return;
			} else {
				dfs(root.left, data);
			}
		} else {
			if(root.right == null){
				root.right = new TreeNode(data);
				return;
			} else {
				dfs(root.right, data);
			}
		}
	}
}
