package bst;

import java.util.*;

/*Given a tree of n nodes. The ith node's father is fa[i-1] and the value of the ith node is val[i-1]. Especially, 1 represents the root, 2 represents the second node and so on. We gurantee that -1 is the father of root(the first node) which means that fa[0] = -1.
The average value of a subtree is the result of the sum of all nodes in the subtree divide by the number of nodes in it.
Find the maximum average value of the subtrees in the given tree, return the number which represents the root of the subtree.

Example
Given fa=[-1,1,1,2,2,2,3,3], representing the father node of each point, and val=[100,120,80,40,50,60,50,70] , representing the value of each node, return 1.

Sample diagram：
                      -1  ------No.1
                    /     \
         No.2 ----1        1---------No.3
               /  |  \     /  \
              2   2   2    3   3
No.1 node is (100+120+80+40+50+60+50+70) / 8 = 71.25
No.2 node are (120 + 40 + 50 + 60) / 4 = 67.5
No.3 node are (80+50+70) / 3 = 66.6667
So return 1.
Notice
the number of nodes do not exceed 100000
If there are more than one subtree meeting the requirement, return the minimum number.*/
public class TreeProblem {
	/**
     * @param fa: the father
     * @param val: the val
     * @return: the biggest node
     */
    double max = Double.MIN_VALUE;
	int maxIndex = 0;
    public int treeProblem(int[] fa, int[] val) {
        // Write your code here
    	TreeNode root = new TreeNode();
    	root.val = val[0];
    	root.index = 1;
    	constuctTree(root, fa, val);
    	dfs(root);
    	return maxIndex;
    }
    // build tree
    private void constuctTree(TreeNode root, int[] fa, int[] val){
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while(!queue.isEmpty()){
    		int len = queue.size();
    		for(int i = 0; i < len; i++){
    			TreeNode cur =  queue.poll();
    			cur.children = getChildren(fa, val, cur.index);
    			for(int j = 0; j < cur.children.size(); j++){
    				TreeNode child = cur.children.get(j);
    				queue.offer(child);
    			}
    		}		
    	}
    }
    // dfs find max avg node
    private void dfs(TreeNode root){
    	if(root.children == null){
    		root.sum = root.val;
    		if(root.sum > max){
    		    maxIndex = root.index;
    		}
    		return;
    	}
    	int totalChildren = 0, sum = 0;
    	for(int i = 0; i < root.children.size(); i++){
    		TreeNode child = root.children.get(i);
    		dfs(child);
    		totalChildren += child.totalChildren + 1;
    		sum += child.sum;
    	}
    	sum += root.val;
    	root.sum = sum;
    	root.totalChildren = totalChildren;
    	double avg = sum * 1.0 / (totalChildren + 1);
    	if(avg > max){
    		max = avg;
    		maxIndex = root.index;
    	}
    }
    private ArrayList<TreeNode> getChildren(int[] fa, int[] val, int index){
    	ArrayList<TreeNode> children = new ArrayList<>();
    	for(int i = 0; i < fa.length; i++){
    		if(fa[i] == index){
    			TreeNode node = new TreeNode();
    			node.val = val[i];
    			node.index = i + 1;
    			children.add(node);
    		}
    	}
    	return children;
    }
    
    class TreeNode {
    	int index, val, sum, totalChildren;
    	ArrayList<TreeNode> children;
    }
}
