package BST;

public class BinaryTreeToDoublyLinkedList {
	class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;

		TreeNode(int x) {
			val = x;
		}
	}
	class DoublyNode {
		DoublyNode pre;
		DoublyNode next;
		int val;
		
		DoublyNode(int x) {
			val = x;
		}
	}
	public DoublyNode convertToList(TreeNode root){
		if(root == null){
			return null;
		}
		
	}
}
