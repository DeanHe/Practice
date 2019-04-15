package BST;

/*We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.
Example 1:
Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Example 2:
Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
		
Example 3:
Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]

Note:
The number of nodes in the original tree is between 1 and 1000. 
Each node will have a value between 1 and 10^9.*/
public class RecoveraTreeFromPreorderTraversal {
	int index = 0;
	public TreeNode recoverFromPreorder(String S) {
		return helper(S, 0);
	}

	private TreeNode helper(String s, int depth) {
		int d = getDepth(s);
		if (d != depth) {
			return null;
		}
		index += d;
		int val = getVal(s);
		TreeNode root = new TreeNode(val);
		index += String.valueOf(val).length();
		root.left = helper(s, depth + 1);
		root.right = helper(s, depth + 1);
		return root;
	}
	// count the following dash
	private int getDepth(String s) {
		int i = 0;
		while (index + i < s.length() && s.charAt(index + i) == '-') {
			i++;
		}
		return i;
	}

	private int getVal(String s) {
		int val = 0, i = 0;
		while (index + i < s.length() && Character.isDigit(s.charAt(index + i))) {
			val = val * 10;
			val += s.charAt(index + i) - '0';
			i++;
		}
		return val;
	}
}
