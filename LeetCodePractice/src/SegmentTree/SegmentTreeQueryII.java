package SegmentTree;

/*
For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)
Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

Example

Example 1:
Input："[0,3,count=3][0,1,count=1][2,3,count=2][0,0,count=1][1,1,count=0][2,2,count=1][3,3,count=1]",[[1, 1], [1, 2], [2, 3], [0, 2]]
Output：[0,1,2,2]
Explanation：
The corresponding value Segment Tree is:

	                     [0, 3, count=3]
	                     /             \
	          [0,1,count=1]             [2,3,count=2]
	          /         \               /            \
	   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]

Input : query(1,1), Output: 0
Input : query(1,2), Output: 1
Input : query(2,3), Output: 2
Input : query(0,2), Output: 2

Example 2:
Input："[0,3,count=3][0,1,count=1][2,3,count=2][0,0,count=1][1,1,count=0][2,2,count=0][3,3,count=1]",[[1, 1], [1, 2], [2, 3], [0, 2]]
Output：[0,0,1,1]
Explanation：
The corresponding value Segment Tree is:

	                     [0, 3, count=2]
	                     /             \
	          [0,1,count=1]             [2,3,count=1]
	          /         \               /            \
	   [0,0,count=1] [1,1,count=0] [2,2,count=0], [3,3,count=1]

Input : query(1,1), Output: 0
Input : query(1,2), Output: 0
Input : query(2,3), Output: 1
Input : query(0,2), Output: 1
*/

public class SegmentTreeQueryII {
	class SegmentTreeNode {
		public int start, end, count;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int count) {
			this.start = start;
			this.end = end;
			this.count = count;
			this.left = this.right = null;
		}
	}

	/*
	 * @param root: The root of segment tree.
	 *
	 * @param start: start value.
	 *
	 * @param end: end value.
	 *
	 * @return: The count number in the interval [start, end]
	 */
	public int query(SegmentTreeNode root, int start, int end) {
		// write your code here
		if (start > end || root == null) {
			return 0;
		}
		if (start <= root.start && root.end <= end) {
			return root.count;
		}
		int mid = (root.start + root.end) / 2;
		if (end <= mid) {
			return query(root.left, start, end);
		}
		if (mid < start) {
			return query(root.right, start, end);
		}
		int leftCount = query(root.left, start, mid);
		int rightCount = query(root.left, mid + 1, end);
		return rightCount + leftCount;
	}
}
