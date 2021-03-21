package bst.NaryTree;

import bst.NNode;

import java.util.List;

/*
Given all the nodes of an N-ary tree as an array  Node[] tree where each node has a unique value.

Find and return the root of the N-ary tree.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Follow up: Can you find the root of the tree with O(1) additional memory space?

Notes:

The following input is only given to testing purposes.
You will receive as input a list of all nodes of the n-ary tree in any order.


Example 1:
Input: [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
Explanation: The input tree is shown above.
The driver code first extracts the nodes so we now have an array of all tree nodes [Node(1),Node(3),Node(2),Node(4),Node(5),Node(6)],
then the array is randomly shuffled, thus the actual input is [Node(5),Node(4),Node(3),Node(6),Node(2),Node(1)].
The root of the tree is Node(1) and the output is the serialization of the node you return.

Example 2:
Input: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]


Constraints:

The total number of nodes is between [1, 5*10^4].
Each node has a unique value.
 */
public class FindRootOfNAryTree {

    public NNode findRoot(List<NNode> tree){
        int sum = 0;
        for(NNode node : tree){
            sum += node.val;
            for(NNode child : node.children){
                sum -= child.val;
            }
        }
        for(NNode node : tree){
            if(node.val == sum){
                return node;
            }
        }
        return null;
    }
}
