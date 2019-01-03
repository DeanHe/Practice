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
	
	TreeNode head = null;
	static TreeNode pre = null;

	public void printList(){
		while(head != null){
			System.out.println(head.val + " ");
			head = head.right;
		}
	}

	public void convertToList(TreeNode root){
		if(root == null){
			return;
		}
		convertToList(root.left);
		if(pre == null){
			head = root;
		} else {
			pre.right = root;
			root.left =pre;
		}
		pre = root;
		convertToList(root.right);
	}
}