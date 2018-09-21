
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
		int leftCount = 0, rightCount = 0;
		if (start <= mid) {
			if (end > mid) {
				leftCount = query(root.left, start, mid);
			} else {
				leftCount = query(root.left, start, end);
			}
		}
		if (end >= mid + 1) {
			if (start >= mid + 1) {
				rightCount = query(root.right, start, end);
			} else {
				rightCount = query(root.right, mid + 1, end);
			}
		}
		return rightCount + leftCount;
	}
}
