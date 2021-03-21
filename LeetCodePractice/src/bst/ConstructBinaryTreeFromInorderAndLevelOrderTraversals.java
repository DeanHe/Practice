package bst;

/*
Given inorder and level-order traversals of a Binary Tree, construct the Binary Tree. Following is an example to illustrate the problem.
BinaryTree

Input: Two arrays that represent Inorder
       and level order traversals of a 
       Binary Tree
in[]    = {4, 8, 10, 12, 14, 20, 22};
level[] = {20, 8, 22, 4, 12, 10, 14};

Output: Construct the tree represented 
        by the two arrays.
        For the above two arrays, the 
        constructed tree is shown in 
        the diagram on right side
        
*/
public class ConstructBinaryTreeFromInorderAndLevelOrderTraversals {
	public TreeNode buildTree(int[] inOrder, int[] levelOrder) {
		int len = inOrder.length;
		return constructTree(inOrder, levelOrder, 0, len - 1);
	}

	private TreeNode constructTree(int[] inOrder, int[] levelOrder, int start, int end) {
		int len = inOrder.length;
		if (start > end) {
			return null;
		}
		for (int i = 0; i < len; i++) {
			int target = levelOrder[i];
			for (int j = start; j <= end; j++) {
				if (target == inOrder[j]) {
					TreeNode root = new TreeNode(target);
					root.left = constructTree(inOrder, levelOrder, start, j - 1);
					root.right = constructTree(inOrder, levelOrder, j + 1, end);
					return root;
				}
			}
		}
		return null;
	}
}
