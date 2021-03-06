package bst;
/*
Given a Binary Search Tree (bst), convert it to a Greater Tree such that every key of the original bst is changed to the original key plus sum of all keys greater than the original key in bst.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
*/
public class ConvertBSTtoGreaterTree {
	int sum = 0;
	public TreeNode convertBST(TreeNode root) {
        if(root == null){
        	return root;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
