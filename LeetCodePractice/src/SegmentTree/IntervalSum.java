package SegmentTree;

import java.util.*;
/*		Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.
		We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

		Example
		Example 1:
		Input: array = [1,2,7,8,5],  queries = [(0,4),(1,2),(2,4)]
		Output: [23,9,20]

		Example 2:
		Input: array : [4,3,1,2],  queries : [(1,2),(0,2)]
		Output: [4,8]
		Challenge
		O(logN) time for each query
*/
public class IntervalSum {
	SegmentTreeNode root;
	/**
	 * @param A:
	 *            An integer list
	 * @param queries:
	 *            An query list
	 * @return: The result list
	 */
	public List<Long> intervalSum(int[] A, List<Interval> queries) {
		// write your code here
		root = buildSegmentTree(0, A.length - 1, A);
		List<Long> res = new ArrayList<>();
		for (Interval interval : queries) {
			long temp = querySegmentTree(root, interval.start, interval.end);
			res.add(temp);
		}
		return res;
	}

	class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	class SegmentTreeNode {
		int start, end;
		long sum;
		SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}

	private SegmentTreeNode buildSegmentTree(int start, int end, int[] arr) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode res = new SegmentTreeNode(start, end, arr[start]);
		if (start == end) {
			return res;
		}
		int mid = (start + end) / 2;
		res.left = buildSegmentTree(start, mid, arr);
		res.right = buildSegmentTree(mid + 1, end, arr);
		res.sum = res.left.sum + res.right.sum;
		return res;
	}

	private long querySegmentTree(SegmentTreeNode node, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (start <= node.start && node.end <= end) {
			return node.sum;
		}
		int mid = (node.start + node.end) / 2;
		if(end <= mid){
			return querySegmentTree(node.left, start, end);
		}
		if(mid < start){
			return querySegmentTree(node.right, start, end);
		}
		long leftSum = querySegmentTree(node.left, start, mid);
		long rightSum = querySegmentTree(node.right, mid + 1, end);
		return leftSum + rightSum;
	}
}
