package BST;

import javafx.util.Pair;

import java.util.*;

/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Example
Example1

Inpurt:  {3,9,20,#,#,15,7}
Output: [[9],[3,15],[20],[7]]
Explanation:
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
Example2

Input: {3,9,8,4,0,1,7}
Output: [[4],[9],[3,0,1],[8],[7]]
Explanation:
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
 */
public class BinaryTreeVerticalOrderTraversal {
	/**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
	public List<List<Integer>> verticalOrder2(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		//column : List of Nodes
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		Queue<Integer> cq = new LinkedList<>();
		Queue<TreeNode> tq = new LinkedList<>();
		tq.offer(root);
		cq.offer(0);
		while(!tq.isEmpty()){
			TreeNode cur = tq.poll();
			int col = cq.poll();
			map.putIfAbsent(col, new ArrayList<>());
			map.get(col).add(cur.val);
			if(cur.left != null){
				tq.offer(cur.left);
				cq.offer(col - 1);
			}
			if(cur.right != null){
				tq.offer(cur.right);
				cq.offer(col + 1);
			}
		}
		for(List<Integer> layer : map.values()){
			res.add(layer);
		}
		return res;
	}
	//// version 2
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
    	List<List<Integer>> res = new ArrayList<>();
    	if(root == null){
    		return res;
    	}
    	//column : List of Nodes
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	Queue<Pair> queue = new LinkedList<>();
    	queue.offer(new Pair(root, 0));
    	while(!queue.isEmpty()){
			Pair cur = queue.poll();
			TreeNode node = cur.node;
    		int col = cur.col;
    		if(!map.containsKey(col)){
    			map.put(col, new ArrayList<>());
    		}
    		map.get(col).add(node.val);
    		if(node.left != null){
    			queue.offer(new Pair(node.left, col - 1));
    		}
    		if(node.right != null){
    			queue.offer(new Pair(node.right, col + 1));
    		}
    	}
    	int minCol = Collections.min(map.keySet());
    	int maxCol = Collections.max(map.keySet());
    	for(int i = minCol; i <= maxCol; i++){
    		res.add(map.get(i));
    	}
    	return res;
    }

    class Pair {
    	TreeNode node;
    	int col;
    	public Pair(TreeNode node, int col){
    		this.node = node;
    		this.col = col;
		}
	}

}
