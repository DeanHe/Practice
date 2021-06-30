package segmentTree;

/*
		Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):
		For query(start, end), return the sum from index start to index end in the given array.
		For modify(index, value), modify the number in the given index to value
		We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

		Example
		Example 1
		Input:
		[1,2,7,8,5]
		[query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
		Output: [10,6,14]
		Explanation:
		Given array A = [1,2,7,8,5].
		After query(0, 2), 1 + 2 + 7 = 10,
		After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
		After query(0, 1), 4 + 2 = 6.
		After modify(2, 1), change A[2] from 7 to 1，A = [4,2,1,8,5].
		After query(2, 4), 1 + 8 + 5 = 14.

		Example 2
		Input:
		[1,2,3,4,5]
		[query(0,0),query(1,2),quert(3,4)]
		Output: [1,5,9]
		Explantion:
		1 = 1
		2 + 3 = 5
		4 + 5 = 9
		Challenge
		O(logN) time for query and modify.
*/
public class IntervalSumII {
	SegmentTreeNode root;
	/**
	 * @param A: An integer array
	 */
	public IntervalSumII(int[] A) {
		root = build(0, A.length - 1, A);
	}

	/**
	 * @param start: An integer
	 *
	 * @param end: An integer
	 *
	 * @return: The sum from start to end
	 */
	public long query(int start, int end) {
		// write your code here
		return querySegmentTreeNode(root, start, end);
	}

	/**
	 * @param index: An integer
	 *
	 * @param value: An integer
	 *
	 * @return: nothing
	 */
	public void modify(int index, int value) {
		// write your code here
		modifySegmentTreeNode(root, index, value);
	}

	class SegmentTreeNode {
		public int start, end;
		public int sum;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}

	private SegmentTreeNode build(int start, int end, int[] A) {
		if(start > end){
			return null;
		}
		SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
		if (start == end) {
			return node;
		}
		int mid = (start + end) / 2;
		node.left = build(start, mid, A);
		node.right = build(mid + 1, end, A);
		node.sum = node.left.sum + node.right.sum;
		return node;
	}

	private int querySegmentTreeNode(SegmentTreeNode node, int start, int end) {
		if (start <= node.start && node.end <= end) {
			return node.sum;
		}
		int mid = (node.start + node.end) / 2;
		if(end <= mid){
			return querySegmentTreeNode(node.left, start, end);
		}
		if(mid < start){
			return querySegmentTreeNode(node.right, start, end);
		}
		int leftSum =  querySegmentTreeNode(node.left, start, mid);
		int rightSum = querySegmentTreeNode(node.right, mid + 1, end);
		return leftSum + rightSum;
	}

	private void modifySegmentTreeNode(SegmentTreeNode node, int index, int value) {
		if (node.start == index && node.end == index) {
			node.sum = value;
			return;
		}
		int mid = (node.start + node.end) / 2;
		if (node.start <= index && index <= mid) {
			modifySegmentTreeNode(node.left, index, value);
		}
		if (mid + 1 <= index && index <= root.end) {
			modifySegmentTreeNode(node.right, index, value);
		}
		node.sum = node.left.sum + node.right.sum;
	}
}
