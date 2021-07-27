package bst;

/*
Given a binary tree, we install cameras on the nodes of the tree.
Each camera at a node can monitor its parent, itself, and its immediate children.
Calculate the minimum number of cameras needed to monitor all nodes of the tree.

Example 1:
Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:
Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:
The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.

analysis: greedy

Here is our greedy solution:

Set cameras on all leaves' parents, thenremove all covered nodes.
Repeat step 1 until all nodes are covered.

TC O(N)
SC O(logN)

NOT_MONITORED = node is null or adjacent to node with MONITORED_WITHCAM
MONITORED_NOCAM = node whose children are NOT_MONITORED
MONITORED_WITHCAM = node whose any of the children is MONITORED_NOCAM
*/

public class BinaryTreeCameras {
	int cameras = 0;
	static final int NOT_MONITORED = 0;
	static final int MONITORED_NOCAM = 0;
	static final int MONITORED_WITHCAM = 0;
    public int minCameraCover(TreeNode root) {
        if(root == null){
        	return 0;
		}
        int top = dfs(root);
        if(top == NOT_MONITORED){
        	return cameras + 1;
		} else {
        	return cameras;
		}
    }

    // child 0 -> parent 2; child 1 -> parent 0; child 2 -> parent 1
    private int dfs(TreeNode root){
    	if(root == null){
    		return MONITORED_NOCAM;
		}
    	int left = dfs(root.left);
		int right = dfs(root.right);
		if(left == MONITORED_NOCAM && right == MONITORED_NOCAM){
			// leaf scenario
			return NOT_MONITORED;
		} else if(left == NOT_MONITORED || right == NOT_MONITORED){
			// leaf parent scenario
			cameras++;
			return MONITORED_WITHCAM;
		} else {
			return MONITORED_NOCAM;
		}

    }
}
