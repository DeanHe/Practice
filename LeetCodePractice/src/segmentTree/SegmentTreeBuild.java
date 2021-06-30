package segmentTree;
/*
The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.

start and end are both integers, they should be assigned in following rules:

The root's start and end is given by build method.
The left child of node A has start=A.left, end=(A.left + A.right) / 2.
The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
if start equals to end, there will be no children for this node.
Implement a build method with two parameters start and end, so that we can create a corresponding segment tree with every node has the correct start and end value, return the root of this segment tree.

Example
Example 1:

Input：[1,4]
Output："[1,4][1,2][3,4][1,1][2,2][3,3][4,4]"
Explanation：
	               [1,  4]
	             /        \
	      [1,  2]           [3, 4]
	      /     \           /     \
	   [1, 1]  [2, 2]     [3, 3]  [4, 4]
Example 2:

Input：[1,6]
Output："[1,6][1,3][4,6][1,2][3,3][4,5][6,6][1,1][2,2][4,4][5,5]"
Explanation：
	       [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]
Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:

which of these intervals contain a given point
which of these points are in a given interval
See wiki:
Segment Tree
Interval Tree
*/

public class SegmentTreeBuild {
	/**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        if(start > end){
            return null;
        }
        if(start == end){
            return new SegmentTreeNode(start, end);
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        int mid = (start + end) / 2;
        node.left = build(start, mid);
        node.right = build(mid + 1, end);
        return node;
    }
    
    class SegmentTreeNode {
    	public int start, end;
    	public SegmentTreeNode left, right;

    	public SegmentTreeNode(int start, int end) {
    		this.start = start;
    		this.end = end;
    		this.left = this.right = null;
    	}
    }
}