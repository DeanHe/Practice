package bst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.



Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.


Constraints:

1 <= descriptions.length <= 10^4
descriptions[i].length == 3
1 <= parenti, childi <= 10^5
0 <= isLefti <= 1
The binary tree described by descriptions is valid.

hint:
1 Could you represent and store the descriptions more efficiently?
2 Could you find the root node?
3 The node that is not a child in any of the descriptions is the root node.
TC: O(N)
 */
public class CreateBinaryTreeFromDescriptions {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> childrens = new HashSet<>();
        for(int[] d : descriptions){
            int father = d[0];
            int child = d[1];
            int isLeft = d[2];
            childrens.add(child);
            TreeNode parentNode = map.computeIfAbsent(father, x -> new TreeNode(father));
            TreeNode childNode = map.computeIfAbsent(child, x -> new TreeNode(child));
            if(isLeft == 1){
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }
        int rootVal = -1;
        for(int[] d : descriptions){
            int father = d[0];
            if(!childrens.contains(father)){
                rootVal = father;
                break;
            }
        }
        return map.getOrDefault(rootVal, null);
    }
}
