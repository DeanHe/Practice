package bst;
/*
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


Example 1:



Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.


Constraints:

The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100

analysis:
difference from I is that not given a perfect bst
head refers to <head of the next level>
cur refers to <current node of current level>
pre refers to <the leading node on the next level>
 */
import bst.PopulatingNextRightPointersInEachNode.Node;

public class PopulatingNextRightPointersInEachNodeII {
    public Node connect(Node root) {
        Node cur = root;
        Node head = null;
        Node pre = null;
        while (cur != null){
            while(cur != null){
                if(cur.left != null){
                    if(pre != null){
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if(cur.right != null){
                    if(pre != null){
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            pre = null;
        }
        return root;
    }
}
