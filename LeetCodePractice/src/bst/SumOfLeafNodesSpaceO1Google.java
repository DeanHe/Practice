package bst;

/*
Given a binary tree, find the sum of all leaf nodes.Use O(1) space.

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
2+5+7 = 14

define your own Node structure, requires space complexity to be O(1)

two approach:
1 Define next pointer to each node which points next node on same level (like BFS), the next pointer here to replace the use of queue in BFS
2 Define parent pointer to each node which points the parent node, the parent pointer here to replace the use of stack in DFS
*/
public class SumOfLeafNodesSpaceO1Google {
	/**
	 * @param root:
	 * @return: the sum of leafnode
	 */
	public int sumLeafNode(TreeNodeWithParent root) {
		int res = 0;
		TreeNodeWithParent last = null;
		while(root != null){
			if(last == root.parent){
				if(root.left != null){
					last = root;
					root = root.left;
					continue;
				} else {
					last = null;
				}
			}
			if(last == root.left){
				if(root.right != null){
					last = root;
					root = root.right;
					continue;
				} else {
					last = null;
				}
			}
			if(root.left == null && root.right == null){
				res += root.val;
			}
			if(last == root.right){
				last = root;
				root = root.parent;
			}
		}
		return res;
	}
}
