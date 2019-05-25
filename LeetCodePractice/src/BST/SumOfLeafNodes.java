package BST;

/*Given a binary tree, find the sum of all leaf nodes.Use O(1) space.

Example
Example 1:

Input:
{1,2,3,4,5}
Output : 12

Explanation：
      1
     / \
   2   3
  / \     
4   5    
Leaf nodes are nodes without children. 4+5+3 = 12
Example 2：

Input：
{12,3,7,4,5,#,#,2}
Output：14

Explanation：
      12
      / \
    3   7
   / \     
 4   5    
/
2
2+5+7 = 14*/
public class SumOfLeafNodes {
	/**
	 * @param root:
	 * @return: the sum of leafnode
	 */
	public int sumLeafNode(TreeNode root) {
		int res = 0;
		if(root == null){
			return res;
		}
		if(root.left == null && root.right == null){
			return root.val;
		}
		res += sumLeafNode(root.left);
		res += sumLeafNode(root.right);
		return res;
	}
}
