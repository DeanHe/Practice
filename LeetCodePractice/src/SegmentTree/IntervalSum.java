package SegmentTree;

import java.util.*;

public class IntervalSum {
	/**
	 * @param A:
	 *            An integer list
	 * @param queries:
	 *            An query list
	 * @return: The result list
	 */
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

	SegmentTreeNode root;

	public SegmentTreeNode buildSegmentTree(int start, int end, int[] arr) {
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

	public long querySegmentTree(SegmentTreeNode node, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (start == node.start && node.end == end) {
			return node.sum;
		}
		int mid = (node.start + node.end) / 2;
		long leftSum = 0, rightSum = 0;
		if (start <= mid) {
			if (end <= mid) {
				leftSum = querySegmentTree(node.left, start, end);
			} else {
				leftSum = querySegmentTree(node.left, start, mid);
			}
		}
		if (end >= mid + 1) {
			if (start >= mid + 1) {
				rightSum = querySegmentTree(node.right, start, end);
			} else {
				rightSum = querySegmentTree(node.right, mid + 1, end);
			}
		}
		return leftSum + rightSum;
	}

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
	/////////////////////////////Binary Index Tree/////////////////////////////////////////
	int[] array;
    long[] bit;
    int len;
    public List<Long> intervalSumBIT(int[] A, List<Interval> queries) {
        // write your code here
        len = A.length;
        array = new int[len];
        bit = new long[len + 1];
        for(int i = 0; i < len; i++){
            update(i, A[i]);
        }
        List<Long> res = new ArrayList<>();
        for(Interval q : queries){
            long temp = prefixSum(q.end) - prefixSum(q.start - 1);
            res.add(temp);
        }
        return res;
    }
    
    private long prefixSum(int x){
        long res = 0;
        for(int ind = x + 1; ind > 0; ind -= lowbit(ind)){
            res += bit[ind];
        }
        return res;
    }
    
    private void update(int i, int val){
        int delta = val - array[i];
        array[i] = val;
        for(int ind = i + 1; ind <= len; ind += lowbit(ind)){
            bit[ind] += delta;
        }
    }
    
    private int lowbit(int x){
        return x & -x;
    }
}
