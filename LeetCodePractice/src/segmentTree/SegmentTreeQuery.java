package segmentTree;

/*For an integer array (index from 0 to n-1, where n is the size of this array), in the corresponding segmentTree, each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).

Design a query method with three parameters root, start and end, find the maximum number in the interval [start, end] by the given root of segment tree.

Example
Example 1:

Input："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",1,2
Output：4
Explanation：
For array [1, 4, 2, 3], the corresponding Segment Tree is :

	                  [0, 3, max=4]
	                 /             \
	          [0,1,max=4]        [2,3,max=3]
	          /         \        /         \
	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
The maximum value of [1,2] interval is 4
Example 2:

Input："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",2,3
Output：3
Explanation：
For array [1, 4, 2, 3], the corresponding Segment Tree is :

	                  [0, 3, max=4]
	                 /             \
	          [0,1,max=4]        [2,3,max=3]
	          /         \        /         \
	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
The maximum value of [2,3] interval is 3
Notice
It is much easier to understand this problem if you finished Segment Tree Build first.
*/

public class SegmentTreeQuery {
	/**
	 * @param root,
	 *            start, end: The root of segment tree and an segment / interval
	 * @return: The maximum number in the interval [start, end]
	 */
	public int query(SegmentTreeNode root, int start, int end) {
		// write your code here
		if (start == root.start && end == root.end) {
			return root.max;
		}
		int mid = (root.start + root.end) / 2;
		if (end <= mid) {
			return query(root.left, start, end);
		} else if (mid < start) {
			return query(root.right, start, end);
		} else {
			return query(root.left, start, mid) + query(root.right, mid + 1, end);
		}
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