package SegmentTree;


public class IntervalSumII {
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

	SegmentTreeNode treeRoot;

	public SegmentTreeNode build(int start, int end, int[] A) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
		if (start != end) {
			int mid = (start + end) / 2;
			node.left = build(start, mid, A);
			node.right = build(mid + 1, end, A);
			node.sum = node.left.sum + node.right.sum;
		} else {
			node.sum = A[start];
		}
		return node;
	}

	public int querySegmentTreeNode(SegmentTreeNode root, int start, int end) {
		if (start == root.start && end == root.end) {
			return root.sum;
		}
		int mid = (root.start + root.end) / 2;
		int leftSum = 0, rightSum = 0;
		// left interval
		if (start <= mid) {
			// outside
			if (mid < end) {
				leftSum = querySegmentTreeNode(root.left, start, mid);
			} else {
				// contain
				leftSum = querySegmentTreeNode(root.left, start, end);
			}
		}
		// right interval
		if (mid + 1 <= end) {
			// outside
			if (start <= mid) {
				rightSum = querySegmentTreeNode(root.right, mid + 1, end);
			} else {
				// contain
				rightSum = querySegmentTreeNode(root.right, start, end);
			}
		}
		return leftSum + rightSum;
	}

	public void modifySegmentTreeNode(SegmentTreeNode root, int index, int value) {
		if (root.start == index && root.end == index) {
			root.sum = value;
			return;
		}
		int mid = (root.start + root.end) / 2;
		if (root.start <= index && index <= mid) {
			modifySegmentTreeNode(root.left, index, value);
		}
		if (mid + 1 <= index && index <= root.end) {
			modifySegmentTreeNode(root.right, index, value);
		}
		root.sum = root.left.sum + root.right.sum;
	}

	/*
	 * @param A: An integer array
	 */
	public IntervalSumII(int[] A) {
		// do intialization if necessary
		treeRoot = build(0, A.length - 1, A);
	}

	/*
	 * @param start: An integer
	 * 
	 * @param end: An integer
	 * 
	 * @return: The sum from start to end
	 */
	public long query(int start, int end) {
		// write your code here
		return querySegmentTreeNode(treeRoot, start, end);
	}

	/*
	 * @param index: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void modify(int index, int value) {
		// write your code here
		modifySegmentTreeNode(treeRoot, index, value);
	}
}
