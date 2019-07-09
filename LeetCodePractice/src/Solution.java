import java.util.*;

import BST.ConstructBinarySearchTreeFromLevelOrderTraversals;
import BST.TreeNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]   in = {4, 8, 10, 12, 14, 20, 2};
		int[] level = {5,4,8,1,7,2,6,3};
		ConstructBinarySearchTreeFromLevelOrderTraversals sol = new ConstructBinarySearchTreeFromLevelOrderTraversals();
		TreeNode root = sol.constructTree(level);
		System.out.println(root.val);
	}
}
