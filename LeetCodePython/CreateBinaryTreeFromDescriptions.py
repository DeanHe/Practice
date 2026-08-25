"""
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

hints:
1 Could you represent and store the descriptions more efficiently?
2 Could you find the root node?
3 The node that is not a child in any of the descriptions is the root node.

analysis:
TC: O(N)
"""
from typing import List, Optional


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class CreateBinaryTreeFromDescriptions:
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        children = set()
        val_to_node = {}
        for parent, child, is_left in descriptions:
            if parent not in val_to_node:
                val_to_node[parent] = TreeNode(parent)
            if child not in val_to_node:
                val_to_node[child] = TreeNode(child)
            if is_left:
                val_to_node[parent].left = val_to_node[child]
            else:
                val_to_node[parent].right = val_to_node[child]
            children.add(child)
        for val, node in val_to_node.items():
            if val not in children:
                return node
        return None
