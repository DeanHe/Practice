package segmentTree;

import java.util.*;
/*
Given an integer array(index from 0to n-1,where n is the size of this array),and an query list.Each query has two integers[start,end].For each query,calculate the minimum number between index start and end in the given array,return the result list.

Example 1:
Input:array:[1,2,7,8,5],queries:[(1,2),(0,4),(2,4)].Output:[2,1,5]
Example 2:

Input:array:[4,5,7,1],queries:[(1,2),(1,3)].Output:[5,1]
Challenge
O(logN)time for each query

Notice
We suggest you finish problem Segment Tree Build,Segment Tree Query and Segment Tree Modify first.
*/
public class IntervalMinimumNumber {
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * @param A:       An integer array
     * @param queries: An query list
     * @return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        SegmentTreeNode root = build(0, A.length - 1, A);
        List<Integer> res = new ArrayList<>();
        for (Interval query : queries) {
            int temp = query(root, query.start, query.end);
            res.add(temp);
        }
        return res;
    }

    class SegmentTreeNode {
        int start, end, minVal;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int minVal) {
            this.start = start;
            this.end = end;
            this.minVal = minVal;
            left = null;
            right = null;
        }
    }

    private SegmentTreeNode build(int start, int end, int[] A) {
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        if(start == end){
            return node;
        }
        int mid = (start + end) / 2;
        node.left = build(start, mid, A);
        node.right = build(mid + 1, end, A);
        node.minVal = Math.min(node.left.minVal, node.right.minVal);
        return node;
    }

    private int query(SegmentTreeNode node, int start, int end) {
        if (start > end) {
            return Integer.MAX_VALUE;
        }
        if (start <= node.start && node.end <= end) {
            return node.minVal;
        }
        int mid = (node.start + node.end) / 2;
        if (end <= mid) {
            return query(node.left, start, end);
        }
        if (mid < start) {
            return query(node.right, start, end);
        }
        int leftMin = query(node.left, start, mid);
        int rightMin = query(node.right, mid + 1, end);
        return Math.min(leftMin, rightMin);
    }
}
