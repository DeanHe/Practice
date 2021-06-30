package segmentTree;

/*
The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.

start and end are both integers, they should be assigned in following rules:

The root's start and end is given by build method.
The left child of node A has start=A.left, end=(A.left + A.right) / 2.
The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
if start equals to end, there will be no children for this node.
Implement a build method with a given array, so that we can create a corresponding segment tree with every node value represent the corresponding interval max value in the array, return the root of this segment tree.

Example
Input: [3,2,1,4]
Explanation:
The segment tree will be
          [0,3](max=4)
          /          \
       [0,1]         [2,3]    
      (max=3)       (max=4)
      /   \          /    \    
   [0,0]  [1,1]    [2,2]  [3,3]
  (max=3)(max=2)  (max=1)(max=4)
Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:

which of these intervals contain a given point
which of these points are in a given interval
*/

public class SegmentTreeBuildII {
	/**
     * @param A: a list of integer
     * @return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        // write your code here
        return buildHelper(0, A.length - 1, A);
    }
    
    private SegmentTreeNode buildHelper(int start, int end, int[] A){
        if(start > end){
            return null;
        }
        if(start == end){
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start  + end) / 2;
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        node.left = buildHelper(start, mid, A);
        node.right = buildHelper(mid + 1, end, A);
        if(node.left != null){
            node.max = Math.max(node.max, node.left.max);
        }
        if(node.right != null){
            node.max = Math.max(node.max, node.right.max);
        }
        return node;
    }

    class SegmentTreeNode {
        public int start, end, max;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = this.right = null;
        }
    }
}
