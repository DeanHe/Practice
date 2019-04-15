package BST;

import java.util.*;

/*Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

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
 4  01   7*/
public class BinaryTreeVerticalOrderTraversal {
	/**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
    	List<List<Integer>> res = new ArrayList<>();
    	if(root == null){
    		return res;
    	}
    	//column : List of Nodes
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	Queue<Integer> qCol = new LinkedList<>();
    	Queue<TreeNode> qNode = new LinkedList<>();
    	qCol.offer(0);
    	qNode.offer(root);
    	while(!qNode.isEmpty()){
    		TreeNode cur = qNode.poll();
    		int col = qCol.poll();
    		if(!map.containsKey(col)){
    			map.put(col, new ArrayList<>());
    		}
    		map.get(col).add(cur.val);
    		if(cur.left != null){
    			qNode.offer(cur.left);
    			qCol.offer(col - 1);
    		}
    		if(cur.right != null){
    			qNode.offer(cur.right);
    			qCol.offer(col + 1);
    		}
    	}
    	int minCol = Collections.min(map.keySet());
    	int maxCol = Collections.max(map.keySet());
    	for(int i = minCol; i <= maxCol; i++){
    		res.add(map.get(i));
    	}
    	return res;
    }
}
