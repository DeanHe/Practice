package bst;
/*Given a binary tree and an integer which is the depth of the target level.

Calculate the sum of the nodes in the target level.

Example
Example 1:

Input：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},2
Output：5 
Explanation：
     1
   /   \
  2     3
 / \   / \
4   5 6   7
   /       \
  8         9
2+3=5
Example 2:

Input：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},3
Output：22
Explanation：
     1
   /   \
  2     3
 / \   / \
4   5 6   7
   /       \
  8         9
4+5+6+7=22*/
public class BinaryTreeLevelSum {
	int sum = 0;
	/**
     * @param root: the root of the binary tree
     * @param level: the depth of the target level
     * @return: An integer
     */
    public int levelSum(TreeNode root, int level) {
    	helper(root, 1, level);
    	return sum;
    }
    private void helper(TreeNode root, int depth, int level){
    	if(root == null){
    		return;
    	}
    	if (depth == level) {
			sum += root.val;
			return;
		}
    	helper(root.left, depth + 1, level);
    	helper(root.right, depth + 1, level);
    }
}
