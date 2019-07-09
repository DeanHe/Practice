package BST;

import java.util.*;

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
*/
public class DeleteNodesAndReturnForest {
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();
        if(root == null){
        	return res;
        }
		for (int i : to_delete) {
			deleteSet.add(i);
		}
        delNode(root, deleteSet, res, true);
        return res;
    }
    private TreeNode delNode(TreeNode root, Set<Integer> deleteSet, List<TreeNode> res, boolean isRoot){
    	if(root == null){
    		return null;
    	}
    	boolean toDelete = deleteSet.contains(root.val);
    	root.left = delNode(root.left, deleteSet, res, toDelete);
    	root.right = delNode(root.right, deleteSet, res, toDelete);
        if(isRoot && !toDelete){
        	res.add(root);
        }
        if(toDelete){
        	return null;
        }
        return root;
    }
}
